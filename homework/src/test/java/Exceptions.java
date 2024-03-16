import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exceptions {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void staleElementReferenceException() throws InterruptedException {
        driver.get(Constants.DELETEURL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement magic = driver.findElement(By.xpath("//li[contains(text(),'Practice magic')]"));
        js.executeScript("$(arguments[0]).hover();", magic);
        WebElement delete = driver.findElement(By.xpath("//li[contains(text(),'Practice magic')]//parent::span/i"));
        js.executeScript("arguments[0].click();", delete);
        Thread.sleep(1000);
        try {
            assert magic.isDisplayed();
        }
//        catch (StaleElementReferenceException e) {
//            assert true;
//        }
        finally {}
    }
    @Test
    public void noSuchElementException() {
        driver.get(Constants.SCROLLINGURL);
        try {
            WebElement element = driver.findElement(By.id(Constants.HONDA));
        }
//        catch (NoSuchElementException e) {
//            e.printStackTrace();
//        }
        finally {}
    }

    @Test
    public void noSuchFrameException(){
        driver.get(Constants.SCROLLINGURL);
        try {
            driver.switchTo().frame(Constants.FRAMENAME);
        }
//        catch (NoSuchFrameException e){
//            e.printStackTrace();
//        }
        finally {}
    }
    @Test
    public void noSuchSessionException(){
        driver.get(Constants.SCROLLINGURL);
        driver.close();
        try {
            driver.findElement(By.xpath(Constants.ASIDEXPATH));
        }
//        catch (NoSuchSessionException e){
//            e.printStackTrace();
//        }
        finally {}
    }

    @Test
    public void noAlertPresentException(){
        driver.get(Constants.ALERTURL);
        try {
            driver.switchTo().alert();
        }
//        catch (NoAlertPresentException e){
//        WebElement alertButton = driver.findElement(By.id(Constants.TIMERALERT));
//            alertButton.click();
//            WebDriverWait wait = new WebDriverWait(driver,10);
//            wait.until(ExpectedConditions.alertIsPresent());
//        }
        finally {}
    }

    @Test
    public void timeoutException(){
        driver.get(Constants.SCROLLINGURL);
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.HONDA)));
        }
//      catch (TimeoutException e) {
//          e.printStackTrace();
//      }
        finally {}
    }

    @Test
    public void noSuchWindowException(){
        driver.get(Constants.SCROLLINGURL);
        try {
            String nonExistingWindow = Constants.HONDA;
            driver.switchTo().window(nonExistingWindow);
        }
//        catch (NoSuchWindowException e){
//            e.printStackTrace();
//        }
        finally {}
    }

    @Test
    public void invalidArgumentException(){
        try{
            driver.get(Constants.HONDA);
        }
//        catch (InvalidArgumentException e){
//            e.printStackTrace();
//        }
        finally {}
    }


    @AfterClass
    public void turnDown(){
        driver.quit();
    }
}

