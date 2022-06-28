package com.auto.test.lesson6;

import com.auto.main.lesson6.AuthSaucedemo;
import com.auto.main.lesson6.AuthUser;
import com.auto.main.lesson6.Products;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SaucedemoServiceTests {
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    @DisplayName("Check error message when fields are empty")
    void errorMessageEmptyFieldsTest() {
        new AuthSaucedemo(driver).checkErrorMessage();
    }

    @ParameterizedTest
    @DisplayName("Positive auth tests")
    @MethodSource("com.auto.test.lesson6.DataProvider#accessUserDataProvider")
    void positiveLoginTest(AuthUser user, boolean expectedMarketIsOpen) {
        new AuthSaucedemo(driver).checkLogin(user, expectedMarketIsOpen);
    }

    @ParameterizedTest
    @DisplayName("Negative auth tests")
    @MethodSource("com.auto.test.lesson6.DataProvider#userDataProvider")
    void negativeLoginTest(AuthUser user, boolean expectedErrorIsDisplayed) {
        new AuthSaucedemo(driver).checkLoginError(user, expectedErrorIsDisplayed);
    }

    @Test
    @DisplayName("Add to cart test")
    void addToCart() {
        new AuthSaucedemo(driver)
                .login("standard_user", "secret_sauce")
                .clickOnProductByName("Sauce Labs Bike Light")
                .clickAddToCart()
                .checkSumAndRemoveButton("$9.99");
    }


    @AfterEach
    void killDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
