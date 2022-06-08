package com.auto.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;


public class Start {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
/*
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        ((JavascriptExecutor)driver).executeScript("window.alert(\"Test!\");");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        driver.switchTo().newWindow(WindowType.TAB);

 */
        WebDriver driver = new ChromeDriver();
        driver.get("https://market.yandex.ru");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(2000);
        driver.manage().deleteAllCookies();
        Cookie cookie = new Cookie("parent_reqid_seq", "1654721223095%2F7630e029e885bccd9374b4cef5e00500%2C1654721822607%2F819a7f85d18053ede24870f2f5e00500");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='header-search']")).sendKeys("test");
        driver.findElement(By.xpath("//button[@data-r='search-button']")).click();

        Thread.sleep(2000);

        driver.quit();
    }
}
