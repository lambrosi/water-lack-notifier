package com.lucasambrosi.water.lack.notifier.controller;

import com.lucasambrosi.water.lack.notifier.CaptchaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @Autowired
    private CaptchaProvider captchaProvider;

    @GetMapping
    public String getToken() {
        String s = captchaProvider.generateToken();
        System.out.println(s);
        return s;
    }
}
