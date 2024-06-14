package com.example.pomdesignpattern.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;

    }
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    WebElement acceptCookie;

    public void clickAnElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        WebElement clickElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickElement.click();
    }
    public void searchText(WebElement element, String searchText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        WebElement sendText = wait.until(ExpectedConditions.visibilityOf(element));
       sendText.sendKeys(searchText);
    }
    public String verifyText(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement getText = wait.until(ExpectedConditions.visibilityOf(element));
        return getText.getText();
    }
    public void verifyWait(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebElement getText = wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void firstTab() {
        String originalWindow = driver.getWindowHandle();
        System.out.println("Tab 1: " + originalWindow);
    }
    public void secondTab(){
        String originalWindow = driver.getWindowHandle();
        System.out.println("driver.getWindowHandles() size : "+ driver.getWindowHandles().size());
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                System.out.println("switching to window : "+windowHandle);
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    public void scrollUntilToElement(WebElement element1 ,WebElement element2) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.valueOf(element1))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element2);
    }
    public void chooseFromComboboxMoveToTwoDown (WebElement element){
        WebElement combobox = driver.findElement((By.xpath(String.valueOf(element)))); // Combobox'un class name'ini girin
        Actions actions = new Actions(driver);
        actions.sendKeys(combobox, Keys.ARROW_DOWN).perform(); // Bir kez aşağı ok tuşuna basar
        actions.sendKeys(combobox, Keys.ARROW_DOWN).perform(); // Bir kez aşağı ok tuşuna basar
        actions.sendKeys(combobox, Keys.ENTER).perform(); // Seçimi onaylamak için Enter tuşuna basar
    }

}
