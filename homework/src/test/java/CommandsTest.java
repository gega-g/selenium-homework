import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommandsTest {
    WebDriver driver;
    @BeforeClass
        public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Constants.LINK);
        }

    @Test
        public void test() throws InterruptedException {
//        buttonsTest
        WebElement button = driver.findElement(By.xpath(Constants.BUTTON));

        System.out.println("Button text is enabled: "+button.getText().equals("Enable"));
        button.click();
        Thread.sleep(5000);
        System.out.println("Button text has changed to disabled: "+button.getText().equals("Disable"));
//        button text and message
        WebElement message = driver.findElement(By.id("message"));
        System.out.println("It's Enabled is displayed: "+message.isDisplayed());
        System.out.println("Button text: "+button.getText());
//        testing input
        WebElement input = driver.findElement(By.xpath(Constants.INPUT));
        System.out.println("Input is enabled: "+input.isEnabled());
        input.sendKeys("TBC IT Academy");
        Thread.sleep(2000);
        input.clear();

    //    labelsTest
        WebElement mainHeading = driver.findElement(By.cssSelector("h4"));
        System.out.println("Main heading equals 'Dynamic Controls: '"
                +mainHeading.getText().equals("Dynamic Controls"));

//        description under main heading
        WebElement description = mainHeading.findElement(By.xpath(Constants.DESCRIPTION));
        String expectation = "This example demonstrates when elements (e.g., checkbox, input field, etc.) are changed asynchronously.";
        if (description.getText().equals(expectation)){
            System.out.println("The texts are equal");
        }else{
            System.out.println("The texts aren't equal");
        }
}

    @AfterClass
        public void tearDown(){
        driver.close();
        }
    }
