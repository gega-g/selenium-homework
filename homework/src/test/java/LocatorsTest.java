import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;


public class LocatorsTest {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
//    unorderedListTest
    public void unorderedListTest(){
        driver.get(Constants.JQUERYUI);
//        finding aside
        WebElement aside = driver.findElement(By.xpath(Constants.ASIDEXPATH));
        Assert.assertNotNull(aside);
//        all li elements and filtering only o-s
        List<WebElement> lis = driver.findElements(By.xpath(Constants.ALLLIINUL));
        List<WebElement> lisStream = lis.stream().filter(str ->
                str.getText().contains("o")).collect(Collectors.toList());
//        using steam and filtering only a-s excluding animate
        lisStream.parallelStream().forEach(element -> {
            WebElement a = element.findElement(By.tagName("a"));
            String hrefs = a.getAttribute(Constants.HREF);
            if (!hrefs.contains(Constants.ANIMATE)){
                System.out.println(hrefs);
            }
        });
    }

    @Test
    public void buttonsTest() {
        driver.get(Constants.HEROKUAPP);
        WebElement addElement = driver.findElement(By.xpath(Constants.ADDELEMENT));
        for (int i=0; i<3; i++){
            addElement.click();
        }
//        last delete button has 'added-manually'
        WebElement lastDelete = driver.findElement(By.xpath(Constants.LASTDELETE));
        boolean attribute = lastDelete.getAttribute(Constants.CLASS).contains(Constants.ADDEDMANUALLY);
        Assert.assertTrue(attribute);
//        last delete button has 'onClick'
        WebElement onClick = driver.findElement(By.cssSelector(Constants.ONCLICKCSS));
        String realOnClick = onClick.getAttribute(Constants.ONCLICK);
        Assert.assertEquals(realOnClick, Constants.DELETEELEMENT);
    }

    @Test
    public void challengingDomTest(){
        driver.get(Constants.HEROKUAPP2);
//
        WebElement apeirian9 = driver.findElement(By.xpath(Constants.APEIRIAN9));
        WebElement lorem = apeirian9.findElement(By.xpath(Constants.LOREM));
        System.out.println(Constants.LOREMVALUE + lorem.getText());
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
