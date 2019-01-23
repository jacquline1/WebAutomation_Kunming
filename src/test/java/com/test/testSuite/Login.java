package com.test.testSuite;

import com.test.actions.LoginAction;
import com.test.basic.OpenBrowser;
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
