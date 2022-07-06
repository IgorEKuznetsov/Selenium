package com.auto.main.lesson7;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;



import org.openqa.selenium.*;


import java.util.Random;

import static com.codeborne.selenide.Selenide.*;


public class WInkIndex {

    private ElementsCollection filmList = $$(By.xpath("//h2[contains(text(), 'Кинопоиск')]/.." +
            "/following::div[contains(@data-analytic, '143676210')]/ancestor::div[@class='swiper-wrapper']/div"));

    private SelenideElement dropdownButton = $(By.xpath("//button[contains(@class, 'item_dropdown')]"));

    public WinkFilm clickOnRandomElementFromList() {
        while (filmList.size() == 0) {
            actions().scrollByAmount(0, 2500).build().perform();
        }
        actions().scrollToElement(filmList.get(1)).build().perform();
        Random random = new Random();
        int randomIndex = random.nextInt(5);
        filmList.get(randomIndex).click();
        return page(WinkFilm.class);
    }


    public void clickItem(String itemXpath) {
        dropdownButton.hover();
        $(By.xpath(itemXpath)).click();
    }

    public void checkHeader(String headerXpath){
        $(By.xpath(headerXpath)).shouldBe(Condition.visible);
    }

}

