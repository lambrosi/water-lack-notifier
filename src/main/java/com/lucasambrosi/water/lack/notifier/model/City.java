package com.lucasambrosi.water.lack.notifier.model;

public enum City {

    ESTEIO(4307708L),
    CACEQUI(4302907L);

    private Long code;

    City(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}
