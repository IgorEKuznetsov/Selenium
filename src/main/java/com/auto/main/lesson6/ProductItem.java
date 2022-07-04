package com.auto.main.lesson6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductItem extends BaseView {
    public ProductItem(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@data-test, 'add')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[contains(text(), 'Remove')]")
    private WebElement removeButton;

    @FindBy(xpath = "//div[@class='inventory_details_price']")
    private WebElement sum;

    @Step("Клик 'Add to cart'")
    public ProductItem clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Проверить сумму и кнопку \"Remove\"")
    public void checkSumAndRemoveButton(String summa) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(summa, sum.getText()),
                () -> Assertions.assertTrue(removeButton.isDisplayed())
        );
    }
}
