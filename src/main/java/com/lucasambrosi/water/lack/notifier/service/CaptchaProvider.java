package com.lucasambrosi.water.lack.notifier.service;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CaptchaProvider {

    private final WebDriver webDriver;
    private final String url;
    private final String scriptCommand;

    public CaptchaProvider(final WebDriver webDriver,
                           @Value("${app.captcha.url}") final String url,
                           @Value("${app.captcha.script-command}") final String scriptCommand) {
        this.webDriver = webDriver;
        this.url = url;
        this.scriptCommand = scriptCommand;
    }

    /*public String generateToken() {
        webDriver.navigate().to(url);

        this.sleep();

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return (String) js.executeScript(scriptCommand);
    }

    private void sleep() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public Mono<String> generateToken() {
        return this.navigate()
                .map(it -> this.sleep())
                .map(it -> webDriver)
                .ofType(JavascriptExecutor.class)
                .map(executor -> executor.executeScript(scriptCommand))
                .ofType(String.class);
    }

    private Mono<Boolean> navigate() {
        webDriver.navigate().to(url);
        return Mono.just(true);
    }

    private Mono<Boolean> sleep() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Mono.just(true);
    }
}
