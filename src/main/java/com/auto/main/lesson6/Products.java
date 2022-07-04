package com.auto.main.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Products extends BaseView {

    public Products(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> productItems;

    @FindBy(xpath = "//span[@class='title' and .='Products']")
    private WebElement title;

    public WebElement getTitle() {
        return title;
    }

    @Step("Клик на товар по названию")
    public ProductItem clickOnProductByName(String name) {
        productItems.stream()
                .filter(item -> item.getText().contains(name))
                .findFirst()
                .get()
                .click();
        return new ProductItem(driver);
    }
}
