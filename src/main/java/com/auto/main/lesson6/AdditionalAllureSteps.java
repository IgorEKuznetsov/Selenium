package com.auto.main.lesson6;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;


public class AdditionalAllureSteps implements WebDriverListener {
    private static Logger log = LoggerFactory.getLogger("wink");

    public void beforeClick(WebElement element) {
        log.info("клик по элементу " + element.getText());
        Allure.step("клик по элементу " + element.getText());
    }

    public void beforeQuit(WebDriver driver) {
        Allure.addAttachment("Перед закрытием браузера", new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
        ));
    }
}
