package com.lucasambrosi.water.lack.notifier.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cofiguration {

    @Bean
    public WebDriver webDriver(ChromeOptions chromeOptions) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    @Bean
    public ChromeOptions chromeOptions() {
        return new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--headless")
                .addArguments("disable-gpu");
    }
}
