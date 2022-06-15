package com.auto.test.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Market {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
       /*
       Тест фильтра "ozon.ru"
       */
        driver.get("https://www.ozon.ru/category/videokarty-15721/?category_was_predicted=true&from_global=true&text=%D0%B2%D0%B8%D0%B4%D0%B5%D0%BE%D0%BA%D0%B0%D1%80%D1%82%D0%B0");
        driver.findElement(By.xpath("//span[contains(text(), 'ASUS') and @class]/ancestor::label/input/following-sibling::div[@class='ui-x2']")).click();
        //driver.findElement(By.xpath("//span[@class='vx3' and contains(text(), 'Новинки')]/ancestor::label/input/following-sibling::div[@class='ui-x2']")).click(); ~устарело
        driver.findElement(By.xpath("//span[contains(text(), 'GeForce RTX 3060 Ti')]/ancestor::label/input/following-sibling::div[@class='ui-x2']")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'GeForce RTX 3080')]/ancestor::label/input/following-sibling::div[@class='ui-x2']")).click();
        Thread.sleep(3000);
        driver.quit();


    }
}
