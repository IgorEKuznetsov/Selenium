package com.auto.test.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class WinkTests {
    WebDriver driver;
    WebDriverWait wait;


    @BeforeAll
    static void registerChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://wink.ru/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    @DisplayName("Load to device form test")
    void loadToDeviceTest() {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        while (driver.findElements(By.xpath("//h2[contains(text(), 'Кинопоиск')]/.." +
                "/following::div[contains(@data-analytic, '143676210')]/ancestor::div[@class='swiper-wrapper']/div")).stream().count() == 0) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2500)", "");
        }

        List<WebElement> filmList = driver.findElements(By.xpath("//h2[contains(text(), 'Кинопоиск')]/.." +
                "/following::div[contains(@data-analytic, '143676210')]/ancestor::div[@class='swiper-wrapper']/div"));
        filmList.get(1).click();
        driver.findElement(By.xpath("//button[@data-test='media_item-button-download']//span[contains(text(), 'Загрузить')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Установить')]")));

        Assertions.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Установить')]")).isDisplayed());

    }

    @ParameterizedTest
    @DisplayName("Popup tests")
    @MethodSource("menuDataProvider")
    void clickPopupCheckNewPageIsOpenTest(String itemXpath, String headerXpath) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//button[contains(@class, 'item_dropdown')]")))
                .click(driver.findElement(By.xpath(itemXpath)))
                .build()
                .perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(headerXpath)));
        Assertions.assertTrue(driver.findElement(By.xpath(headerXpath)).isDisplayed());
    }

    private static Stream<Arguments> menuDataProvider() {
        return Stream.of(
                Arguments.of(("//a[contains(@class, 'item') and @href='/books']"), ("//h1[.='Аудиокниги']")),
                Arguments.of(("//a[contains(@class, 'item') and @href='/services']"), ("//h1[.='Подписки и сервисы']")),
                Arguments.of(("//a[contains(@class, 'item') and @href='/collections']"), ("//h1[.='Все подборки']")),
                Arguments.of(("//a[contains(@class, 'item') and @href='/promocode']"), ("//h1[.='Активация промокода']"))
        );
    }


    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
