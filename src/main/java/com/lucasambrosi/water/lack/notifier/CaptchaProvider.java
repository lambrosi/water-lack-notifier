package com.lucasambrosi.water.lack.notifier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CaptchaProvider {

    private final WebDriver webDriver;
    private final String url;
    private final String userKey;
    private final String scriptCommand;

    public CaptchaProvider(final WebDriver webDriver,
                           @Value("${app.captcha.url}") final String url,
                           @Value("${app.captcha.user-key}") final String userKey,
                           @Value("${app.captcha.script-command}") final String scriptCommand) {
        this.webDriver = webDriver;
        this.url = url;
        this.userKey = userKey;
        this.scriptCommand = scriptCommand;
    }

    public String generateToken() {
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
    }
}
