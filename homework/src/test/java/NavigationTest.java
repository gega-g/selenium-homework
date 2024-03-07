import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NavigationTest {
    WebDriver driver;
    @BeforeClass
        public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.NAVIGATIONLINK);
    }

    @Test
        public void test3() throws InterruptedException {
//        navigating to successStories
        WebElement button = driver.findElement(By.id(Constants.ID));
        button.click();
        String url = driver.getCurrentUrl();
        if (url.equals(Constants.SUCCESSSTORIES)){
            System.out.println("Current url is https://ultimateqa.com/testimonials/");
        } else {
            System.out.println("Current url is something different");
        }

//        navigating back to home page
        Thread.sleep(3000);
        driver.navigate().back();
        if (url.equals(Constants.NAVIGATIONLINK)){
            System.out.println("The browser has successfully navigated back");
        } else {
            System.out.println("Current page is something else");
        }
        System.out.println("Current url: "+url);
    }

    @AfterClass
        public void tearDown(){
        driver.close();
    }
}
