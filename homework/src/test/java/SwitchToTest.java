import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class SwitchToTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void switchToTest() {
        driver.get(Constants.IFRAMEURL);
        driver.switchTo().frame(Constants.FRAMENAME);
        WebElement textGoesHere = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        textGoesHere.clear();
        textGoesHere.sendKeys(Constants.HEREGOES);
        driver.switchTo().defaultContent();
        WebElement alignCenterButton = driver.findElement(By.xpath("//button[@title='Align center']"));
        alignCenterButton.click();
    }

    @Test
    public void alertTest() throws InterruptedException {
        driver.get(Constants.ALERTURL);
        WebElement alertButton = driver.findElement(By.id(Constants.ALERTBUTTON));
        alertButton.click();
//        უბრალოდ ვიზუალურად რომ გამოჩნდეს რას აკეთებს მაგისთვის მიწერია sleep
        Thread.sleep(500);
        driver.switchTo().alert().accept();
        Thread.sleep(500);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
