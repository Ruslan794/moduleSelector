package org.rr.moduleselector.survey;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.rr.moduleselector.survey.model.SurveyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping
    public String showSurveyForm(HttpSession session, Model model) {
        SurveyData surveyData = surveyService.loadSurveyFromSession(session);
        if (surveyData == null) {
            surveyData = new SurveyData();
        }
        model.addAttribute("surveyData", surveyData);
        return "survey_form";
    }

    @PostMapping
    public String processSurvey(@Valid @ModelAttribute("surveyData") SurveyData surveyData, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "survey_form";
        }

        surveyService.saveSurveyToSession(session, surveyData);
        model.addAttribute("success", "Form saved successfully!");
        return "redirect:/selector";
    }

    @GetMapping("/clear")
    public String clearForm(HttpSession session) {
        surveyService.clearSession(session);
        return "redirect:/survey";
    }
}