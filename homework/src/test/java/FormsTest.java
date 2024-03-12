import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.universalSelector;

import java.util.List;



public class FormsTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void customDropDownTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get(Constants.TYMPANUS);
        WebElement demo2 = driver.findElement(By.xpath("//a[@href='index2.html']"));
        demo2.click();

        WebElement dropDown = driver.findElement(By.id("dd"));
        wait.until(ExpectedConditions.elementToBeClickable(dropDown));
        String attribute = dropDown.getAttribute(Constants.CLASS);
        boolean isDropDownVisible = attribute.contains(Constants.ACTIVE);
        Assert.assertFalse(isDropDownVisible);

        dropDown.click();
        WebElement activeDropDown = driver.findElement(By.xpath("//div[@class ='wrapper-dropdown-2 active']"));
        System.out.println(Constants.ISDISPLAYEDMESSAGE + activeDropDown.isDisplayed());

        WebElement github = driver.findElement(By.xpath("//a[@href='#']"));
        universalSelector.helper(github, Constants.GITHUB);
    }

    @Test
    public void nativeDropDownTest() {
        driver.get(Constants.TECHCANVASS);
        WebElement male = driver.findElement(By.xpath("//*[@value='male']"));
        male.click();
        WebElement select = driver.findElement(By.xpath("//select"));
        universalSelector.helper(select, Constants.MEGA);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> allRequired = driver.findElements(By.xpath("//*[@required]"));
        for (WebElement element : allRequired) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            element.sendKeys(Constants.MEGA);
        }

        WebElement register = driver.findElement(By.xpath("//*[@value='Register']"));
        register.click();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}


