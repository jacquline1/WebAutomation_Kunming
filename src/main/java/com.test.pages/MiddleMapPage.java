package com.test.pages;

import com.test.basic.Functions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;

public class MiddleMapPage {
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
    //右侧，页面标题
    public static WebElement pageTitle(WebDriver driver){
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
    //定位后在地图上显示的位置
    public static WebElement locatedInMap(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var locatedInMap ="
                + "document.querySelectorAll('#map_graphics_layer > path')[0];" + "return locatedInMap");
       // Functions.highlight(driver,element);
        return element;
    }
    //查询按钮
    public static WebElement queryBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var queryBtn ="
                + "document.querySelectorAll('button#query-btn')[0];" + "return queryBtn");
        Functions.highlight(driver,element);
        return element;
    }
    //点击查询按钮后,道路等级
    public static WebElement roadLevel(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var roadLevel ="
                + "document.querySelectorAll('select#lang1')[0];" + "return roadLevel");
        Functions.highlight(driver,element);
        return element;
    }
    //道路等级菜单下的某一项
    public static WebElement oneItemOfRoadLevel(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var oneItemOfRoadLevel ="
                + "document.querySelectorAll('#ck1 > li:nth-child(1) > label')[0];" + "return oneItemOfRoadLevel");
        Functions.highlight(driver,element);
        return element;
    }
    //点击查询按钮后，车道数1
    public static WebElement cheDaoShu1(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var cheDaoShu1 ="
                + "document.querySelectorAll('input#s_lane')[0];" + "return cheDaoShu1");
        Functions.highlight(driver,element);
        return element;
    }
    //点击查询按钮后，车道数2
    public static WebElement cheDaoShu2(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var cheDaoShu2 ="
                + "document.querySelectorAll('input#m_lane')[0];" + "return cheDaoShu2");
        Functions.highlight(driver,element);
        return element;
    }
    //点击查询按钮后，路段长度1
    public static WebElement roadLong1(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var roadLong1 ="
                + "document.querySelectorAll('input#s_link_len')[0];" + "return roadLong1");
        Functions.highlight(driver,element);
        return element;
    }
    //点击查询按钮后，路段长度2
    public static WebElement roadLong2(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var roadLong2 ="
                + "document.querySelectorAll('input#m_link_len')[0];" + "return roadLong2");
        Functions.highlight(driver,element);
        return element;
    }
    //点击查询按钮后，定位按钮
    public static WebElement loateBtnInTable(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var loateBtnInTable ="
                + "document.querySelectorAll('#table > tbody > tr:nth-child(1) > td:nth-child(8) > a')[0];" + "return loateBtnInTable");
    //   Functions.highlight(driver,element);
        return element;
    }
    //点击查询按钮后，弹出窗口上的查询按钮
    public static WebElement queryBtnOfOpenedWind(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var queryBtnOfOpenedWind ="
                + "document.querySelectorAll('div.btn#query')[0];" + "return queryBtnOfOpenedWind");
        Functions.highlight(driver,element);
        return element;
    }
    public static void inputRoadNameAndLocate(WebDriver driver,String roadName){
        MiddleMapPage.inputRoadName(driver,roadName);
        MiddleMapPage.locateBtn(driver).click();
    }
    //数据版本
    public static WebElement dataType(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var locatedInMap ="
                + "document.querySelectorAll('select#text_data_type')[0];" + "return locatedInMap");
//         Functions.highlight(driver,element);
        return element;
    }
    //从数据版本下拉列表中选择项目
    public static String  selectFromDataTypeList(WebDriver driver,String value){
        Select select = new Select(dataType(driver));
        select.selectByValue(value);
        return select.getFirstSelectedOption().getText();
    }
    // 图层管理
    public static WebElement layerManagement(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var layerManagement ="
                + "document.querySelectorAll('#layer > ul > li:nth-child(1)')[0];" + "return layerManagement");
        Functions.highlight(driver,element);
        return element;
    }
    //图层菜单上第一个开关
    public static WebElement layerMenu1(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var layerMenu1 ="
                + "document.querySelectorAll('#layer-management-main > ul > li:nth-child(1) > div')[0];" + "return layerMenu1");
        Functions.highlight(driver,element);
        return element;
    }
    //图层关闭按钮
    public static WebElement layerClose(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var layerClose ="
                + "document.querySelectorAll('span#layer-management-close')[0];" + "return layerClose");
        Functions.highlight(driver,element);
        return element;
    }
    //面积测量
    public static WebElement areaMeasure(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var areaMeasure ="
                + "document.querySelectorAll('li#brightdata_measure')[0];" + "return areaMeasure");
        Functions.highlight(driver,element);
        return element;
    }
    //距离测量
    public static WebElement distanceMeasure(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var distanceMeasure ="
                + "document.querySelectorAll('li#brightdata_measure')[1];" + "return distanceMeasure");
        Functions.highlight(driver,element);
        return element;
    }
    //放大
    public static WebElement max(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var max ="
                + "document.querySelectorAll('#layer > ul > li:nth-child(4)')[0];" + "return max");
        Functions.highlight(driver,element);
        return element;
    }
    //缩小
    public static WebElement min(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var min ="
                + "document.querySelectorAll('#layer > ul > li:nth-child(5)')[0];" + "return min");
        Functions.highlight(driver,element);
        return element;
    }
    //面积,长度测量窗口标题
    public static WebElement measureTitle(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var measureTitle ="
                + "document.querySelectorAll('.esriPopupWrapper > div:nth-child(1) > div > div.title')[0];" + "return measureTitle");
        Functions.highlight(driver,element);
        return element;
    }
    public static void dragAndDropInMap(WebDriver driver) throws IOException {
        Runtime.getRuntime().exec("D:\\dragAndDropOnMap.exe");
        Functions.highlight(driver,MiddleMapPage.measureTitle(driver));
    }


}
