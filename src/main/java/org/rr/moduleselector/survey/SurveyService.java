package org.rr.moduleselector.survey;

import jakarta.servlet.http.HttpSession;
import org.rr.moduleselector.survey.model.SurveyData;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
    private static final String SESSION_KEY = "surveyData";

    public void saveSurveyToSession(HttpSession session, SurveyData surveyData) {
        session.setAttribute(SESSION_KEY, surveyData);
    }

    public SurveyData loadSurveyFromSession(HttpSession session) {
        return (SurveyData) session.getAttribute(SESSION_KEY);
    }

    public void clearSession(HttpSession session) {
        session.removeAttribute(SESSION_KEY);
    }
}