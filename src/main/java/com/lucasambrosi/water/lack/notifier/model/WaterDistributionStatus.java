package com.lucasambrosi.water.lack.notifier.model;

public class WaterDistributionStatus {

    private Boolean hasOcurrency;
    private String normalizationShift;
    private String ocurrencyDescription;

    private WaterDistributionStatus(boolean hasOcurrency) {
        this.hasOcurrency = hasOcurrency;
    }

    private WaterDistributionStatus(final String normalizationShift,
                                    final String ocurrencyDescription) {
        this(Boolean.TRUE);
        this.normalizationShift = normalizationShift;
        this.ocurrencyDescription = ocurrencyDescription;
    }

    public static WaterDistributionStatus withoutOcurrency() {
        return new WaterDistributionStatus(Boolean.FALSE);
    }

    public static WaterDistributionStatus withOcurrency(final String normalizationShift,
                                                        final String ocurrencyDescription) {
        return new WaterDistributionStatus(normalizationShift, ocurrencyDescription);
    }

    public Boolean getHasOcurrency() {
        return hasOcurrency;
    }

    public String getNormalizationShift() {
        return normalizationShift;
    }

    public String getOcurrencyDescription() {
        return ocurrencyDescription;
    }
}
