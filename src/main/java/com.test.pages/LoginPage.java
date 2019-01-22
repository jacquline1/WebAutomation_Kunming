package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    public static WebElement element;
    public static WebElement userName (WebDriver driver){
        element = driver.findElement(By.id("user"));
        return element;
    }
    public static WebElement passWord(WebDriver driver){
        element = driver.findElement(By.id("pwd"));
        return element;
    }
    public static WebElement loginButton(WebDriver driver){
        element = driver.findElement(By.id("btn_sub"));
        return element;
    }

}