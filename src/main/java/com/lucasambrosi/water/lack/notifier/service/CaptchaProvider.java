package com.lucasambrosi.water.lack.notifier.service;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class CaptchaProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaProvider.class);

    private static final Long MAX_ATTEMPTS = 5L;
    private static final Duration SECONDS_INTERVAL = Duration.ofSeconds(2);

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

    public Mono<String> generateToken() {
        return this.navigateToUrl()
                .ofType(JavascriptExecutor.class)
                .flatMap(this::executeScript);
    }

    private Mono<WebDriver> navigateToUrl() {
        webDriver.navigate().to(url);
        return Mono.just(webDriver);
    }

    private Mono<String> executeScript(JavascriptExecutor javascriptExecutor) {
        return Mono.just(javascriptExecutor)
                .doOnNext(it -> LOGGER.info("Getting captcha"))
                .map(executor -> executor.executeScript(scriptCommand))
                .ofType(String.class)
                .retryWhen(Retry.fixedDelay(MAX_ATTEMPTS, SECONDS_INTERVAL));
    }
}
