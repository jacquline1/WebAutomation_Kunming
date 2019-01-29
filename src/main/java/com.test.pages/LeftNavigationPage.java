package com.test.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeftNavigationPage {
    public static WebElement element ;
    //数据浏览
    public static WebElement dataBrowse (WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element = (WebElement) js.executeScript("var dataBrowse ="
                + "document.querySelectorAll('#p > h1.Menu_shadow > a')[0];dataBrowse.style.border=\\\"1px solid red\\\";" + "return dataBrowse");
        return element;
    }
    //数据浏览 -》道路设施数据
    public static WebElement dataBrowse_road(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element  = (WebElement) js.executeScript("var dataBrowse_road ="
                + "document.querySelectorAll('div.no h2')[0];" + "return dataBrowse_road");
        return element;
    }

    //数据浏览 -》宏观仿真路网
  public static WebElement dataBrowse_hguan(WebDriver driver){
      JavascriptExecutor js = (JavascriptExecutor) driver;
      element = (WebElement) js.executeScript("var dataBrowse_hguan ="
              + "document.querySelectorAll('div.no h2')[1];" + "return dataBrowse_hguan");
      return element;
  }
  //数据浏览 -》中观仿真路网
    public static WebElement dataBrowse_zhguan(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element  = (WebElement) js.executeScript("var dataBrowse_zhguan ="
                + "document.querySelectorAll('div.no h2')[2];" + "return dataBrowse_zhguan");
        return element;
    }
    //数据浏览 -》公交设施数据
    public static WebElement dataBrowse_gjiao(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element  =  (WebElement) js.executeScript("var dataBrowse_gjiao ="
                + "document.querySelectorAll('div.no h2')[3];" + "return dataBrowse_gjiao");
        return element;
    }
    //查询统计
    public static WebElement query(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element   = (WebElement) js.executeScript("var query ="
                + "document.querySelectorAll('#p > h1.Menu_shadow > a')[0];" + "return query");
        return element;
    }
    //点击查询统计，展开菜单
    public static void clickQuery(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String l = "$('#p > h1:nth-child(3) > a').click()";
        js.executeScript(l);
    }
    //查询统计 ->> 单体属性查询
    public static WebElement query_danti(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element   = (WebElement) js.executeScript("var query_danti ="
                + "document.querySelectorAll('div.no h2')[4];" + "return query_danti");
        return element;
    }
    //查询统计 ->> 条件属性查询
    public static WebElement query_tiaojian(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element   = (WebElement) js.executeScript("var query_tiaojian ="
                + "document.querySelectorAll('div.no h2')[5];" + "return query_tiaojian");
        return element;
    }
    //查询统计 ->> 自定义范围
    public static WebElement query_custom(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element   = (WebElement) js.executeScript("var query_custom ="
                + "document.querySelectorAll('div.no h2')[6];" + "return query_custom");
        return element;
    }
    //数据更新 -》数据更新
    public static void clickDataUpdate(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String gx = "$('#p > h1:nth-child(5) > a').click()";
        js.executeScript(gx);
    }
    public static WebElement dataUpdate_shuju(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var dataUpdate_shuju ="
                + "document.querySelectorAll('div.no h2')[7];" + "return dataUpdate_shuju");
        return element;
    }

    //数据更新 -》数据恢复
    public static WebElement dataUpdate_recover(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var dataUpdate_recover ="
                + "document.querySelectorAll('div.no h2')[8];" + "return dataUpdate_recover");
        return element;
    }
    //点击数据导出，展开菜单
    public static void clickDataExport(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String gx = "$('#p > h1:nth-child(7) > a').click()";
        js.executeScript(gx);
    }


    //数据导出 -》全部导出
    public static WebElement dataExport_all(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var dataExport_all ="
                + "document.querySelectorAll('div.no h2')[9];" + "return dataExport_all");
        return element;
    }
    //数据导出 -》自定义范围导出
    public static WebElement dataExport_custom(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var dataExport_custom ="
                + "document.querySelectorAll('div.no h2')[10];" + "return dataExport_custom");
        return element;
    }
    //点击权限管理，展开菜单
    public static void clickAuthManage(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String auth = "$('h1:nth-child(9) > a').click()";
        js.executeScript(auth);
    }
    //权限管理 -》用户管理
    public static WebElement authManage_user(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var authManage_user ="
                + "document.querySelectorAll('div.no h2')[11];" + "return authManage_user");
        return element;
    }
    //权限管理 -》角色管理
    public static WebElement authManage_role(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var authManage_role ="
                + "document.querySelectorAll('div.no h2')[12];" + "return authManage_role");
        return element;
    }
    //权限管理 -》部门管理
    public static WebElement authManage_dept(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        element    = (WebElement) js.executeScript("var authManage_dept ="
                + "document.querySelectorAll('div.no h2')[13];" + "return authManage_dept");
        return element;
    }


}
