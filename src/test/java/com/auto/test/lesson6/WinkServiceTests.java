package com.auto.test.lesson6;


import com.auto.main.lesson6.AdditionalAllureSteps;
import com.auto.main.lesson6.WInkIndex;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.MethodSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

@Feature("Онлайн кинотеатр")
public class WinkServiceTests {
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new AdditionalAllureSteps()).decorate(new ChromeDriver());
        driver.get("https://wink.ru/");
    }

    @Test
    @Description("Load to device form test")
    void loadToDevice() {
        new WInkIndex(driver)
                .clickOnRandomElementFromList()
                .checkSetupAppButtonIsDisplayed();
    }

    @ParameterizedTest
    @MethodSource("com.auto.test.lesson6.DataProvider#menuDataProvider")
    @Description("Popup tests")
    void popup(String itemXpath, String headerXpath) {
        new WInkIndex(driver).checkPageHeader(itemXpath, headerXpath);
    }

    @AfterEach
    void killDriver() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log : logEntries) {
            Allure.addAttachment("Элемент лога браузера", log.getMessage());
        }
        driver.quit();
    }
}
