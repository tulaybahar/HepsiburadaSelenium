package com.example.pomdesignpattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SearchProductPage {
    private WebDriver driver;

    public SearchProductPage(WebDriver driver) {
        this.driver = driver;

    }


    SearchProductElements SearchProductElements = new SearchProductElements(driver);

    /**
     * Page Object Design Pattern written by Tulay

     */

    public void clickAnElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        WebElement clickElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickElement.click();
    }

    public void searchText(WebElement element, String searchText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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

    public void choseRandomFromProductList(WebElement element) {
        List<WebElement> products = driver.findElements(By.xpath(String.valueOf(element)));
        if (products.isEmpty()) {
            System.out.println("Arama sonuçları bulunamadı.");
            return;
        }
        WebElement randomProduct = products.get(new Random().nextInt(products.size()));
        randomProduct.click();
    }
    public void choseRandomFromProductList1(){
        List<WebElement> products = driver.findElements(By.xpath("//li[starts-with(@class, 'productListContent')]"));
        if (products.isEmpty()) {
            System.out.println("Arama sonuçları bulunamadı.");
            return;
        }
        WebElement randomProduct = products.get(new Random().nextInt(products.size()));
        randomProduct.click();
    }

    public void choseRandomFromThumbsButton(WebElement element) {
        List<WebElement> thumbsButtons = driver.findElements(By.xpath(String.valueOf(element)));
        if (!thumbsButtons.isEmpty()) {
            WebElement randomThumb = thumbsButtons.get(new Random().nextInt(thumbsButtons.size()));
            randomThumb.click();
        }
    }
    public void choseFirstFromThumbsButton(WebElement element) {
        List<WebElement> thumbsButtons = driver.findElements(By.xpath(String.valueOf(element)));
        if (!thumbsButtons.isEmpty()) {
            WebElement firstThumb = thumbsButtons.get(0);
            WebElement targetElement=firstThumb.findElement(By.xpath(String.valueOf(SearchProductElements.thumbsUpButton)));
            targetElement.click();
        }
        String displayedMessage =verifyText(SearchProductElements.thankYouMessage);
        Assert.assertEquals(displayedMessage,"Teşekkür Ederiz.","Teşekkür ederiz yazısı görünmedi.");
    }
    public void otherSellersClickFirstSeller(WebElement element) throws InterruptedException {
        // Diğer satıcılar görünürlüğünü kontrol et
        List<WebElement> otherSellersTab = driver.findElements(By.cssSelector(String.valueOf(element)));
        boolean otherSellersVisible = !otherSellersTab.isEmpty();
        if (otherSellersVisible) {
            // Diğer satıcılardan ilkine tıkla
            otherSellersTab.get(0).click();
            // En ucuz ürünü sepete ekle
            Thread.sleep(10000);
            WebElement addToCartButton = driver.findElement(By.cssSelector("#addToCart"));
            addToCartButton.click();
        } else {
            // Mevcut ürünü sepete ekle
            WebElement addToCartButton = driver.findElement(By.cssSelector("#addToCart"));
            addToCartButton.click();
        }
    }
        /**
    public void otherSellersClickBestPrice() {
        SearchProductElements searchProductElements = new SearchProductElements(driver);
    // Diğer satıcılar görünürlüğünü kontrol et
    List<WebElement> otherSellersTab = driver.findElements(searchProductElements.otherSellers);
    boolean otherSellersVisible = !otherSellersTab.isEmpty();

    // Seçilen ürün fiyatını al
    WebElement selectedProductPriceElement = driver.findElement(searchProductElements.chosenProductPrice);
    String selectedProductPriceText = selectedProductPriceElement.getText();
    double selectedProductPrice = Double.parseDouble(selectedProductPriceText.replaceAll("[^0-9,]", "").replace(",", "."));

    double minPrice = selectedProductPrice;
    WebElement minPriceProductElement = selectedProductPrice;
    //Diğer satıcılar varsa fiyatlarını karşılaştır
        if(otherSellersVisible)

    {
        otherSellersTab.get(0).click();
        List<WebElement> otherSellersPrices = driver.findElements(searchProductElements.otherSellerPrices);
        for (WebElement priceElement : otherSellersPrices) {
            String priceText = priceElement.getText();
            double price = Double.parseDouble(priceText.replaceAll("[^0-9,]", "").replace(",", "."));
            if (price < minPrice) {
                minPrice = price;
                System.out.println("minPrice = " + minPrice);
                minPriceProductElement = priceElement.findElement(searchProductElements.minPriceProductElement);
            }
        }
        // Min fiyatlı ürüne tıklama ve sepete ekleme
    }
        minPriceProductElement.click();
    }
    */

    public double getSelectedProductPrice(WebElement element) {
        // Seçilen ürün fiyatını al
        WebElement  selectedProductPriceElement = driver.findElement(By.xpath(String.valueOf(element)));
        String selectedProductPriceText = selectedProductPriceElement.getText();
        double selectedProductPrice = Double.parseDouble(selectedProductPriceText.replaceAll("[^0-9,]", "").replace(",", "."));
        return selectedProductPrice;
    }
    public double getSelectedProductPrice1(WebElement element) {
        // Seçilen ürün fiyatını al
        List<WebElement> prices = driver.findElements(By.xpath(String.valueOf(element)));
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


    public double getCartPrice(WebElement element) {
        // Sepet fiyat kontrolü
        WebElement cartPriceElement = driver.findElement(By.xpath(String.valueOf(element)));
        String cartPriceText = cartPriceElement.getText();
        double cartPrice = Double.parseDouble(cartPriceText.replaceAll("[^0-9,]", "").replace(",", "."));
        return cartPrice;
    }





    }
