package com.test.actions;

import com.test.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginAction {
    private WebDriver driver;
    public LoginAction(WebDriver driver){
        this.driver = driver;
    }
    //登录
    public void Login(String user,String pw){
        LoginPage.userName(driver).sendKeys(user);
        LoginPage.passWord(driver).sendKeys(pw);
        LoginPage.loginButton(driver).click();
    }
    //返回driver
    public WebDriver ReturnDriver(){
        return this.driver;
    }
}
