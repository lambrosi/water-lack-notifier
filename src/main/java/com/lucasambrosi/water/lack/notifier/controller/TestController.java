package com.lucasambrosi.water.lack.notifier.controller;

import com.lucasambrosi.water.lack.notifier.service.CaptchaProvider;
import com.lucasambrosi.water.lack.notifier.client.WaterDistributionClient;
import com.lucasambrosi.water.lack.notifier.client.dto.request.WaterDistributionFields;
import com.lucasambrosi.water.lack.notifier.client.dto.request.WaterDistributionRequest;
import com.lucasambrosi.water.lack.notifier.model.City;
import com.lucasambrosi.water.lack.notifier.model.WaterDistributionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collections;

@RestController
@RequestMapping
public class TestController {

    @Autowired
    private CaptchaProvider captchaProvider;

    @Autowired
    private WaterDistributionClient waterDistributionClient;

    @GetMapping
    public Flux<WaterDistributionStatus> test() {
        WaterDistributionFields fields = WaterDistributionFields.of(City.CACEQUI);

        return captchaProvider.generateToken()
                .map(token -> {
                    WaterDistributionRequest request = new WaterDistributionRequest();
                    request.setRequestStepId(5);
                    request.setChannel("5");
                    request.setFields(Collections.singletonList(fields));
                    request.setCaptcha(token);
                    return request;
                })
                .flatMapMany(waterDistributionClient::get);
    }
}
