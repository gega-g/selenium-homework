import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WaitsTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void waitForDisappearance(){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get(Constants.DYNAMIC);
        WebElement enable = driver.findElement(By.xpath("//button[@onclick='swapInput()']"));
        enable.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"message\" and text()=\"" + Constants.ENABLED + "\"]"), Constants.ENABLED));

        WebElement disable = driver.findElement(By.xpath("//button[text()='Disable']"));
        assert disable.isDisplayed();

        WebElement field = driver.findElement(By.xpath("//form/input[@type='text']"));
        field.click();
        field.sendKeys(Constants.FIELDMESSAGE);
    }
    @Test
    public void waitForText(){
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.get(Constants.DEMOQA);
        WebElement start = driver.findElement(By.id(Constants.STARTSTOP));
        start.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@role = 'progressbar']"), Constants.HUNDREDPERCENT));
        System.out.println(Constants.HUNDREDPERCENT);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }


}
