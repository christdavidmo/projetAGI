package com.example.amicale.Data.Enumeration;

import lombok.Getter;

@Getter
public enum TypeEcole {
    DROIT("droit"),
    CAMPUS_DIGITAL("ingénieur"),
    MADIBA("MADIBA"),
    MANAGEMENT("Management");

    public final String label;

    TypeEcole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
