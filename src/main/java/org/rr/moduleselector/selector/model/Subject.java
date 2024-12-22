package org.rr.moduleselector.selector.model;

public enum Subject {
    SCIENTIFIC_ASSISTANT("Scientific Assistant"),
    BUSINESS_ALLROUNDER("Business Allrounder"),
    IT_PROJECT_MANAGER("IT/Project Manager"),
    SOFTWARE_ENGINEER("Software Engineer"),
    DATA_ANALYST("Data Analyst");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
