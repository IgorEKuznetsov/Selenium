package com.auto.test.lesson5;

import com.auto.test.lesson3.Auth;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    static void registerChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @ParameterizedTest
    @DisplayName("Positive auth tests")
    @MethodSource("accessUserDataProvider")
    void authFieldsPositiveTest(AuthUser user, boolean expectedMarketIsOpen) {
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(user.getUsername());
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(user.getPwd());
        driver.findElement(By.id("login-button")).click();
        boolean actualMarketIsOpen = driver.findElement(By.xpath("//span[@class='title']")).isDisplayed();
        assertThat(expectedMarketIsOpen).isEqualTo(actualMarketIsOpen);
    }

    @ParameterizedTest
    @DisplayName("Check error is displayed")
    @MethodSource("userDataProvider")
    void authFieldErrorsCheckTest(AuthUser user, boolean expectedErrorIsDisplayed) {
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(user.getUsername());
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(user.getPwd());
        driver.findElement(By.id("login-button")).click();
        boolean actualErrorIsDisplayed = driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
        assertThat(expectedErrorIsDisplayed).isEqualTo(actualErrorIsDisplayed);
    }

    @Test
    @DisplayName("Check error message when fields are empty")
    void errorMessageEmptyFieldsTest() {
        driver.findElement(By.id("login-button")).click();
        assertThat(driver.findElement(By.xpath("//h3[@data-test='error']")).getText())
                .isEqualTo("Epic sadface: Username is required");
    }

    private static Stream<Arguments> userDataProvider() {
        return Stream.of(
                Arguments.of(new AuthUser("standard_user", "12345"), true),
                Arguments.of(new AuthUser("standard_user", ""), true),
                Arguments.of(new AuthUser("locked_out_user", "locked_out_user"), true),
                Arguments.of(new AuthUser("", "secret_sauce"), true),
                Arguments.of(new AuthUser("", ""), true)
        );
    }

    private static Stream<Arguments> accessUserDataProvider() {
        return Stream.of(
                Arguments.of(new AuthUser("standard_user", "secret_sauce"), true),
                Arguments.of(new AuthUser("performance_glitch_user", "secret_sauce"), true),
                Arguments.of(new AuthUser("problem_user", "secret_sauce"), true)
        );
    }


    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
