package com.test.basic;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Functions {
    static WebDriver driver;
    WebElement element;
    public static void highlight(WebDriver diver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) diver;
        js.executeScript("element = arguments[0];" +
                "original_style = element.getAttribute('style');" +
                "element.setAttribute('style', original_style + \";" +
                "border: 2px solid red;\");" +
                "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", element);
    }
    public static void dismissAlert(WebDriver driver) throws InterruptedException {
        Thread.sleep(10);
        Alert alert = driver.switchTo().alert();
        Thread.sleep(10);
        alert.dismiss();
        Thread.sleep(10);
    }
    public static void acceptAlert(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        Thread.sleep(2000);
        alert.accept();
        Thread.sleep(2000);
    }

//    public static void alertAccept1() {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        try {
//            Alert alert = wait.until(new ExpectedCondition<Alert>() {
//                public Alert apply(WebDriver driver) {
//                    try {
//                        return driver.switchTo().alert();
//                    } catch (NoAlertPresentException e) {
//                        return null;
//                    }
//                }
//            });
//            alert.accept();
//        } catch (NullPointerException e) {
//            /* Ignore */
//            System.out.println("ff2 nullpoint");
//        }
//    }
    
}
