package com.example.pomdesignpattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

import java.time.Duration;

public class SearchProductTest {

    WebDriver driver;
    WebDriverWait wait;

@BeforeMethod
    public void setup() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.hepsiburada.com/");
    }



    /**
     * Page Object Design Pattern written by Tulay
     */


    @Test(groups = {"smoke", "regression"},priority = 1,
            description = "Scenario 1:Verify searching functionality with iphone and click thumbsUp/Down ")
    public void searchIphone1() throws InterruptedException {
        SearchProductPage SearchProductPage = new SearchProductPage(driver);
        SearchProductElements SearchProductElements = new SearchProductElements(driver);
        System.out.println("Test basliyor");
        String originalWindow = driver.getWindowHandle();
        System.out.println("Tab 1: " + originalWindow);
        Thread.sleep(1000);


        // Arama kutusunu bul ve "iphone" yaz Ara butonuna tıkla
        SearchProductPage.clickAnElement(SearchProductElements.searchBox);
        SearchProductPage.searchText(SearchProductElements.searchBox1, "Iphone" );
        SearchProductElements.searchButton.click();
        // Arama sonuçlarının yüklenmesini bekle
        Assert.assertNotNull(SearchProductElements.productList, "Ürün listesi görüldü");
        System.out.println("ürün listesi görüldü ");
        Thread.sleep(1000);
        // Ürünleri bul ve bir tanesini rastgele seç
        SearchProductPage.choseRandomFromProductList1();
        // Ürün detay sayfasının yüklenmesini bekle
        Thread.sleep(1000);
        Assert.assertNotNull(SearchProductElements.productDetail, "Ürün detayları görüldü");
        System.out.println("ürün detayı görüldü ");
        // Değerlendirmeler tab'ına geç
    //yenitab yapısı
        System.out.println("driver.getWindowHandles() size : "+ driver.getWindowHandles().size());
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                System.out.println("switching to window : "+windowHandle);
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='wrapper']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", SearchProductElements.productReviewsTab);


        SearchProductPage.clickAnElement(SearchProductElements.productReviewsTab);
        System.out.println("review tab görüldü ");
        Thread.sleep(10000);
        // En yeni değerlendirmelere göre sırala ama önce orange arrowa tıkla
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='wrapper']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", SearchProductElements.orangeArrow);
        SearchProductPage.clickAnElement(SearchProductElements.orangeArrow);
        Thread.sleep(5000);
        System.out.println("Filtreleme seçenekleri açıldı");
        //searchProductElements.sortByNewest1.click();

        // Combobox elementini bulun
        WebElement combobox = driver.findElement(By.xpath("//div[@class='arrowDownOrange']")); // Combobox'un class name'ini girin
        Actions actions = new Actions(driver);
        actions.sendKeys(combobox, Keys.ARROW_DOWN).perform(); // Bir kez aşağı ok tuşuna basar
        actions.sendKeys(combobox, Keys.ARROW_DOWN).perform(); // Bir kez aşağı ok tuşuna basar
        actions.sendKeys(combobox, Keys.ENTER).perform(); // Seçimi onaylamak için Enter tuşuna basar

        Thread.sleep(5000);
        System.out.println("En yeniye göre sıralandı");
        // Değerlendirmelerin yüklenmesini bekle
        Assert.assertNotNull(SearchProductElements.evaluationsLoading, "Değerlendirmeler yüklendi.");
        //////////////////////////

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='wrapper']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", SearchProductElements.thumbsUpButton);
        Thread.sleep(2000);
        //////////////////////////////
        // ThumbsUp seçeneğini rastgele seç
        SearchProductPage.choseFirstFromThumbsButton(SearchProductElements.thumbsButton);
        System.out.println("ThumbsUp seçeneğini rastgele seç");
        // Teşekkür ederiz yazısını kontrol et
        Thread.sleep(2000);
        String displayedMessage = SearchProductPage.verifyText(SearchProductElements.thankYouMessage);
        Assert.assertEquals(displayedMessage,"Teşekkür Ederiz.","Teşekkür ederiz yazısı görünmedi.");
        System.out.println("Teşekkür Ederiz");


    }

    @Test(groups = {"smoke","regression"},priority=2,
            description = "Scenario 2:Verify searching functionality with iphone and choose cheapest one from other dealers ")
    public void searchIphone2() throws InterruptedException {
        SearchProductPage SearchProductPage = new SearchProductPage(driver);
        SearchProductElements SearchProductElements = new SearchProductElements(driver);
               String originalWindow = driver.getWindowHandle();
               System.out.println("Tab 1: " + originalWindow);
        // Arama kutusunu bul ve "iphone" yaz Ara butonuna tıkla
        SearchProductPage.clickAnElement(SearchProductElements.searchBox);
        SearchProductPage.searchText(SearchProductElements.searchBox1, "Iphone" );
        SearchProductElements.searchButton.click();
        // Arama sonuçlarının yüklenmesini bekle
        Assert.assertNotNull(SearchProductElements.productList, "Ürün listesi görüldü");
        System.out.println("ürün listesi görüldü ");
        Thread.sleep(1000);
        // Ürünleri bul ve bir tanesini rastgele seç
        SearchProductPage.choseRandomFromProductList1();
        // Ürün detay sayfasının yüklenmesini bekle
        Thread.sleep(1000);
        Assert.assertNotNull(SearchProductElements.productDetail, "Ürün detayları görüldü");
        System.out.println("ürün detayı görüldü ");
        //yenitab yapısı
        System.out.println("driver.getWindowHandles() size : "+ driver.getWindowHandles().size());
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                System.out.println("switching to window : "+windowHandle);
                driver.switchTo().window(windowHandle);
                break;
            }
        }
              WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
              wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='wrapper']")));
              ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", SearchProductElements.otherSellers1);

            //Diğer satıcılar tabına tıkla
            SearchProductPage.clickAnElement(SearchProductElements.otherSellers);
        System.out.println("Diğer satıcılara tıklandı");
            //Diğer satıcılar varsa en düşük fiyatlıyı sepete ekle
        Thread.sleep(3000);
            SearchProductPage.otherSellersClickFirstSeller(SearchProductElements.otherSellers1);
        }


/**
    @Test(groups = {"smoke"},priority=3,
            description = "Scenario 3:Verify searching functionality with iphone and compare the price on cart and on the list")
    public void searchIphone3() throws InterruptedException {
    SearchProductPage searchProductPage = new SearchProductPage(driver);
    SearchProductElements searchProductElements = new SearchProductElements(driver);
        String originalWindow = driver.getWindowHandle();
        System.out.println("Tab 1: " + originalWindow);
    // Arama kutusunu bul ve "iphone" yaz Ara butonuna tıkla
    searchProductPage.clickAnElement(searchProductElements.searchBox);
    searchProductPage.searchText(searchProductElements.searchBox1, "Iphone" );
    searchProductElements.searchButton.click();
    // Arama sonuçlarının yüklenmesini bekle
    Assert.assertNotNull(searchProductElements.productList, "Ürün listesi görüldü");
    System.out.println("ürün listesi görüldü ");
    Thread.sleep(1000);
    // Ürünleri bul ve bir tanesini rastgele seç
    searchProductPage.choseRandomFromProductList1();
    // Ürün detay sayfasının yüklenmesini bekle
    Assert.assertNotNull(searchProductElements.productDetail, "Ürün detayları görüldü");
    System.out.println("ürün detayı görüldü ");
        //yenitab yapısı
        System.out.println("driver.getWindowHandles() size : "+ driver.getWindowHandles().size());
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                System.out.println("switching to window : "+windowHandle);
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='addToCart']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchProductElements.selectedProductPrice1);
        Thread.sleep(10000);
        double selectedProductPrice= searchProductPage.getSelectedProductPrice1(searchProductElements.selectedProductPrice1);
        System.out.println("selectedprice alındı ");
        // Sepete ekleme işlemi
        searchProductPage.clickAnElement(searchProductElements.addTocart);
        Thread.sleep(1000);
        System.out.println("sepete eklendi ");
        // Sepete git
        Thread.sleep(3000);
        searchProductPage.clickAnElement(searchProductElements.goTocart);
        System.out.println("sepete gidildi ");
        // Sepetteki fiyat kontrolü
        Thread.sleep(3000);
        double cartPrice= searchProductPage.getCartPrice(searchProductElements.cartPrice);
        // Sepetteki fiyat ile ürün fiyatı karşılaştırılır.
        System.out.println("cartprice a tıklandı ");
        Assert.assertEquals(selectedProductPrice,cartPrice,"Sepetteki fiyat, ürün sayfasındaki fiyat ile eşit değil.");
    }
*/


    @AfterMethod
    public void tearDown() {
        // Tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }


}
