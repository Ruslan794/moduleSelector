package org.rr.moduleselector.selector.model;

public enum Universities {
    ASUE("Armenian State University of Economics (ASUE)", "Armenia"),
    IBSU("International Black Sea University (IBSU)", "Georgia"),
    KAFU("Kazakh-American Free University (KAFU)", "Kazakhstan"),
    INAI("Kyrgyz-German Institute of Applied Informatics (INAI.kg)", "Kyrgyzstan");

    private final String displayName;
    private final String country;

    Universities(String displayName, String country) {
        this.displayName = displayName;
        this.country = country;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCountry() {
        return country;
    }
}