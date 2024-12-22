package org.rr.moduleselector.selector;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.rr.moduleselector.pdf.PdfGenerationService;
import org.rr.moduleselector.selector.model.Module;
import org.rr.moduleselector.selector.model.Subject;
import org.rr.moduleselector.selector.model.Universities;
import org.rr.moduleselector.survey.SurveyService;
import org.rr.moduleselector.survey.model.SurveyData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class SelectorController {

    private final SurveyService surveyService;
    private final DataHolder dataHolder = DataHolder.getInstance();

    /**
     * Zeigt die Haupt-Seite ("selector.html").
     */
    @GetMapping({"/", "/selector"})
    public String showModules(HttpSession session, Model model) {
        // Sicherstellen, dass ein Survey ausgefüllt ist:
        SurveyData surveyData = surveyService.loadSurveyFromSession(session);
        if (surveyData == null) {
            return "redirect:/survey";
        }
        model.addAttribute("userData", surveyData);

        // Wenn Fach bereits in Session gesetzt, Daten laden
        Subject selectedSubject = (Subject) session.getAttribute("selectedSubject");
        if (selectedSubject != null) {
            // Für Semester 1..4 Compulsory + Additional ablegen
            for (int semester = 1; semester <= 4; semester++) {
                model.addAttribute("compulsoryModules" + semester,
                        dataHolder.getCompulsoryModules(selectedSubject, semester));
                model.addAttribute("additionalModules" + semester,
                        dataHolder.getAdditionalModules(selectedSubject, semester));
            }
            // Partner (falls gewählt)
            Universities chosenPartner = (Universities) session.getAttribute("selectedPartnerUniversity");
            if (chosenPartner != null) {
                model.addAttribute("studyAbroadModules",
                        dataHolder.getStudyAbroadModules(selectedSubject, chosenPartner));
            }
            // Liste aller verfügbaren Partnerunis
            model.addAttribute("universities", Universities.values());
        }
        return "selector";  // Thymeleaf-Template "selector.html"
    }

    /**
     * POST /subject: Speichert das ausgewählte Fach in der Session
     * und leert alte Auswahl.
     */
    @PostMapping("/subject")
    public String selectSubject(@RequestParam Subject subject, HttpSession session) {
        // Neues Fach in Session merken
        session.setAttribute("selectedSubject", subject);

        // Frühere Selektionen zurücksetzen
        for (int semester = 1; semester <= 4; semester++) {
            session.removeAttribute("semester" + semester + "Modules");
            session.removeAttribute("semester" + semester + "Alternatives");
            session.removeAttribute("semester" + semester + "Universities");
        }
        session.removeAttribute("selectedPartnerUniversity");

        // Zur Startseite/Selector zurück
        return "redirect:/";
    }
    @PostMapping("/saveAll")
    public ResponseEntity<byte[]> saveAll(@RequestParam Map<String, String> allParams,
                                          HttpSession session, PdfGenerationService pdfGenerationService) {
        // Get user data
        SurveyData userData = surveyService.loadSurveyFromSession(session);
        Subject selectedSubject = (Subject) session.getAttribute("selectedSubject");

        // Collect all compulsory modules
        Map<Integer, List<Module>> compulsoryModules = new HashMap<>();
        Map<Integer, List<Module>> chosenModules = new HashMap<>();

        // Get modules for each semester
        for (int semester = 1; semester <= 4; semester++) {
            compulsoryModules.put(semester,
                    dataHolder.getCompulsoryModules(selectedSubject, semester));

            // Process chosen modules from the form
            List<Module> semesterChosen = new ArrayList<>();
            dataHolder.getAdditionalModules(selectedSubject, semester)
                    .forEach(module -> {
                        if (module.getAlternative().isEmpty()) {
                            semesterChosen.add(module);
                        } else {
                            String chosen = allParams.get("alt" + module.getCode());
                            if (chosen != null) {
                                module.getAlternative().stream()
                                        .filter(alt -> alt.getCode().equals(chosen))
                                        .findFirst()
                                        .ifPresent(semesterChosen::add);
                            }
                        }
                    });
            chosenModules.put(semester, semesterChosen);
        }

        // Get partner university modules if selected
        List<Module> partnerModules = new ArrayList<>();
        if (allParams.containsKey("selectedPartnerUniversity") &&
                !allParams.get("selectedPartnerUniversity").isEmpty()) {
            Universities uni = Universities.valueOf(allParams.get("selectedPartnerUniversity"));
            List<Module> allPartnerModules = dataHolder.getStudyAbroadModules(selectedSubject, uni);

            // Filter partner modules to only include selected ones
            for (Module module : allPartnerModules) {
                if (module.getAlternative().isEmpty()) {
                    // Add regular modules
                    partnerModules.add(module);
                } else {
                    // For modules with alternatives, only add the selected one
                    String chosen = allParams.get("alt" + module.getCode());
                    if (chosen != null) {
                        module.getAlternative().stream()
                                .filter(alt -> alt.getCode().equals(chosen))
                                .findFirst()
                                .ifPresent(partnerModules::add);
                    }
                }
            }
        }

        // Generate PDF
        byte[] pdfContent = pdfGenerationService.generateModulesPdf(
                userData, compulsoryModules, chosenModules, partnerModules);

        // Return PDF with proper headers for browser display
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "module-selection.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }


    @GetMapping("/api/universities/{uni}/modules")
    @ResponseBody
    public List<Module> getPartnerModules(@PathVariable("uni") Universities uni,
                                          HttpSession session) {
        // Ausgewähltes Fach aus Session
        Subject selectedSubject = (Subject) session.getAttribute("selectedSubject");
        if (selectedSubject == null) {
            // Keine Fachwahl => leere Liste
            return List.of();
        }
        // Erfrage die Module für Semester 3 (StudyAbroad) aus DataHolder
        return dataHolder.getStudyAbroadModules(selectedSubject, uni);
    }
}
