package com.auto.main.lesson6;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class WInkIndex extends BaseView {

    public WInkIndex(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[contains(text(), 'Кинопоиск')]/.." +
            "/following::div[contains(@data-analytic, '143676210')]/ancestor::div[@class='swiper-wrapper']/div")
    private List<WebElement> filmList;

    @FindBy(xpath = "//h2[contains(text(), 'Кинопоиск')]")
    private List<WebElement> searchElements;

    @FindBy(xpath = "//button[contains(@class, 'item_dropdown')]")
    private WebElement dropdownButton;

    @Step("Рандомный выбор фильма из списка")
    public WinkFilm clickOnRandomElementFromList() {
        driver.manage().window().setSize(new Dimension(1920, 1080));

        while (searchElements.size() == 0) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2500)", "");
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchElements.get(0));

        filmList.stream()
                .findAny()
                .get()
                .click();
        return new WinkFilm(driver);
    }

    @Step("Проверка заголовков страниц")
    public void checkPageHeader(String itemXpath, String headerXpath) {
        wait.until(d -> d.findElements(By.xpath("//button[contains(@class, 'item_dropdown')]")).size() > 0);
        Allure.step("Наведение на элемент раскрывающегося списка");
        actions.moveToElement(dropdownButton)
                .build()
                .perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(itemXpath)));
        driver.findElement(By.xpath(itemXpath)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(headerXpath)));
        Allure.step("Заголовок " + driver.findElement(By.xpath(headerXpath)).getText() + " отображен");
        Assertions.assertTrue(driver.findElement(By.xpath(headerXpath)).isDisplayed());
    }
}

