package com.test.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserEngine {
    public String browserName;
    public String serverURL;
    WebDriver driver;

   public BrowserEngine(WebDriver driver){
       this.driver = driver;
   }
    public void initConfigData() throws IOException {
        Properties properties = new Properties();
        //加载配置文件
        InputStream ins = new FileInputStream("E:\\JZ\\src\\main\\java\\testConfig\\config.properties");
        properties.load(ins);
        browserName = properties.getProperty("browserName");
        serverURL = properties.getProperty("URL");
        ins.close();
    }
    public void  getBrowser(){
        if(browserName.equalsIgnoreCase("firefox")){
          //  System.setProperty("webdriver.gecko.driver", ".\\Tools\\geckodriver.exe");
            //  driver = createFireFoxDriver();
        }else if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "E:\\JZ\\src\\test\\java\\Tools\\chromedriver.exe");
            driver= new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver", ".\\Tools\\IEDriverServer.exe");
            driver= new InternetExplorerDriver();
        }
        driver.get(serverURL);
        driver.manage().window().maximize();
        //return driver;
    }


}
