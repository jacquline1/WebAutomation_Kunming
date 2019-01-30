package com.test.testSuite;

import com.test.actions.LoginAction;
import com.test.basic.OpenBrowser;
import com.test.pages.MiddleMapPage;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {
    LoginAction la = null;
    @Test
    public void logPass() {
        la = new LoginAction(OpenBrowser.openBrowser("http://192.168.10.35:8012/page/main.html"));
        la.Login("chen", "000000");
        Reporter.log("登录成功",true);
    }

    @AfterMethod
    public void afterMethod(){
        la.ReturnDriver().close();
    }
    }