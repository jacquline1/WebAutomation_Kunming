package com.test.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class OpenBrowser {
    public static WebDriver openBrowser(String url){
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        //放大浏览器
        driver.manage().window().maximize();
        return driver;
    }
}
