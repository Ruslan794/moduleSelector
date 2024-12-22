package org.rr.moduleselector.selector;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.rr.moduleselector.selector.model.Subject;
import org.rr.moduleselector.selector.model.Universities;
import org.rr.moduleselector.survey.SurveyService;
import org.rr.moduleselector.survey.model.SurveyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class SelectorController {

    private final SurveyService surveyService;
    private final DataHolder dataHolder = DataHolder.getInstance();

    @GetMapping({"/", "/selector"})
    public String showModules(HttpSession session, Model model) {
        // Ensure user has filled out survey:
        SurveyData surveyData = surveyService.loadSurveyFromSession(session);
        if (surveyData == null) {
            return "redirect:/survey";
        }
        model.addAttribute("userData", surveyData);

        Subject selectedSubject = (Subject) session.getAttribute("selectedSubject");
        if (selectedSubject != null) {
            // Provide compulsory & additional modules for each semester
            for (int semester = 1; semester <= 4; semester++) {
                model.addAttribute("compulsoryModules" + semester,
                        dataHolder.getCompulsoryModules(selectedSubject, semester));
                model.addAttribute("additionalModules" + semester,
                        dataHolder.getAdditionalModules(selectedSubject, semester));
            }

            // For semester 3, see if user already picked a partner:
            Universities chosenPartner = (Universities) session.getAttribute("selectedPartnerUniversity");
            if (chosenPartner != null) {
                // Only those partner’s modules
                List<org.rr.moduleselector.selector.model.Module> partnerModules =
                        dataHolder.getStudyAbroadModules(selectedSubject, chosenPartner);
                model.addAttribute("studyAbroadModules", partnerModules);
            }
            model.addAttribute("universities", Universities.values());
        }
        return "selector";
    }

    @PostMapping("/subject")
    public String selectSubject(@RequestParam Subject subject, HttpSession session) {
        // Save new subject
        session.setAttribute("selectedSubject", subject);

        // Clear previous selections
        for (int semester = 1; semester <= 4; semester++) {
            session.removeAttribute("semester" + semester + "Modules");
            session.removeAttribute("semester" + semester + "Alternatives");
            session.removeAttribute("semester" + semester + "Universities");
        }
        session.removeAttribute("selectedPartnerUniversity");

        return "redirect:/";
    }

    /**
     * Saves the selection for each semester.
     *
     * For semester 3 specifically, we handle the chosen partner university.
     */
    @PostMapping("/semester/{semester}/save")
    public String saveSemesterSelection(
            @PathVariable int semester,
            @RequestParam(required = false) String selectedPartnerUniversity, // only relevant for semester 3
            @RequestParam Map<String, String> allParams,
            HttpSession session
    ) {
        // If it's semester 3, handle partner selection:
        if (semester == 3 && selectedPartnerUniversity != null) {
            // If user picked no partner, clear
            if (selectedPartnerUniversity.isEmpty()) {
                session.removeAttribute("selectedPartnerUniversity");
            } else {
                // Save user’s choice in session
                Universities chosenUni = Universities.valueOf(selectedPartnerUniversity);
                session.setAttribute("selectedPartnerUniversity", chosenUni);
            }
        }

        // Radio selections for alternatives
        Map<String, String> alternatives = allParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("alt"))  // e.g. altOPT1, altASUE3, ...
                .collect(Collectors.toMap(
                        e -> e.getKey().substring(3), // everything after 'alt'
                        Map.Entry::getValue
                ));
        session.setAttribute("semester" + semester + "Alternatives", alternatives);

        // We no longer do checkboxes for additional modules, so if you had a “selectedModules” param, remove it:
        // session.setAttribute("semester" + semester + "Modules", selectedModules);

        return "redirect:/";
    }

}
