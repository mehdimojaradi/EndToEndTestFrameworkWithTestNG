package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    private static final int TIMEOUT = 15;
    private static final int POLLING = 100;

    public WebDriver driver;
    public WebDriverWait wait;


    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,TIMEOUT, POLLING);
    }

    protected void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    public String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    public void assertEquals(By elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);
    }

    public void selectDropdownByText(By elementBy, String expectedText){
        waitVisibility(elementBy);
        Select drp = new Select(driver.findElement(elementBy));
        drp.selectByVisibleText(expectedText);
    }

    public void selectDropdownByValue(By elementBy, String expectedValue){
        waitVisibility(elementBy);
        Select drp = new Select(driver.findElement(elementBy));
        drp.selectByValue(expectedValue);
    }

    public void selectFromAutocomplete(By elementBy, String text, int millis ) throws InterruptedException{
        waitVisibility(elementBy);
        WebElement el = driver.findElement(elementBy);
        el.clear();
        el.sendKeys(text);
        Thread.sleep(millis);
        el.sendKeys(Keys.ARROW_DOWN);
        el.sendKeys(Keys.ENTER);
    }

    public void sleep(int millis ){
        try {
            Thread.sleep(millis);
        }catch (Exception ex){}
    }

    public void clearElement(By elementBy){
        waitVisibility(elementBy);
        driver.findElement(elementBy).clear();
    }
}
