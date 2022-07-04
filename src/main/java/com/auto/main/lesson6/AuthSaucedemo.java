package com.auto.main.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthSaucedemo extends BaseView {

    public AuthSaucedemo(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement pwdField;

    @Step("Клик логин с пустыми полями, проверить сообщение об ошибке")
    public void checkErrorMessage() {
        loginButton.click();
        assertThat(errorMessage.getText())
                .isEqualTo("Epic sadface: Username is required");
    }

    @Step("Логин валидные user/pwd")
    public void checkLogin(AuthUser user, boolean expectedMarketIsOpen) {
        userNameField.sendKeys(user.getUsername());
        pwdField.sendKeys(user.getPwd());
        loginButton.click();
        boolean actualMarketIsOpen = new Products(driver).getTitle().isDisplayed();
        assertThat(expectedMarketIsOpen).isEqualTo(actualMarketIsOpen);
    }

    @Step("Логин невалидные user/pwd")
    public void checkLoginError(AuthUser user, boolean expectedErrorIsDisplayed) {
        userNameField.sendKeys(user.getUsername());
        pwdField.sendKeys(user.getPwd());
        loginButton.click();
        boolean actualErrorIsDisplayed = errorMessage.isDisplayed();
        assertThat(expectedErrorIsDisplayed).isEqualTo(actualErrorIsDisplayed);
    }

    @Step("Логин")
    public Products login(String username, String pwd) {
        userNameField.sendKeys(username);
        pwdField.sendKeys(pwd);
        loginButton.click();
        return new Products(driver);
    }
}
