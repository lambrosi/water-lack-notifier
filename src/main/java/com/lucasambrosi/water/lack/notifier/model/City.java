package com.lucasambrosi.water.lack.notifier.model;

public enum City {

    ESTEIO("codMunicipio", 4307708L),
    CACEQUI("codMunicipio", 4302907L);

    private String parameterName;
    private Long code;

    City(String parameterName, Long code) {
        this.parameterName = parameterName;
        this.code = code;
    }

    public String getParameterName() {
        return parameterName;
    }

    public Long getCode() {
        return code;
    }
}
