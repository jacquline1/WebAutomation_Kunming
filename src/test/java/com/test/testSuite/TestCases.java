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
      import org.openqa.selenium.support.ui.ExpectedConditions;
      import org.openqa.selenium.support.ui.WebDriverWait;
      import org.testng.Assert;
      import org.testng.annotations.BeforeClass;
      import org.testng.annotations.Test;
      import java.io.IOException;

      import static org.testng.Assert.assertEquals;
      public class TestCases {
       WebDriver driver = new ChromeDriver();
       WebDriverWait wait = new WebDriverWait(driver,10);
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
       public void testInputRoadNameAndLocate(){
        MiddleMapPage.switchFrame(driver);
        Assert.assertEquals(MiddleMapPage.roadTitle(driver).getText(),"道路设施数据");
        MiddleMapPage.inputRoadName(driver,"吴井路");
        MiddleMapPage.locateBtn(driver).click();
        MiddleMapPage.locatedInMap(driver);
       }
       @Test
       public void testDateBrowseFolder(){
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
        LeftNavigationPage.dataBrowse_gjiao(driver).click();
        System.out.println("dataBrowse_gjiao text:" + LeftNavigationPage.dataBrowse_gjiao(driver).getText());
       }
       @Test
       public void testDateQueryFolder() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //查询统计,点击.
        Thread.sleep(10);
        LeftNavigationPage.clickQuery(driver);
        //查询统计 ->> 单体属性查询
        LeftNavigationPage.query_danti(driver).click();
        System.out.println("query_danti text:" + LeftNavigationPage.query_danti(driver).getText());
        //查询统计 ->> 条件属性查询
        wait.until(ExpectedConditions.visibilityOf(LeftNavigationPage.query_tiaojian(driver)));
        LeftNavigationPage.query_tiaojian(driver).click();
        System.out.println("query_tiaojian text:" + LeftNavigationPage.query_tiaojian(driver).getText());
        //查询统计 ->> 自定义范围
        LeftNavigationPage.query_custom(driver).click();
        System.out.println("query_custom text:" + LeftNavigationPage.query_custom(driver).getText());
       }
       /**
        * 上传数据更新文件
        */
       @Test
       public void testDateUpdate() throws IOException {
        //数据更新,点击展开菜单
        LeftNavigationPage.clickDateUpdate(driver);
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
       @Test
       public void testDateExport(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //数据导出 -》全部导出
        WebElement dataExport_all = (WebElement) js.executeScript("var dataExport_all ="
                + "document.querySelectorAll('div.no h2')[9];" + "return dataExport_all");
        System.out.println("dataExport_all text:" + dataExport_all.getText());
        //数据导出 -》自定义范围导出
        WebElement dataExport_custom = (WebElement) js.executeScript("var dataExport_custom ="
                + "document.querySelectorAll('div.no h2')[10];" + "return dataExport_custom");
        System.out.println("dataExport_custom text:" + dataExport_custom.getText());

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
       }
       /**
        * 成功添加用户
        */
       @Test
       public void testAddUserPass() throws InterruptedException {
        UserManagePage.goToUserManagePage(driver);
        WebElement table = driver.findElement(By.id("table"));
        int beforeRowNo = new Table(table).getRowCount();
        System.out.println("添加用户前总用户数为：" + beforeRowNo);
        UserManagePage.addUserBtn(driver).click();
        UserManagePage.addUser(driver,"test2","test2","1","1");
        Thread.sleep(10);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(10);
        int afterRowNo = new Table(table).getRowCount();
        System.out.println("添加成功后用户数为：" + afterRowNo);
        Assert.assertEquals(afterRowNo,beforeRowNo+1);
       }
       /**
        * 按输入的内容查询用户
        */
       @Test
       public void testInputKeywordAndSearchUser(){
        UserManagePage.goToUserManagePage(driver);
        UserManagePage.searchByKeyword(driver,"test1");
       }
       /**
        * 从用户下拉列表中选择，查询用户
        */
       @Test
       public void testSelectFromDropListAndSearchUser(){
        UserManagePage.goToUserManagePage(driver);
        UserManagePage.userDropList(driver).click();
        Functions.highlight(driver,UserManagePage.userDropList(driver));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (WebElement) js.executeScript("var user ="
                + "document.querySelectorAll('#user > ul > li:nth-child(10)')[0];" + "return user");
        element.click();
        wait.until(ExpectedConditions.visibilityOf(UserManagePage.totalRecord(driver)));
        Assert.assertEquals("显示第 1 到第 1 条记录，总共 1 条记录",UserManagePage.totalRecord(driver).getText());
       }

       /**
        * 编辑用户
        */
       @Test
       public void testEditUser() throws InterruptedException {
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
        //验证弹出编辑成功信息
        Thread.sleep(10);
        Alert alert = driver.switchTo().alert();
        Thread.sleep(10);
        Assert.assertEquals("编辑成功",alert.getText());
        Thread.sleep(10);
        alert.accept();
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
                + "document.querySelectorAll('tr:nth-child(9) > td:nth-child(6) > a.remove.ml10')[0];" + "return removeBtn");
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
       }

       /**
        *添加角色
        */
       @Test
       public void testAddRolePass() throws InterruptedException {
        UserManagePage.goToRoleManagePage(driver);
        UserManagePage.addRole(driver,"测试角色a1");
        Functions.acceptAlert(driver);

       }

       @Test
       public void locateTable(){
        UserManagePage.goToUserManagePage(driver);
        WebElement table = driver.findElement(By.id("table"));
        Functions.highlight(driver,table);
       // new Table(table).printContentsInTable();
       // System.out.println("cell:" + new Table(driver).getCellText("//*[@id='table']/tbody",2,2));
       }

       @Test
       public void TestDemo() throws InterruptedException, IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //数据更新 -》数据恢复
        WebElement dataUpdate_recover = (WebElement) js.executeScript("var dataUpdate_recover ="
                + "document.querySelectorAll('div.no h2')[8];" + "return dataUpdate_recover");
        //dataUpdate_recover.click();
        System.out.println("dataUpdate_recover text:" + dataUpdate_recover.getText());

        }
       @Test
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

      WebElement bd = (WebElement) js.executeScript("var bd ="
                      + "document.getElementById('kw');"
              + "return bd");
      bd.sendKeys("abc");
       }
       @Test
       public void testBaiduMap(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://map.baidu.com/");
        driver.findElement(By.cssSelector("#sole-input")).sendKeys("中关村");
        driver.findElement(By.id("search-button")).click();

       }


      }



