import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebElementTest {
WebDriver driver;
@BeforeClass
    public void setup(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get(Constants.LINKFORWEB);
    }

@Test
    public void test2() throws InterruptedException {
//  dimensionTest
    WebElement a = driver.findElement(By.id("column-a"));
    WebElement b = driver.findElement(By.id("column-b"));
//    checking if coordinates are same
    Point locationA = a.getLocation();
    Point locationB = b.getLocation();
    if (locationA.y == locationB.y){
        System.out.println("Coordinate Y is the same for A and B.");
    } else {
        System.out.println("Coordinate Y is different.");
    }

//    check if columns are draggable
    String aIsDraggable = a.getAttribute("draggable");
    String bIsDraggable = b.getAttribute("draggable");
    if (aIsDraggable.equals("true") || equals(bIsDraggable)){
        System.out.println("A and B are draggable");
    } else {
        System.out.println("They aren't draggable");
    }

//    linkTest
    WebElement linkTest = driver.findElement(By.xpath(Constants.LINKTEST));
    String href = linkTest.getAttribute("href");
    if (href.equals("http://elementalselenium.com/")){
        System.out.println("It is set to http://elementalselenium.com/");
    } else {
        System.out.println("It isn't set to that link.");
    }
}



@AfterClass
    public void tearDown(){
        driver.close();
    }
}
