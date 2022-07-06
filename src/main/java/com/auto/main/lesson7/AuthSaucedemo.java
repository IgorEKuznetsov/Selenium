package com.auto.main.lesson7;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthSaucedemo  {


    private SelenideElement loginButton = $(By.id("login-button"));

    private SelenideElement errorMessage = $(By.xpath("//h3[@data-test='error']"));

    private SelenideElement userNameField= $(By.xpath("//input[@placeholder='Username']"));

    private SelenideElement pwdField = $(By.xpath("//input[@placeholder='Password']"));


    public void checkErrorMessage() {
        loginButton.click();
        assertThat(errorMessage.getText())
                .isEqualTo("Epic sadface: Username is required");
    }


    public void checkLogin(AuthUser user, boolean expectedMarketIsOpen) {
        userNameField.sendKeys(user.getUsername());
        pwdField.sendKeys(user.getPwd());
        loginButton.click();
        boolean actualMarketIsOpen = new Products().getTitle().isDisplayed();
        assertThat(expectedMarketIsOpen).isEqualTo(actualMarketIsOpen);
    }


    public void checkLoginError(AuthUser user, boolean expectedErrorIsDisplayed) {
        userNameField.sendKeys(user.getUsername());
        pwdField.sendKeys(user.getPwd());
        loginButton.click();
        boolean actualErrorIsDisplayed = errorMessage.isDisplayed();
        assertThat(expectedErrorIsDisplayed).isEqualTo(actualErrorIsDisplayed);
    }


    public Products login(String username, String pwd) {
        userNameField.sendKeys(username);
        pwdField.sendKeys(pwd);
        loginButton.click();
        return page(Products.class);
    }
}
