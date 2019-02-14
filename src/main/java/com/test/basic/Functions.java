package com.test.basic;

import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Functions {
    public static void highlight(WebDriver diver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) diver;
        js.executeScript("element = arguments[0];" +
                "original_style = element.getAttribute('style');" +
                "element.setAttribute('style', original_style + \";" +
                "border: 2px solid red;\");" +
                "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", element);
    }
    public static void dismissAlert(WebDriver driver) throws InterruptedException {
        Thread.sleep(10);
        Alert alert = driver.switchTo().alert();
        Thread.sleep(10);
        alert.dismiss();
        Thread.sleep(10);
    }
    public static void acceptAlert(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        Thread.sleep(2000);
        alert.accept();
        Thread.sleep(2000);
    }
    public static void acceptAlertViaRobot() throws InterruptedException, AWTException {
        Thread.sleep(4000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        Thread.sleep(4000);
    }
    public static void mouseMove(int fromX,int fromY,int toX,int toY) throws AWTException {
        Robot robot = new Robot();
        robot.setAutoDelay(1000);

        robot.mouseMove(fromX,fromY);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(toX,toY);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public static void getScreenShot(String filePath,String fileName) throws AWTException, IOException {
        Robot robot = new Robot();
        //设置Robot产生一个动作后的休眠时间,否则执行过快
        robot.setAutoDelay(1000);
        //获取屏幕分辨率
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(d);
        //以屏幕的尺寸创建个矩形
        Rectangle screenRect = new Rectangle(d);
        //截图（截取整个屏幕图片）
        BufferedImage bufferedImage = robot.createScreenCapture(screenRect);
        //保存截图
        File file = new File(filePath + new Date().getTime() + fileName);
        ImageIO.write(bufferedImage, "png", file);
    }

//    public static void alertAccept1() {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        try {
//            Alert alert = wait.until(new ExpectedCondition<Alert>() {
//                public Alert apply(WebDriver driver) {
//                    try {
//                        return driver.switchTo().alert();
//                    } catch (NoAlertPresentException e) {
//                        return null;
//                    }
//                }
//            });
//            alert.accept();
//        } catch (NullPointerException e) {
//            /* Ignore */
//            System.out.println("ff2 nullpoint");
//        }
//    }

}
