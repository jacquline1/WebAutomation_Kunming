package com.test.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DateUpdateWindowPage {
    public static WebElement element;
    //数据上传，选择文件区域
    public static WebElement selectUpload(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var selectUpload ="
     //      + "document.getElementById('select-uploadfile');" + "return selectUpload");
                   + "document.querySelectorAll('div#select-uploadfile.el-upload-dragger')[0];" + "return selectUpload");
        return element;
    }
    //
    //导入数据按钮
    public static WebElement importBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var importBtn ="
                + "document.querySelectorAll('button#btn_import')[0];" + "return importBtn");
        return element;
    }

}
