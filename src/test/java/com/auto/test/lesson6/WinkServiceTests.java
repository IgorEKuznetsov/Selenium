package com.auto.test.lesson6;


import com.auto.main.lesson6.WInkIndex;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import  org.junit.jupiter.params.provider.MethodSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WinkServiceTests {
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://wink.ru/");
    }

    @Test
    @DisplayName("Load to device form test")
    void loadToDevice() {
        new WInkIndex(driver)
                .clickOnRandomElementFromList()
                .checkSetupAppButtonIsDisplayed();
    }

    @ParameterizedTest
    @MethodSource("com.auto.test.lesson6.DataProvider#menuDataProvider")
    @DisplayName("Popup tests")
    void popup(String itemXpath, String headerXpath) {
        new WInkIndex(driver).checkPageHeader(itemXpath, headerXpath);
    }

    @AfterEach
    void killDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
