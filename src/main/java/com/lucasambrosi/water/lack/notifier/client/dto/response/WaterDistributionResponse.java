package com.lucasambrosi.water.lack.notifier.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WaterDistributionResponse {
    @JsonProperty("protocolo")
    private Long protocol;
    @JsonProperty("numeroProcesso")
    private Long processNumber;
    @JsonProperty("resposta")
    private List<WaterDistributionDataResponse> response;

    public Long getProtocol() {
        return protocol;
    }

    public void setProtocol(Long protocol) {
        this.protocol = protocol;
    }

    public Long getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(Long processNumber) {
        this.processNumber = processNumber;
    }

    public List<WaterDistributionDataResponse> getResponse() {
        return response;
    }

    public void setResponse(List<WaterDistributionDataResponse> response) {
        this.response = response;
    }
}
