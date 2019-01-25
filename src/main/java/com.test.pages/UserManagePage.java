package com.test.pages;

import com.test.basic.Functions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UserManagePage {
    public static WebElement element;
    // 切换 frame 进入用户管理页面
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
    //用户下拉列表中的某个项目
    public static WebElement itemOfUserDropList(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var itemOfUserDropList ="
                + "document.querySelectorAll('#user > ul > li:nth-child(10)')[0];" + "return itemOfUserDropList");
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
    public static void searchByClickDropList(WebDriver driver,WebElement element){
        element.click();
        Functions.highlight(driver,element);
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
    public static void selectDept(WebDriver driver,String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var dept ="
                + "document.querySelectorAll('select#in-department')[0];" + "return dept");
        Select select = new Select(element);
        select.selectByValue(value);
    }
    public static void selectRole(WebDriver driver,String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var role ="
                + "document.querySelectorAll('select#in-department')[0];" + "return role");
        Select select = new Select(element);
        select.selectByValue(value);
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
    public static void addUser(WebDriver driver,String realName,String userName,String dept,String role){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(realName(driver)));
        realName(driver).sendKeys(realName);
        wait.until(ExpectedConditions.visibilityOf(userName(driver)));
        userName(driver).sendKeys(userName);
        selectDept(driver,dept);
        selectRole(driver,role);
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
    public static WebElement totalRecord(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var totalRecord ="
                + "document.querySelectorAll('div.pull-left >span.pagination-info')[0];" + "return totalRecord");
        return element;
    }
    //编辑、添加用户/角色/部门的窗口标题
    public static WebElement windowTitle(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var windowTitle ="
                + "document.querySelectorAll('h4#modal_title')[0];" + "return windowTitle");
        return element;
    }
    public static void goToRoleManagePage(WebDriver driver){
        WebDriverWait wait= new WebDriverWait(driver,10);
        LeftNavigationPage.clickAuthManage(driver);
        wait.until(ExpectedConditions.visibilityOf(LeftNavigationPage.authManage_role(driver))).click();
        MiddleMapPage.switchFrame(driver);
    }
    //选择角色下拉列表
    public static WebElement roleDropList(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var roleDropList ="
                + "document.querySelectorAll('#search ul > li:nth-child(3) > input')[0];" + "return roleDropList");
        return element;
    }
    //角色下拉列表中的某个项目
    public static WebElement itemOfRoleList(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("var itemOfRoleList ="
                + "document.querySelectorAll('#Power > ul > li:nth-child(2)')[0];" + "return itemOfRoleList");
        return element;
    }

    //选择部门下拉列表
    public static WebElement deptDropList(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var deptDropList ="
                + "document.querySelectorAll('#search ul > li:nth-child(4) > input')[0];" + "return deptDropList");
        return element;
    }
    //添加角色窗口，角色名
    public static WebElement roleName(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var roleName ="
                + "document.querySelectorAll('input#addPowerEdit')[0];" + "return roleName");
        return element;
    }
    //添加角色，数据浏览复选框
    public static WebElement checkboxDataBrowse(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var checkboxDataBrowse ="
                + "document.querySelectorAll('div:nth-child(1) > h1 > input[type=\"checkbox\"]')[0];" + "return checkboxDataBrowse");
        return element;
    }
    //添加角色，数据浏览-> 道路设施数据复选框
    public static WebElement checkboxDataBrowse_1(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var checkboxDataBrowse_1 ="
                + "document.querySelectorAll('div:nth-child(1) > div > h2:nth-child(1) > input[type=\"checkbox\"]')[0];" + "return checkboxDataBrowse_1");
        return element;
    }
    //添加角色，查询统计复选框
    public static WebElement checkboxQuery(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var checkboxQuery ="
                + "document.querySelectorAll('div:nth-child(2) > h1 > input[type=\"checkbox\"]')[0];" + "return checkboxQuery");
        return element;
    }
    public static void addRole(WebDriver driver,String roleName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        UserManagePage.addUserBtn(driver).click();
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.windowTitle(driver)));
        Assert.assertEquals("添加角色",UserManagePage.windowTitle(driver).getText());
        UserManagePage.roleName(driver).sendKeys(roleName);
        UserManagePage.checkboxDataBrowse(driver).click();
        UserManagePage.saveBtn(driver).click();
    }
    public static void goToDeptManagePage(WebDriver driver){
        WebDriverWait wait= new WebDriverWait(driver,10);
        LeftNavigationPage.clickAuthManage(driver);
        wait.until(ExpectedConditions.visibilityOf(LeftNavigationPage.authManage_dept(driver))).click();
        MiddleMapPage.switchFrame(driver);

    }
    //部门下拉列表中的某个项目
    public static WebElement itemOfDeptList(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("var itemOfRoleList ="
                + "document.querySelectorAll('#department> ul > li:nth-child(2)')[0];" + "return itemOfRoleList");
        return element;
    }
    //添加部门窗口，部门名称
    public static WebElement deptName(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var deptName ="
                + "document.querySelectorAll('input#depa-name')[0];" + "return deptName");
        return element;
    }
    //添加部门窗口，部门名称
    public static WebElement deptNameError(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var deptNameError ="
                + "document.querySelectorAll('label#depa-name-error')[0];" + "return deptNameError");
        return element;
    }

    //添加部门窗口，部门负责人
    public static WebElement deptResponser(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var deptResponser ="
                + "document.querySelectorAll('input#depa-responsible')[0];" + "return deptResponser");
        return element;
    }
    //添加部门窗口，上级部门
    public static WebElement deptParent(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var deptParent ="
                + "document.querySelectorAll('select#parent-depa')[0];" + "return deptParent");
        return element;
    }
    public static void selectDeptParent(WebDriver driver,String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Select select = new Select(deptParent(driver));
        select.selectByValue(value);
    }
    public static void addDept(WebDriver driver,String deptName,String responser,String parentValue){
        deptName(driver).clear();
        deptName(driver).sendKeys(deptName);
        deptResponser(driver).clear();
        deptResponser(driver).sendKeys(responser);
        selectDeptParent(driver,parentValue);
        saveBtn(driver).click();
    }










}
