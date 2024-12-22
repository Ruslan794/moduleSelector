package org.rr.moduleselector.selector;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.rr.moduleselector.selector.model.Module;
import org.rr.moduleselector.selector.model.Subject;
import org.rr.moduleselector.selector.model.Universities;
import org.rr.moduleselector.survey.SurveyService;
import org.rr.moduleselector.survey.model.SurveyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * POST /saveAll: Sammelformular aller Semester + Uni.
     */
    @PostMapping("/saveAll")
    public String saveAll(@RequestParam Map<String, String> allParams,
                          HttpSession session) {
        System.out.println("===== Empfangenes Formular =====");
        allParams.forEach((key, value) ->
                System.out.println(key + " = " + value));
        System.out.println("================================");

        // Beispiel: Partner-Uni in Session übernehmen (falls gewünscht)
        if (allParams.containsKey("selectedPartnerUniversity")) {
            String uniStr = allParams.get("selectedPartnerUniversity");
            if (!uniStr.isEmpty()) {
                session.setAttribute("selectedPartnerUniversity", Universities.valueOf(uniStr));
            } else {
                session.removeAttribute("selectedPartnerUniversity");
            }
        }

        // Oder alternative Auswahlen pro Semester auswerten etc...

        // Zurück zur Seite
        return "redirect:/selector";
    }

    /**
     * GET /api/universities/{uni}/modules: Gibt die Studien-Module einer Uni (für das aktuelle Fach) als JSON zurück.
     */
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
