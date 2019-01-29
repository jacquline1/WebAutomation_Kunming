package com.test.testSuite;

import com.test.actions.LoginAction;
import com.test.basic.Functions;
import com.test.basic.Table;
import com.test.pages.DataUpdateWindowPage;
import com.test.pages.LeftNavigationPage;
import com.test.pages.MiddleMapPage;
import com.test.pages.UserManagePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
public class TestCases {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver,20);
    @BeforeClass
    public void setUP() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://192.168.10.35:8012/index.html");
        new LoginAction(driver).Login("chen","000000");
        Thread.sleep(1000);
    }
    /**
     * 数据浏览,输入路名并定位
     */
    @Test
    public void testInputRoadNameAndLocate() throws InterruptedException {
        MiddleMapPage.switchFrame(driver);
        Assert.assertEquals(MiddleMapPage.pageTitle(driver).getText(),"道路设施数据");
        MiddleMapPage.inputRoadNameAndLocate(driver,"吴井路");
        Thread.sleep(3000);
        Assert.assertTrue(MiddleMapPage.locatedInMap(driver).isDisplayed());
        driver.switchTo().defaultContent();
    }

    /**
     * 宏观仿真路网，选择数据版本
     * 0：现状路网  1：规划路网
     */
    @Test
    public void testSelectDataType_HongGuan(){
        LeftNavigationPage.dataBrowse_hguan(driver).click();
        MiddleMapPage.switchFrame(driver);
        String actualText = MiddleMapPage.selectFromDataTypeList(driver,"1");
        Assert.assertEquals(actualText,"规划路网");
       //图层操作
       MiddleMapPage.layerManagement(driver).click();
       wait.until(ExpectedConditions.visibilityOf(MiddleMapPage.layerMenu1(driver))).click();
       MiddleMapPage.layerClose(driver).click();
        driver.switchTo().defaultContent();
    }
    @Test
    public void testDataBrowseFolder(){
        //数据浏览 -》道路设施数据
        LeftNavigationPage.dataBrowse_road(driver).click();
        System.out.println("dataBrowse_road text:" + LeftNavigationPage.dataBrowse_road(driver).getText());
        // //数据浏览 -》宏观仿真路网
        LeftNavigationPage.dataBrowse_hguan(driver).click();
        System.out.println("dataBrowse_hguan text:" + LeftNavigationPage.dataBrowse_hguan(driver).getText());
        //数据浏览 -》中观仿真路网
        LeftNavigationPage.dataBrowse_zhguan(driver).click();
        System.out.println("dataBrowse_zhguan text:" + LeftNavigationPage.dataBrowse_zhguan(driver).getText());
        //数据浏览 -》公交设施数据
        wait.until(ExpectedConditions.visibilityOf(LeftNavigationPage.dataBrowse_gjiao(driver)));
        LeftNavigationPage.dataBrowse_gjiao(driver).click();
        System.out.println("dataBrowse_gjiao text:" + LeftNavigationPage.dataBrowse_gjiao(driver).getText());
        driver.switchTo().defaultContent();
    }

    /**
     * 单体属性查询页面，选择数据类型版本后进行面积测量
     */
    @Test
    public void testSelectDataTypeAndAreaMeasureInDataQueryFolder() throws InterruptedException, IOException {
        //查询统计,点击.
        Thread.sleep(10);
        LeftNavigationPage.clickQuery(driver);
        //查询统计 ->> 单体属性查询，选择数据版本
        LeftNavigationPage.query_danti(driver).click();
        MiddleMapPage.switchFrame(driver);
        MiddleMapPage.selectFromDataTypeList(driver,"2");
        //面积测量
        MiddleMapPage.areaMeasure(driver).click();
        MiddleMapPage.dragAndDropInMap(driver);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(MiddleMapPage.measureTitle(driver))).isDisplayed());
        driver.switchTo().defaultContent();
    }
    /**
     * 条件查询，输入查询条件后，在查询结果中定位
     */
    @Test
    public void testConditionQueryAndLocate() throws InterruptedException {
        LeftNavigationPage.clickQuery(driver);
        wait.until(ExpectedConditions.visibilityOf(LeftNavigationPage.query_tiaojian(driver))).click();
        MiddleMapPage.switchFrame(driver);
        MiddleMapPage.queryBtn(driver).click();
        //选择道路等级下的复选框
        MiddleMapPage.roadLevel(driver).click();
        MiddleMapPage.oneItemOfRoadLevel(driver).click();
        //输入车道数
        MiddleMapPage.cheDaoShu1(driver).sendKeys("2");
        MiddleMapPage.cheDaoShu2(driver).sendKeys("4");
        //输入路段长度
        MiddleMapPage.roadLong1(driver).sendKeys("1000");
        MiddleMapPage.roadLong2(driver).sendKeys("2000");
        //点击查询
        MiddleMapPage.queryBtnOfOpenedWind(driver).click();
        wait.until(ExpectedConditions.visibilityOf(MiddleMapPage.loateBtnInTable(driver)));
        MiddleMapPage.loateBtnInTable(driver).click();
        Thread.sleep(3000);
        Assert.assertTrue(MiddleMapPage.locatedInMap(driver).isDisplayed());
        driver.switchTo().defaultContent();
    }
    /**
     * 上传数据更新文件
     */
    @Test
    public void testDataUpdate() throws IOException {
        //数据更新,点击展开菜单
        LeftNavigationPage.clickDataUpdate(driver);
        //数据更新 -》数据更新
        LeftNavigationPage.dataUpdate_shuju(driver).click();
        Functions.highlight(driver,LeftNavigationPage.dataUpdate_shuju(driver));
        Assert.assertEquals("数据更新",LeftNavigationPage.dataUpdate_shuju(driver).getText());
        //数据更新 -》数据更新 -> 数据上传
        wait.until(ExpectedConditions.elementToBeClickable(DataUpdateWindowPage.selectUpload(driver)));
        DataUpdateWindowPage.selectUpload(driver).click();
        Runtime.getRuntime().exec("D:\\upLoad.exe");
        DataUpdateWindowPage.importBtn(driver).click();
    }
    /**
     *全部导出页面，选择数据类型版本后进行长度测量
     */
    @Test
    public void testSelectDataTypeAndMeasureInDataExport() throws IOException {
        //自定义范围导出界面，选择数据类型
        LeftNavigationPage.clickDataExport(driver);
        wait.until(ExpectedConditions.visibilityOf(LeftNavigationPage.dataExport_all(driver))).click();
        MiddleMapPage.switchFrame(driver);
        MiddleMapPage.selectFromDataTypeList(driver,"1");
        //长度测量
        MiddleMapPage.distanceMeasure(driver).click();
        MiddleMapPage.dragAndDropInMap(driver);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(MiddleMapPage.measureTitle(driver))).isDisplayed());
        driver.switchTo().defaultContent();
    }
    /**
     * 数据导出,输入路名并定位
     */
    @Test
    public void testInputRoadNameAndLocateInDataExport(){
        LeftNavigationPage.clickDataExport(driver);
        wait.until(ExpectedConditions.visibilityOf(LeftNavigationPage.dataExport_custom(driver))).click();
        MiddleMapPage.switchFrame(driver);
        Assert.assertEquals(MiddleMapPage.pageTitle(driver).getText(),"自定义范围导出");
        MiddleMapPage.inputRoadNameAndLocate(driver,"吴井路");
        driver.switchTo().defaultContent();
    }
    /**
     * 添加用户，验证必填字段不能为空
     */
    @Test
    public void testAddUserFiledNotNull(){
        UserManagePage.goToUserManagePage(driver);
        UserManagePage.addUserBtn(driver).click();
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.saveBtn(driver)));
        UserManagePage.saveBtn(driver).click();
        Assert.assertEquals(UserManagePage.fullNameError(driver).getText(),"真实姓名不能为空!");
        Assert.assertEquals(UserManagePage.userNameError(driver).getText(),"用户名不能为空!");
        UserManagePage.cancelBtn(driver).click();
        driver.switchTo().defaultContent();
    }
    /**
     * 添加用户，验证必填字段的字符长度
     */
    @Test
    public void testAddUserFieldLength(){
        UserManagePage.goToUserManagePage(driver);
        UserManagePage.addUserBtn(driver).click();
        UserManagePage.addUser(driver,"a","a","1","1");
        Assert.assertEquals("真实姓名的最小长度为2个字符, 最大长度为10个字符!",UserManagePage.fullNameError(driver).getText());
        Assert.assertEquals("用户名的最小长度为4个字符, 最大长度为10个字符!",UserManagePage.userNameError(driver).getText());
        wait.until(ExpectedConditions.elementToBeClickable(UserManagePage.closeBtn(driver))).click();
        driver.switchTo().defaultContent();
    }
    /**
     * 成功添加用户
     */
    @Test
    @Parameters({"realName","userName"})
    public void testAddUserPass(String realName,String userName) throws InterruptedException {
        UserManagePage.goToUserManagePage(driver);
        UserManagePage.addUserBtn(driver).click();
        UserManagePage.addUser(driver,realName,userName,"1","1");
        Functions.acceptAlert(driver);
        //验证添加的记录可搜索到
        UserManagePage.searchByKeyword(driver,"test1");
        Assert.assertEquals("显示第 1 到第 1 条记录，总共 1 条记录",UserManagePage.totalRecord(driver).getText());
        driver.switchTo().defaultContent();
    }
    /**
     * 按输入的内容查询用户
     */
    @Test
    public void testInputKeywordAndSearchUser(){
        UserManagePage.goToUserManagePage(driver);
        UserManagePage.searchByKeyword(driver,"test1");
        driver.switchTo().defaultContent();
    }
    /**
     * 从用户下拉列表中选择，查询用户
     */
    @Test
    public void testSelectFromDropListAndSearchUser() throws InterruptedException {
        UserManagePage.goToUserManagePage(driver);
        UserManagePage.userDropList(driver).click();
        Functions.highlight(driver,UserManagePage.userDropList(driver));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("var user ="
                + "document.querySelectorAll('#user > ul > li:nth-child(10)')[0];" + "return user");
        element.click();
        Thread.sleep(10);
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.totalRecord(driver)));
        Assert.assertEquals("显示第 1 到第 1 条记录，总共 1 条记录",UserManagePage.totalRecord(driver).getText());
        driver.switchTo().defaultContent();
    }

    /**
     * 编辑用户
     */
    @Test
    public void testEditUser() throws InterruptedException, AWTException {
        UserManagePage.goToUserManagePage(driver);
        //定位编辑按钮：表格的第 9 行第 6 列的单元格内
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("var editBtn ="
                + "document.querySelectorAll('tr:nth-child(9) > td:nth-child(6) > a.edit.ml10')[0];" + "return editBtn");
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        // 编辑用户窗口正常打开
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.windowTitle(driver)));
        Assert.assertEquals("编辑用户",UserManagePage.windowTitle(driver).getText());
        //修改用户信息
        UserManagePage.realName(driver).clear();
        UserManagePage.realName(driver).sendKeys("update4");
        UserManagePage.selectRole(driver,"0");
        UserManagePage.selectDept(driver,"0");
        UserManagePage.saveBtn(driver).click();
        Functions.acceptAlertViaRobot();
        driver.switchTo().defaultContent();
    }
    /**
     * 删除用户
     */
    @Test
    public void testDeleteUser() throws InterruptedException {
        UserManagePage.goToUserManagePage(driver);
        //删除前的用户总数
        WebElement table = driver.findElement(By.id("table"));
        int beforeDelete = new Table(table).getRowCount();
        //定位删除按钮：表格的第 9 行第 6 列的单元格内
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement removeBtn = (WebElement) js.executeScript("var removeBtn ="
                + "document.querySelectorAll('tr:nth-child(10) > td:nth-child(6) > a.remove.ml10')[0];" + "return removeBtn");
        // 获得要删除的用户名
      //  String toDelete = new Table(table).getCellText("//*[@id='table']/tbody",10,2);
        String toDelete = driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[10]/td[2]")).getText();
        System.out.println("要删除的是：" + toDelete);
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        // 取消删除
        Functions.dismissAlert(driver);
        //确认删除
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        Functions.acceptAlert(driver);
        Functions.acceptAlert(driver);
        //删除后的用户总数
//        int afterDelete = new Table(table).getRowCount();
//////        Assert.assertEquals(afterDelete,beforeDelete-1);
        UserManagePage.searchByKeyword(driver,toDelete);
        Assert.assertEquals("没有找到匹配的记录",UserManagePage.noResult(driver).getText());
        driver.switchTo().defaultContent();
    }

    /**
     *添加角色
     */
    @Test
    public void testAddRolePass() throws InterruptedException {
        UserManagePage.goToRoleManagePage(driver);
        UserManagePage.addRole(driver,"测试角色0");
        Functions.acceptAlert(driver);
        driver.switchTo().defaultContent();
    }
    /**
     * 按输入的关键字，查询角色
     */
    @Test
    public void testInputKeywordAndSearchRole(){
        UserManagePage.goToRoleManagePage(driver);
        UserManagePage.searchByKeyword(driver,"测试角色0");
        driver.switchTo().defaultContent();
    }
    /**
     * 从下拉列表中选择，查询角色
     */
    @Test
    public void testSelectFromDropListAndSearchRole(){
        UserManagePage.goToRoleManagePage(driver);
        UserManagePage.roleDropList(driver).click();
        Functions.highlight(driver,UserManagePage.roleDropList(driver));
        UserManagePage.searchByClickDropList(driver,UserManagePage.itemOfRoleList(driver));
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.totalRecord(driver)));
        Assert.assertEquals("显示第 1 到第 1 条记录，总共 1 条记录",UserManagePage.totalRecord(driver).getText());
        driver.switchTo().defaultContent();

    }
    /**
     * 编辑角色
     */
    @Test
    public void testEditRole() throws InterruptedException {
        UserManagePage.goToRoleManagePage(driver);
        //定位编辑按钮：表格的第 9 行第 4 列的单元格内
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("var editBtn ="
                + "document.querySelectorAll('tr:nth-child(5) > td:nth-child(4) > a.edit.ml10')[0];" + "return editBtn");
       wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        //修改信息
        Thread.sleep(10);
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.checkboxDataBrowse_1(driver))).click();
        //保存
        UserManagePage.saveBtn(driver).click();
        Functions.acceptAlert(driver);
        driver.switchTo().defaultContent();
    }

    /**
     *用户角色页面，分页功能验证
     */
    @Test
    public void testFenYeInUserRole() throws AWTException, InterruptedException {
        UserManagePage.goToRoleManagePage(driver);
        WebElement table = driver.findElement(By.id("table"));
        int rowNo = new Table(table).getRowCount()-1;
        while (rowNo<10){
            UserManagePage.addRole(driver,"role"+ rowNo);
            Functions.acceptAlertViaRobot();
            rowNo = new Table(table).getRowCount()-1;
        }
        UserManagePage.addRole(driver,"role"+ rowNo);
        Functions.acceptAlertViaRobot();
        //点击第2页
        UserManagePage.secondPage(driver).click();
        Assert.assertEquals(2,new Table(table).getRowCount());
        //点击下一页
        UserManagePage.nextPage(driver).click();
        Assert.assertEquals(11,new Table(table).getRowCount());
        //点击上一页
        UserManagePage.prePage(driver).click();
        Assert.assertEquals(2,new Table(table).getRowCount());
        //选择每页显示行数
        UserManagePage.numberOfOnePage(driver).click();
        UserManagePage.selectDisplayedNo(driver).click();
        Assert.assertEquals(12,new Table(table).getRowCount());
        driver.switchTo().defaultContent();
    }
    /**
     * 删除角色
     */
    @Test
    public void testDeleteRole() throws InterruptedException {
        UserManagePage.goToRoleManagePage(driver);
        //删除前的总数
        WebElement table = driver.findElement(By.id("table"));
        int beforeDelete = new Table(table).getRowCount();
        System.out.println("删除前的总数：" + beforeDelete);
        //定位删除按钮：表格的第 9 行第 4 列的单元格内
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement removeBtn = (WebElement) js.executeScript("var removeBtn ="
                + "document.querySelectorAll('tr:nth-child(9) > td:nth-child(4) > a.remove.ml10')[0];" + "return removeBtn");
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        // 取消删除
        Functions.dismissAlert(driver);
        //确认删除
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        Functions.acceptAlert(driver);
        Functions.acceptAlert(driver);
        //删除后的用户总数
        int afterDelete = new Table(table).getRowCount();
        System.out.println("删除后的总数：" + afterDelete);
        Assert.assertEquals(afterDelete,beforeDelete-1);
        driver.switchTo().defaultContent();
    }
    /**
     * 添加部门
     */
    @Test
    public void testAddDept() throws InterruptedException {
        UserManagePage.goToDeptManagePage(driver);
        UserManagePage.addUserBtn(driver).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.deptName(driver)));
        UserManagePage.addDept(driver,"部门2","负责人2","1");
        Functions.acceptAlert(driver);
        driver.switchTo().defaultContent();
    }
    /**
     * 添加部门，部门名称字段验证
     */
    @Test
    public void testVerifyDeptNameField(){
        UserManagePage.goToDeptManagePage(driver);
        UserManagePage.addUserBtn(driver).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.deptName(driver)));
        UserManagePage.saveBtn(driver).click();
        Assert.assertEquals("部门名称不能为空!",UserManagePage.deptNameError(driver).getText());
        UserManagePage.deptName(driver).sendKeys("abcdefghigk");
        UserManagePage.saveBtn(driver).click();
        Assert.assertEquals("部门名称的最小长度:2, 最大长度:10!",UserManagePage.deptNameError(driver).getText());
        UserManagePage.deptName(driver).clear();
        UserManagePage.deptName(driver).sendKeys("a");
        UserManagePage.saveBtn(driver).click();
        Assert.assertEquals("部门名称的最小长度:2, 最大长度:10!",UserManagePage.deptNameError(driver).getText());
        UserManagePage.cancelBtn(driver).click();
        driver.switchTo().defaultContent();
    }
    /**
     * 按输入的关键字，查询部门
     */
    @Test
    public void testInputKeywordAndSearchDept(){
        UserManagePage.goToDeptManagePage(driver);
        UserManagePage.searchByKeyword(driver,"部门2");
        Assert.assertEquals("显示第 1 到第 1 条记录，总共 1 条记录",UserManagePage.totalRecord(driver).getText());
        driver.switchTo().defaultContent();
    }
    /**
     * 从下拉列表中选择，查询部门
     */
    @Test
    public void testSelectFromDropListAndSearchDept(){
        UserManagePage.goToDeptManagePage(driver);
        UserManagePage.deptDropList(driver).click();
        Functions.highlight(driver,UserManagePage.deptDropList(driver));
        UserManagePage.searchByClickDropList(driver,UserManagePage.itemOfDeptList(driver));
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.totalRecord(driver)));
        Assert.assertEquals("显示第 1 到第 1 条记录，总共 1 条记录",UserManagePage.totalRecord(driver).getText());
        driver.switchTo().defaultContent();

    }
    /**
     * 编辑部门
     */
    @Test
    public void testEditDept() throws InterruptedException {
        UserManagePage.goToDeptManagePage(driver);
        //定位编辑按钮：表格的第 4 行第 6 列的单元格内
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("var editBtn ="
                + "document.querySelectorAll('tr:nth-child(4) > td:nth-child(6) > a.edit.ml10')[0];" + "return editBtn");
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        //修改信息
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.deptName(driver)));
        UserManagePage.addDept(driver,"updateName","newRespons","3");
        //保存后验证
        Functions.acceptAlert(driver);
        driver.switchTo().defaultContent();
    }
    /**
     * 删除部门
     */
    @Test
    public void testDeleteDept() throws InterruptedException {
        UserManagePage.goToDeptManagePage(driver);
        //删除前的总数
        WebElement table = driver.findElement(By.id("table"));
        int beforeDelete = new Table(table).getRowCount();
        //定位删除按钮：表格的第 6 行第 6 列的单元格内
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement removeBtn = (WebElement) js.executeScript("var removeBtn ="
                + "document.querySelectorAll('tr:nth-child(6) > td:nth-child(6) > a.remove.ml10')[0];" + "return removeBtn");
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        // 取消删除
        Functions.dismissAlert(driver);
        //确认删除
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        Functions.acceptAlert(driver);
        Functions.acceptAlert(driver);
        //删除后的用户总数
        int afterDelete = new Table(table).getRowCount();
        Assert.assertEquals(afterDelete,beforeDelete-1);
        driver.switchTo().defaultContent();
    }

    @Test(enabled = false)
    public void locateTable(){
        UserManagePage.goToUserManagePage(driver);
        WebElement table = driver.findElement(By.id("table"));
        Functions.highlight(driver,table);
        // new Table(table).printContentsInTable();
        // System.out.println("cell:" + new Table(driver).getCellText("//*[@id='table']/tbody",2,2));
    }

    @Test(enabled = false)
    public void TestDemo() throws InterruptedException, IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //数据更新 -》数据恢复
        WebElement dataUpdate_recover = (WebElement) js.executeScript("var dataUpdate_recover ="
                + "document.querySelectorAll('div.no h2')[8];" + "return dataUpdate_recover");
        //dataUpdate_recover.click();
        System.out.println("dataUpdate_recover text:" + dataUpdate_recover.getText());
    }
    @Test(enabled = false)
    public void testJavaScriptCalls() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String title = (String) js.executeScript("return document.title");
        assertEquals("百度一下，你就知道", title);
        long links = (Long) js.executeScript(
                "var links = "
                        + "document.getElementsByTagName('A'); "
                        + "return links.length");
        System.out.println("links" + links);
        assertEquals(33, links);

//        WebElement bd = (WebElement) js.executeScript("var bd ="
//                + "document.getElementById('kw');"
//                + "return bd");
//        bd.sendKeys("abc");
WebElement inputBox = driver.findElement(By.id("'kw'"));
        js.executeScript("$(arguments[0]).fadeOut();", inputBox);
        inputBox.sendKeys("abc");
    }
    @Test(enabled = false)
    public void testBaiduMap(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://map.baidu.com/");
        driver.findElement(By.cssSelector("#sole-input")).sendKeys("中关村");
        driver.findElement(By.id("search-button")).click();

    }


}



