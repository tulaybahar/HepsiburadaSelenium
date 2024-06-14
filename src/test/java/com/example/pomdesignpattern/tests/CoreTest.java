package com.example.pomdesignpattern.tests;

import com.example.pomdesignpattern.pages.CommentPage;
import com.example.pomdesignpattern.pages.ProductSearchPage;
import com.example.pomdesignpattern.pages.SellerPage;
import com.example.pomdesignpattern.base.BasePage;
import com.example.pomdesignpattern.pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CoreTest {

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


    @Test(groups = {"smoke", "regression"}, priority = 1,
            description = "Scenario 1:Verify searching functionality with iphone and click thumbsUp/Down ")
    public void searchIphone1() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        ProductSearchPage productSearchPage = new ProductSearchPage(driver);
        CommentPage commentPage = new CommentPage(driver);
        basePage.firstTab();
        // Arama kutusunu bul ve "iphone" yaz Ara butonuna tıkla
        productSearchPage.keywordIsSearched()
                         .assertKeywordIsSearched()
                         .chooseRandomFromProductList1()
                         .assertProductDetailLoaded();
        //İkinci taba geçildi
        basePage.secondTab();
        commentPage.scrollUntilToReviewTab()
                .clickReviewTab()
                .clickOrangeArrow();
                // Combobox içinde en yeni değerlendirmeyi seç
                //.clickNewestReviews1()
                // Değerlendirmelerin yüklenmesini bekle
                //.assertReviewsLoadedAccordingToNewest()
                // ThumbsUp seçeneğini rastgele seç
                //.chooseFirstFromThumbsButton1()
                // Teşekkür ederiz yazısını kontrol et
                //.checkThanksMessage();
    }

    @Test(groups = {"smoke", "regression"}, priority = 2,
            description = "Scenario 2:Verify searching functionality with iphone and choose cheapest one from other dealers ")
    public void searchIphone2() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        ProductSearchPage productSearchPage = new ProductSearchPage(driver);
        SellerPage sellerPage = new SellerPage(driver);
        basePage.firstTab();
        // Arama kutusunu bul ve "iphone" yaz Ara butonuna tıkla
        productSearchPage.keywordIsSearched()
                .assertKeywordIsSearched()
                .chooseRandomFromProductList1()
                .assertProductDetailLoaded();
        //İkinci taba geçildi
        basePage.secondTab();
        sellerPage.scrollUntilToSellerTab()
                //Diğer satıcılar tabına tıkla
                .clickToSellerTab()
                //Diğer satıcılar varsa en düşük fiyatlıyı sepete ekle
                .otherSellersClickFirstSellerAndAddToCart1()
                .assertCheapestProductAddedToBasket();
    }


    @Test(groups = {"smoke"}, priority = 3,
            description = "Scenario 3:Verify searching functionality with iphone and compare the price on cart and on the list")
    public void searchIphone3() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        ProductSearchPage productSearchPage = new ProductSearchPage(driver);
        CartPage cartPage = new CartPage(driver);
        basePage.firstTab();
        // Arama kutusunu bul ve "iphone" yaz Ara butonuna tıkla
        productSearchPage.keywordIsSearched()
                .assertKeywordIsSearched()
                .chooseRandomFromProductList1()
                .assertProductDetailLoaded();
        //İkinci taba geçildi
        basePage.secondTab();
        //selectedProductPrice alındı
        //cartPage.getSelectedProductPrice2();
        cartPage.addToCartTheProduct1()
        // Ürünü sepete ekle
                .goToCart();
                //.gettingCartPrice()
                // Sepetteki fiyat ile ürün fiyatı karşılaştırılır.
                //.compareProductAndCartPrice();
    }


    @AfterMethod
    public void tearDown() {
        // Tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }


}
