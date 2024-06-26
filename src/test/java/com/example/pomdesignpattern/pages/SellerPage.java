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

import java.time.Duration;
import java.util.List;

public class SellerPage extends BasePage{
    private WebDriver driver;

    public SellerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //scenario 2:
    @FindBy(xpath = ".//div[@class='wrapper']")
    WebElement commonReviewsTab;
    @FindBy(xpath = "//td[@id='merchantTabTrigger']")
    WebElement otherSellers;
    @FindBy(xpath = "//button[@class='add-to-basket button']")
    WebElement otherSellers1;
    @FindBy(xpath = ".//div[contains(text(),' Ürün sepetinizde')]")
    WebElement addedToCart;
    @FindBy(xpath = "//button[contains(text(),'Sepete git')]")
    WebElement addedToCart1;

    /**
     @FindBy(css = "div.product-price")
     WebElement chosenProductPrice;
     @FindBy(css = "div.price")
     WebElement otherSellersPrices;
     @FindBy(xpath = "ancestor::div[contains(@class, 'seller-list-item')]//a")
     WebElement minPriceProductElement;
     */

    BasePage basePage = new BasePage(driver);

    public SellerPage otherSellersClickFirstSellerAndAddToCart() throws InterruptedException {
        // Diğer satıcılar görünürlüğünü kontrol et
        List<WebElement> otherSellersTab = driver.findElements(By.cssSelector(String.valueOf(otherSellers1)));
        boolean otherSellersVisible = !otherSellersTab.isEmpty();
        if (otherSellersVisible) {
            // Diğer satıcılardan ilkine tıkla
            otherSellersTab.get(0).click();
            // En ucuz ürünü sepete ekle
            Thread.sleep(10000);
            WebElement addToCartButton = driver.findElement(By.cssSelector(String.valueOf(addedToCart)));
            addToCartButton.click();
        } else {
            // Mevcut ürünü sepete ekle
            WebElement addToCartButton = driver.findElement(By.cssSelector(String.valueOf(addedToCart)));
            addToCartButton.click();
        }
        return this;
    }
    public SellerPage otherSellersClickFirstSellerAndAddToCart1() throws InterruptedException {
        // Diğer satıcılar görünürlüğünü kontrol et
        List<WebElement> otherSellersTab = driver.findElements(By.xpath("//button[@class='add-to-basket button']"));
        boolean otherSellersVisible = !otherSellersTab.isEmpty();
        if (otherSellersVisible) {
            // Diğer satıcılardan ilkine tıkla
            otherSellersTab.get(0).click();
            // En ucuz ürünü sepete ekle
            Thread.sleep(10000);
        }else {
            // Mevcut ürünü sepete ekle
            WebElement addToCartButton = driver.findElement(By.xpath("//i[@class='icon icon-preorder']"));
            addToCartButton.click();

        }
        return this;
    }
    public SellerPage scrollUntilToSellerTab() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='wrapper']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", otherSellers);
            return this;
    }
public SellerPage clickToSellerTab() throws InterruptedException {
    basePage.clickAnElement(otherSellers);
    System.out.println("Diğer satıcılara tıklandı");
    Thread.sleep(3000);
    return this;
}
    public SellerPage assertCheapestProductAddedToBasket() throws InterruptedException {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement getText = wait.until(ExpectedConditions.visibilityOf(addedToCart1));
        String displayedMessage=getText.getText();
        Assert.assertEquals(displayedMessage, "Sepete git", "Ürün sepetinizde yazısı görünmedi.");
        System.out.println("Sepete git görüldü");
        return  this;
    }
    /**
     public void otherSellersClickBestPrice() {
     // Diğer satıcılar görünürlüğünü kontrol et
     List<WebElement> otherSellersTab = driver.findElements(otherSellers);
     boolean otherSellersVisible = !otherSellersTab.isEmpty();
     // Seçilen ürün fiyatını al
     WebElement selectedProductPriceElement = driver.findElement(chosenProductPrice);
     String selectedProductPriceText = selectedProductPriceElement.getText();
     double selectedProductPrice = Double.parseDouble(selectedProductPriceText.replaceAll("[^0-9,]", "").replace(",", "."));
     double minPrice = selectedProductPrice;
     WebElement minPriceProductElement = selectedProductPrice;
     //Diğer satıcılar varsa fiyatlarını karşılaştır
     if(otherSellersVisible)
     {
     otherSellersTab.get(0).click();
     List<WebElement> otherSellersPrices = driver.findElements(otherSellerPrices);
     for (WebElement priceElement : otherSellersPrices) {
     String priceText = priceElement.getText();
     double price = Double.parseDouble(priceText.replaceAll("[^0-9,]", "").replace(",", "."));
     if (price < minPrice) {
     minPrice = price;
     System.out.println("minPrice = " + minPrice);
     minPriceProductElement = priceElement.findElement(minPriceProductElement);
     }
     }
     // Min fiyatlı ürüne tıklama ve sepete ekleme
     }
     minPriceProductElement.click();
     }
     */
}