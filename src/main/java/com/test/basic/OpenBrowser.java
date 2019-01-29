package com.test.basic;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class OpenBrowser {
    public  static WebDriver openBrowser(String url){
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        return driver;
    }


}
