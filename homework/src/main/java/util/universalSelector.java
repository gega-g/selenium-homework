package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class universalSelector {
    public static void helper(Object element, String visibleText){
        WebDriver driver = new ChromeDriver();
        if(element instanceof Select){
           Select select = (Select) element;
           select.selectByVisibleText(visibleText);
        }else if(element instanceof WebElement){
            WebElement web = (WebElement) element;
            try {
                WebElement text = waitUntilVisible(driver, web.findElement(By.xpath("//*[text() ='"+visibleText+"']")));
                text.click();
            }catch (NoSuchElementException exception){
                throw new NoSuchElementException("This element doesn't exist");
            }
        }
    }
    private static WebElement waitUntilVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
