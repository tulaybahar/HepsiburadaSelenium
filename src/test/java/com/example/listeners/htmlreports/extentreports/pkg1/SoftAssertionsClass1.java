package com.example.listeners.htmlreports.extentreports.pkg1;

import com.example.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionsClass1 {
    @Test(groups = "regression")
    public void testEbaySoftAssertion() {
        try {
            Driver.getDriver().get("https://www.ebay.com/");
            SoftAssert softAssert = new SoftAssert();

            WebElement searchButton = Driver.getDriver().findElement(By.xpath("//input[@id='gh-btn']"));
            searchButton.click();
            String expectedTitle = "Shop by Category | eBay";
            String actualTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitle, expectedTitle, "Assertion Failed!!!!");
            System.out.println("Assertion Successful");
            //At the end of the assertions, we need to use below assertAll() method.
            softAssert.assertAll();
        } catch (Exception e) {
            Driver.closeDriver();
            e.printStackTrace();
        }
    }


}
