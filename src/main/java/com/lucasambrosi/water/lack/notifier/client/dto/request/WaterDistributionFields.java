package com.lucasambrosi.water.lack.notifier.client.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasambrosi.water.lack.notifier.model.City;

public class WaterDistributionFields {
    @JsonProperty("parametro")
    private String parameterName;

    @JsonProperty("valor")
    private Long value;

    private WaterDistributionFields(String parameterName, Long value) {
        this.parameterName = parameterName;
        this.value = value;
    }

    public static WaterDistributionFields of(City city) {
        return new WaterDistributionFields("codMunicipio", city.getCode());
    }

    public String getParameterName() {
        return parameterName;
    }

    public Long getValue() {
        return value;
    }
}
