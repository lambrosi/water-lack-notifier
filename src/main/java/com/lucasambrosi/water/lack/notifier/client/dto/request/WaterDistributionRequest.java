package com.lucasambrosi.water.lack.notifier.client.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WaterDistributionRequest {

    @JsonProperty("tipoSolicitacaoEtapaId")
    private Integer requestStepId;

    @JsonProperty("canal")
    private String channel;

    @JsonProperty("campos")
    private List<WaterDistributionFields> fields;

    private String captcha;

    public Integer getRequestStepId() {
        return requestStepId;
    }

    public void setRequestStepId(Integer requestStepId) {
        this.requestStepId = requestStepId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public List<WaterDistributionFields> getFields() {
        return fields;
    }

    public void setFields(List<WaterDistributionFields> fields) {
        this.fields = fields;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
