import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.*;
import java.util.stream.Collectors;

public class CookiesTest {

//    for injectCookie
    public List<Cookie> cookieBuilder(String name, String value) {
        Cookie cookie = new Cookie.Builder(name, value).build();
        driver.manage().addCookie(cookie);
        List<Cookie> cookies = new ArrayList<>();
        cookies.add(cookie);
        return cookies;
    }
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void filterCookies() {
        driver.get(Constants.FILTERCOOKIESURL);
        Set<Cookie> cookies = driver.manage().getCookies();
        Cookie filteredCookie = cookies.stream()
                .filter(cookie -> cookie.getName().contains(Constants.ACTIVETEMP))
                .filter(cookie -> cookie.getValue().contains(Constants.PUB))
                .findFirst().orElse(null);
        assert filteredCookie != null;
    }

    @Test
    public void injectCookie() {
        driver.get(Constants.FILTERCOOKIESURL);
        List<Cookie> cookiesAfterAdding = new ArrayList<>();
        for (int i=1; i<=10; i++){
            cookiesAfterAdding.addAll(cookieBuilder(Constants.CHOCOCOOKIE+i, Constants.SOMEVALUE));
        }
        List<Cookie> addedCookies = cookiesAfterAdding.stream()
                .filter(cookie -> cookie.getName().contains(Constants.CHOCOCOOKIE))
                .collect(Collectors.toList());
        for (Cookie cookie:addedCookies){
            System.out.println(cookie);
        }

        for (Cookie cookie : addedCookies) {
            driver.manage().deleteCookieNamed(cookie.getName());
        }

        List<Cookie> cookiesAfterDelete = driver.manage().getCookies().stream()
                .filter(cookie -> cookie.getName().contains(Constants.CHOCOCOOKIE))
                .collect(Collectors.toList());
            assert cookiesAfterDelete.isEmpty();

    }
    @Test
    public void autoCompleteTest(){
        driver.get(Constants.AUTOCOMPLETETESTURL);
        WebElement field = driver.findElement(By.xpath("//div[@class="+Constants.FIELD));
        field.click();
        WebElement element = driver.findElement(By.id(Constants.SEARCHBOXID));
        element.click();
        element.sendKeys(Constants.EURO2024CONTENDER);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class="+Constants.XPATHAUTO)));
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);
        System.out.println("This line should raise a conflict");
    }


    @AfterClass
    public void turnDown() {
        driver.close();
    }
}
