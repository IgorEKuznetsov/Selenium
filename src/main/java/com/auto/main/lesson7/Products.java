package com.auto.main.lesson7;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class Products  {

    private ElementsCollection productItems = $$(By.xpath("//div[@class='inventory_item_name']"));
    private SelenideElement title = $(By.xpath("//span[@class='title' and .='Products']"));

    public WebElement getTitle() {
        return title;
    }

    public ProductItem clickOnProductByName(String name) {
        productItems.findBy(Condition.text(name)).click();
        return page(ProductItem.class);
    }
}
