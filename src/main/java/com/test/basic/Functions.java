package com.test.basic;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Functions {
    WebDriver driver;
    WebElement element;
    public static void highlight(WebDriver diver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) diver;
        js.executeScript("element = arguments[0];" +
                "original_style = element.getAttribute('style');" +
                "element.setAttribute('style', original_style + \";" +
                "border: 2px solid red;\");" +
                "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", element);
    }
}
