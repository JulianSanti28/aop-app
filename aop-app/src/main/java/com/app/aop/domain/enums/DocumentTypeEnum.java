package com.app.aop.domain.enums;

public enum DocumentTypeEnum {
    C("Cédula"), P("Pasaporte");
    private final String description;
    DocumentTypeEnum(String description) {
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
}
