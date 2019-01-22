package com.test.pages;

import com.test.basic.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MiddleMapPage {
    WebDriver driver;
    public static WebElement  element;
    //右侧 frame 区域
    public static WebElement rightFrame(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var rightFrame ="
                + "document.getElementById('right_content');" + "return rightFrame");
        return element;
    }
    //切换至右侧 frame 区域
    public static void switchFrame(WebDriver driver){
        driver.switchTo().frame(rightFrame(driver));
    }
    //右侧，道路设施数据标题
    public static WebElement roadTitle(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var roadTitle ="
                + "document.querySelectorAll('#full_box > div.top_div > a')[0];" + "return roadTitle");
        return element;
    }
    //输入路名文本框
    public static WebElement roadName(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var roadName ="
                + "document.querySelectorAll('input#input_roadname')[0];" + "return roadName");
        Functions.highlight(driver,element);
        return element;
    }
    //输入路名
    public static void inputRoadName(WebDriver driver,String name){
        MiddleMapPage.roadName(driver).sendKeys(name);
    }
    //定位按钮
    public static WebElement locateBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var locateBtn ="
                + "document.querySelectorAll('button#location-btn')[0];" + "return locateBtn");
        Functions.highlight(driver,element);
        return element;
    }
    //定位到的位置
    public static WebElement locatedInMap(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var locatedInMap ="
                + "document.querySelectorAll('#map_graphics_layer > path')[0];" + "return locatedInMap");
       // Functions.highlight(driver,element);
        return element;
    }


}
