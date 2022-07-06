package com.auto.main.lesson7;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;

public class WinkFilm {
    private SelenideElement setupButton = $(By.xpath("//span[contains(text(), 'Установить')]"));
    private SelenideElement loadButton = $(By.xpath("//button[@data-test='media_item-button-download']//span[contains(text(), 'Загрузить')]"));

    public void checkSetupAppButtonIsDisplayed() {
        loadButton.click();
        setupButton.shouldBe(Condition.visible);
    }
}
