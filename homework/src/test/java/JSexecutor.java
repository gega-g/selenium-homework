import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSexecutor {
    WebDriver driver;


    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
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
