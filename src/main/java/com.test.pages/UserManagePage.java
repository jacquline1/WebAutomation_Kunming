package com.test.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserManagePage {
    public static WebElement element;
    public static void goToUserManagePage(WebDriver driver){
        LeftNavigationPage.clickAuthManage(driver);
        LeftNavigationPage.authManage_user(driver).click();
        MiddleMapPage.switchFrame(driver);
    }
    //查询内容输入框
    public static WebElement searchInputBox(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var searchInputBox ="
                + "document.querySelectorAll('ul > li:nth-child(1) > input')[0];" + "return searchInputBox");
        return element;
    }
    //选择用户下拉列表
    public static WebElement userDropList(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var userDropList ="
                + "document.querySelectorAll('#search ul > li:nth-child(2) > input')[0];" + "return userDropList");
        return element;
    }
    //查询按钮
    public static WebElement queryBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var queryBtn ="
                + "document.querySelectorAll('ul > li .search_submit')[0];" + "return queryBtn");
        return element;
    }
    public static void searchByKeyword(WebDriver driver,String keyword){
        UserManagePage.searchInputBox(driver).sendKeys(keyword);
        UserManagePage.queryBtn(driver).click();

    }
    public static void searchBySelectfromList(WebDriver driver,String keyword){
        UserManagePage.searchInputBox(driver).sendKeys(keyword);
        UserManagePage.queryBtn(driver).click();

    }


    public static WebElement addUserBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
         element = (WebElement) js.executeScript("var addUserBtn ="
                     + "document.getElementById('add');" + "return addUserBtn");
         return element;
    }

    public static WebElement realName(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var realName ="
                + "document.querySelectorAll('input#full-name')[0];" + "return realName");
        return element;
    }
    public static WebElement userName(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var userName ="
                + "document.querySelectorAll('input#user-name')[0];" + "return userName");
        return element;
    }
    public static void selectDept(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var dept ="
                + "document.querySelectorAll('select#in-department')[0];" + "return dept");
        Select select = new Select(element);
        select.selectByValue("1");
    }
    public static void selectRole(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var role ="
                + "document.querySelectorAll('select#in-department')[0];" + "return role");
        Select select = new Select(element);
        select.selectByValue("1");
    }
    public static WebElement saveBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var saveBtn ="
                + "document.querySelectorAll('button#submit')[0];" + "return saveBtn");
        return element;
    }
    public static WebElement cancelBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var cancelBtn ="
                + "document.querySelectorAll('button#cancel')[0];" + "return cancelBtn");
        return element;
    }
    public static void addUser(WebDriver driver,String realName,String userName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(realName(driver)));
        realName(driver).sendKeys(realName);
        wait.until(ExpectedConditions.visibilityOf(userName(driver)));
        userName(driver).sendKeys(userName);
        selectDept(driver);
        selectRole(driver);
        UserManagePage.saveBtn(driver).click();
    }
    public static WebElement fullNameError(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var fullNameError ="
                + "document.querySelectorAll('label#full-name-error')[0];" + "return fullNameError");
        return element;
    }
    public static WebElement userNameError(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var userNameError ="
                + "document.querySelectorAll('label#user-name-error')[0];" + "return userNameError");
        return element;
    }
    public static WebElement closeBtn(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var closeBtn ="
                + "document.querySelectorAll('#content1 > div.modal-header > button')[0];" + "return closeBtn");
        return element;
    }




}
