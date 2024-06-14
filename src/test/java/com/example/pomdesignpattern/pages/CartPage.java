package com.example.pomdesignpattern.pages;

import com.example.pomdesignpattern.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage{
    private static WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //scenario 3:
    @FindBy(xpath= "//span[@class='price hepsiburada price-new-old']")
    WebElement selectedProductPrice;
    @FindBy(css= ".price hepsiburada price-new-old")
    WebElement selectedProductPrice1;
    //@FindBy(xpath= "//span[@class='addToCartButton']")
    //WebElement addTocart;
    @FindBy(xpath= "//button[@class='button big with-icon']")
    WebElement addTocart1;
    @FindBy(xpath = "//button[contains(text(),'Sepete git')]")
    WebElement goTocart;
    @FindBy(xpath = "//div[@class='price_1D8UZ']")
    WebElement cartPrice;

    BasePage basePage = new BasePage(driver);

    public double getSelectedProductPrice(WebElement element) {
        // Seçilen ürün fiyatını al
        WebElement  selectedProductPriceElement = driver.findElement(By.xpath(String.valueOf(element)));
        String selectedProductPriceText = selectedProductPriceElement.getText();
        double selectedProductPrice = Double.parseDouble(selectedProductPriceText.replaceAll("[^0-9,]", "").replace(",", "."));
        return selectedProductPrice;
    }
    public double getSelectedProductPrice1() {
        // Seçilen ürün fiyatını al
        List<WebElement> prices = driver.findElements(By.xpath(String.valueOf(selectedProductPrice)));
        String beforePrice = prices.get(0).getText().replaceAll("[^0-9,]", "");
        System.out.println("beforePrice = " + beforePrice);
        String afterPrice = prices.get(1).getText().replaceAll("[^0-9,]", "");
        System.out.println("afterPrice = " + afterPrice);
        String selectedProductPriceText= beforePrice+"."+afterPrice;
        System.out.println("selectedProductPriceText = " + selectedProductPriceText);
        double selectedProductPrice = Double.parseDouble(selectedProductPriceText.replaceAll("[^0-9,]", "").replace(",", "."));
        System.out.println("selectedProductPrice = " + selectedProductPrice);
        return selectedProductPrice;
    }

    public double getSelectedProductPrice2() {
        // Seçilen ürün fiyatını al
        WebElement  selectedProductPriceElement = driver.findElement(By.xpath("//button[@data-price]"));
        String selectedProductPriceText = selectedProductPriceElement.getText();
        double selectedProductPrice = Double.parseDouble(selectedProductPriceText.replaceAll("[^0-9,]", "").replace(",", "."));
        return selectedProductPrice;
    }
    public double getCartPrice() {
        // Sepet fiyat kontrolü
        WebElement cartPriceElement = driver.findElement(By.xpath(String.valueOf(cartPrice)));
        String cartPriceText = cartPriceElement.getText();
        double cartPrice = Double.parseDouble(cartPriceText.replaceAll("[^0-9,]", "").replace(",", "."));
        return cartPrice;
    }
    public CartPage addToCartTheProduct() throws InterruptedException {
        List<WebElement> addToCartTheProduct = driver.findElements(By.xpath("//button[@class='button big with-icon']"));
        boolean buttonIsVisible = !addToCartTheProduct.isEmpty();
        if (buttonIsVisible) {
            // Sepete ekle butonlarından ilkine tıkla
            addToCartTheProduct.get(0).click();
            // Ürünü sepete ekl
            Thread.sleep(5000);
            System.out.println("Ürün sepete eklendi ");
        }
        return this;
    }
    public CartPage addToCartTheProduct1() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        WebElement addToCartTheProduct = wait.until(ExpectedConditions.elementToBeClickable(addTocart1));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", addTocart1);addToCartTheProduct.click();
            // Ürünü sepete ekle
            Thread.sleep(10000);
            System.out.println("Ürün sepete eklendi ");
        return this;
        }


    public CartPage goToCart() throws InterruptedException {
        Thread.sleep(5000);
        clickAnElement(goTocart);
        Thread.sleep(5000);
        System.out.println("Sepete gidildi ");
        return this;
    }
    public CartPage gettingSelectedCartPrice() {
        double selectedProductPrice= getSelectedProductPrice1();
        return this;
    }
    public CartPage gettingCartPrice() {
        double cartPrice= getCartPrice();
        return this;
    }
    public CartPage compareProductAndCartPrice() {
        System.out.println("cartprice alındı ");
        Assert.assertEquals(selectedProductPrice, cartPrice, "Sepetteki fiyat, ürün sayfasındaki fiyat ile eşit değil.");
        return this;
    }
}