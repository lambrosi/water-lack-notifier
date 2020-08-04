package com.lucasambrosi.water.lack.notifier.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class WaterDistributionDataResponse {
    @JsonProperty("turnoNormalizacao")
    private NormalizationShift normalizationShift;

    @JsonProperty("descricaoOcorrencia")
    private OcurrencyDescription ocurrencyDescription;

    public String getNormalizationShift() {
        return Optional.ofNullable(normalizationShift)
                .map(NormalizationShift::getValue)
                .orElse("");
    }

    public void setNormalizationShift(NormalizationShift normalizationShift) {
        this.normalizationShift = normalizationShift;
    }

    public String getOcurrencyDescription() {
        return Optional.ofNullable(ocurrencyDescription)
                .map(OcurrencyDescription::getValue)
                .orElse("");
    }

    public void setOcurrencyDescription(OcurrencyDescription ocurrencyDescription) {
        this.ocurrencyDescription = ocurrencyDescription;
    }

    public static class NormalizationShift {
        @JsonProperty("valor")
        private String value;

        public NormalizationShift() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class OcurrencyDescription {
        @JsonProperty("valor")
        private String value;

        public OcurrencyDescription() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}