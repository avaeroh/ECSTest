package pages;

import base.Test_base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage extends Test_base {

    private long defaultTimeOut = 30;

    public WebDriver webDriver() {
        return driver;
    }

    public void goTo(String url) {
        webDriver().navigate().to(url);
    }

    public WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    public void click(By by) {
        waitForPageToLoad();
        getWebElement(by).click();
    }

    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeOut);
        wait.until(pageLoadCondition);
    }

    public void sendKeys(By element, String text) {
        waitForPageToLoad();
        driver.findElement(element).sendKeys(text);
    }

}