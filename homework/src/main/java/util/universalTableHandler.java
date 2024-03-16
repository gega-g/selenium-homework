package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class universalTableHandler {
    WebDriver driver;

    public List<List<String>> getTableData(WebElement table) {
        List<List<String>> tableData = new ArrayList<>();

        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.xpath(".//td | .//th"));
            List<String> rowElements = new ArrayList<>();
            for (WebElement column : columns) {
                rowElements.add(column.getText());
            }
            tableData.add(rowElements);
        }
        return tableData;
    }
    public void tableTest(String url, String elementToSearch) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        List<WebElement> tables = driver.findElements(By.xpath("//table"));
        for (WebElement table : tables) {
            universalTableHandler tableHandler = new universalTableHandler();
            List<List<String>> tableData = tableHandler.getTableData(table);
            for (List<String> row : tableData) {
                try{ if (row.contains(elementToSearch))
                    System.out.println(row);
                }catch (NoSuchElementException exception){
                    throw new NoSuchElementException("This element doesn't exist");
                }
            }
        }
        driver.close();
    }
}