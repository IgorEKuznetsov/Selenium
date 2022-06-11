package com.auto.test.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
       /*
       Тест аутентификации "saucedemo.com"
       */

public class Auth {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User("testUser", "12345"),
                new User("standard_user", ""),
                new User("locked_out_user", "locked_out_user"),
                new User("", "secret_sauce"),
                new User("performance_glitch_user", "secret_sauce"), //right user
                new User("standard_user", "secret_sauce1")));

        driver.get("https://www.saucedemo.com/");
        for (User user : userList) {
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(user.getUsername());
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(user.getPwd());
            driver.findElement(By.id("login-button")).click();
            boolean isMarketOpen = driver.findElements(By.xpath("//*[contains(text(), 'Sauce Labs Backpack')]")).stream().count() > 0;
            if (!isMarketOpen) {
                driver.navigate().refresh();
            } else {
                driver.findElement(By.xpath("//*[contains(text(), 'Sauce Labs Backpack')]")).click();
                driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));
                driver.findElement(By.id("remove-sauce-labs-backpack")).click();
                driver.quit();
                break;
            }

        }
    }


    public static class User {
        private String username;
        private String pwd;

        public User(String username, String pwd) {
            this.username = username;
            this.pwd = pwd;
        }

        public String getUsername() {
            return username;
        }

        public String getPwd() {
            return pwd;
        }
    }
}
