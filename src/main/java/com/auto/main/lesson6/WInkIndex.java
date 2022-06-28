package com.auto.main.lesson6;

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

    public WinkFilm clickOnRandomElementFromList() {
        driver.manage().window().setSize(new Dimension(1920, 1080));

        while (searchElements.size() == 0) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2500)", "");
        }
        //actions.scrollByAmount(0, 2500);
        // ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2900)", "");
        // wait.until(d -> d.findElements(By.xpath("//h2[contains(text(), 'Кинопоиск')]")).size() > 0);

        filmList.stream()
                .findAny()
                .get()
                .click();
        return new WinkFilm(driver);
    }

    public void checkPageHeader(String itemXpath, String headerXpath) {
        actions.moveToElement(dropdownButton)
                .click(driver.findElement(By.xpath(itemXpath)))
                .build()
                .perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(headerXpath)));
        Assertions.assertTrue(driver.findElement(By.xpath(headerXpath)).isDisplayed());
    }
}

