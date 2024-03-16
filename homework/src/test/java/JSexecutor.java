import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSexecutor {
    WebDriver driver;

    public static Clipboard getSystemClipboard() {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (c == null) {
            c = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        return c;
    }

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void deleteTest() throws InterruptedException {
        driver.get(Constants.DELETEURL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement magic = driver.findElement(By.xpath("//li[contains(text(),'Practice magic')]"));
        js.executeScript("$(arguments[0]).hover();", magic);
        WebElement delete = driver.findElement(By.xpath("//li[contains(text(),'Practice magic')]//parent::span/i"));
        js.executeScript("arguments[0].click();", delete);
//        sleep ვიზუალური დემონსტრაციისთვის მიწერია
        Thread.sleep(500);
        try {
            assert magic.isDisplayed();
        } catch (StaleElementReferenceException e) {
            assert true;
        }
    }

    @Test
    public void scrollTest(){
        driver.get(Constants.TECHLISTURL);
        List <WebElement> exampleCodes = driver.findElements(By.xpath("//div[@class = 'bg-black rounded-md mb-4']"));
        WebElement copyCode = driver.findElement(By.xpath("//*[@class='h-4 w-4']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        ბევრი ვეცადე აქ მაგრამ არაფერი გამოვიდა ;დ tutorial-ებზე ჩვეულებრივად მუშაობს
        for(WebElement exampleCode : exampleCodes){
            js.executeScript("arguments[0].scrollIntoView();",exampleCode);
            copyCode.click();
//            სხვანაირი ხერხი ვერ ვიპოვე რითაც დავაკოპირებდი კოდს და შევინახავდი მარა ესეც არმუშაობს ;დდ

            WebElement names = exampleCode.findElement(By.xpath("//h3/parent::div/parent::div/parent::div//pre//button"));
            String namesString = names.getText();
            Map<String, String> copiedCodes = new HashMap<>();
            copiedCodes.put(namesString,getSystemClipboard().toString());
            System.out.println(copiedCodes);
        }



//        tutorial links
        WebElement tutorials = driver.findElement(By.xpath("//span[@face='roboto, sans-serif']"));
        js.executeScript("arguments[0].scrollIntoView();",tutorials);
        WebElement element1 = driver.findElement(By.tagName("ul"));
        List<WebElement> elements = element1.findElements(By.tagName("a"));
        Map<String, String> links = new HashMap<>();

        for (WebElement element : elements) {
            String text = element.getText().trim();
            String url = element.getAttribute(Constants.HREF).trim();
                links.put(text, url);
        }
        System.out.println(links);

    }


    @Test
    public void anotherScrollTest(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get(Constants.SCROLLINGURL);
        WebElement left = driver.findElement(By.id(Constants.ZONE2));
        js.executeScript("arguments[0].scrollIntoView();",left);
//        აქ რატომღაც ვერ ვიყენებ Constant-ს zone2-entries ჩასანაცვლებლად
        String text = js.executeScript("return document.getElementById('zone2-entries').innerText;").toString();
        System.out.println(text);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
