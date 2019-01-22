package com.test.cases;

import com.test.actions.LoginAction;
import com.test.basic.OpenBrowser;
import com.test.pages.LeftNavigationPage;
import com.test.pages.LoginPage;
import com.test.pages.MiddleMapPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.test.basic.OpenBrowser.openBrowser;

public class Login {
    LoginAction la = null;
    @BeforeMethod
    public void beforeMethod(){

    }

    @Test
    public void logPass(){
        la = new LoginAction(OpenBrowser.openBrowser("http://192.168.10.35:8012/page/main.html"));
        la.Login("chen","000000");
    }
    @Test
    public void logFail(){

    }
    @AfterMethod
    public void afterMethod(){
        la.ReturnDriver().close();
    }

}
