package com.test.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
public class Table {
    WebDriver driver;
    public WebElement _table;
    public Table(){}
    public Table(WebDriver driver){
        this.driver=driver;
    }
    public Table(WebElement element){
        this._table = element;
    }
    public WebElement getTable(){
        return this._table;
    }
    public void setTable(WebElement table){
        this._table = table;
    }
    public int getRowCount(){
        List<WebElement> rows = this._table.findElements(By.tagName("tr"));
        return rows.size();
    }
    public int getColCount(){
        WebElement row = this._table.findElement(By.xpath("//tr[0]"));
        return row.findElements(By.tagName("td")).size();
    }
    public WebElement getCell(int rowNo,int colNo){
        WebElement needRow;
        List<WebElement> rows = this._table.findElements(By.tagName("tr"));
        if(rows.size()<rowNo){    return null;       }
        needRow = rows.get(rowNo-1);
        List<WebElement> cols = needRow.findElements(By.tagName("td"));
        if(cols.size()<colNo){    return null;       }
        return cols.get(colNo-1);
    }
    //返回指定单元格中的内容
    public String getCellText(String locator,int rowNo,int colNo){
        String xpath = locator + "/tr[" + rowNo  +"]/td[" + colNo + "]";
                       //  /tr[2]/td[2]
       // WebElement cell = driver.findElement(By.xpath("//*[@id='table']/tbody/tr[2]/td[2]"));
        WebElement cell = driver.findElement(By.xpath(xpath));
        return cell.getText();
    }
    //遍历表中的所有内容
    public void printContentsInTable(){
       // WebElement table = driver.findElement(By.id("table"));
        List<WebElement> rows = _table.findElements(By.tagName("tr"));
        for(WebElement row : rows){
            List<WebElement> cols = _table.findElements(By.tagName("td"));
            for(WebElement col: cols){
                System.out.println(col.getText() + "\t");
            }
        }
    }
}
