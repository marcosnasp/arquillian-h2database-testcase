package org.example.entity;

public enum ProblemaHorus {

    GRAVE("GRAVE"),

    NAOGRAVE("NAOGRAVE");

    private String problemaHorus;

    private ProblemaHorus(String problemaHorus) {
        this.problemaHorus = problemaHorus;
    }

    public String getNome() {
        return problemaHorus;
    }

}