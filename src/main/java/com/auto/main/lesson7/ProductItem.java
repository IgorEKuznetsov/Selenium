package com.auto.main.lesson7;


import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductItem  {

    private SelenideElement addToCartButton = $(By.xpath("//button[contains(@data-test, 'add')]"));
    private SelenideElement removeButton = $(By.xpath("//button[contains(text(), 'Remove')]"));
    private SelenideElement price = $(By.xpath("//div[@class='inventory_details_price']"));

    public ProductItem clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    public void checkSumAndRemoveButton(String summa) {
        Assertions.assertAll(
                () -> removeButton.shouldBe(visible),
                () -> price.shouldHave(text(summa))
        );
    }
}
