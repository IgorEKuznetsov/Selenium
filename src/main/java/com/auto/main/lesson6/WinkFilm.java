package com.auto.main.lesson6;


import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WinkFilm extends BaseView {
    private static final String SETUP_BUTTON_XPATH = "//span[contains(text(), 'Установить')]";

    public WinkFilm(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = SETUP_BUTTON_XPATH)
    private WebElement setupButton;

    @FindBy(xpath = "//button[@data-test='media_item-button-download']//span[contains(text(), 'Загрузить')]")
    private WebElement loadButton;

    @Step("Проверка отображения кнопки 'Установить приложение'")
    public void checkSetupAppButtonIsDisplayed() {
        loadButton.click();
        // wait.until(d -> d.findElements(By.xpath(SETUP_BUTTON_XPATH)).size() > 0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SETUP_BUTTON_XPATH)));
        Assertions.assertTrue(setupButton.isDisplayed());
    }
}
