package com.example.amicale.Data.Enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public enum TypeRessource {
    PDF("PDF"),
    DOC("Word Document"),
    XLS("Excel Spreadsheet"),
    TXT("Text File");

    private final String label;

    TypeRessource(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
