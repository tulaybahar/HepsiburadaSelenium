package com.example.pomdesignpattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProductElements {
    private WebDriver driver;

    public SearchProductElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//ortak
    @FindBy(xpath = "//div[@class='searchBoxOld-uKvVtLzPNdHp38hj3B13']")
    WebElement searchBox;
    @FindBy(xpath = "//*[@placeholder='Ürün, kategori veya marka ara']")
    WebElement searchBox1;
    @FindBy(xpath = "//div[contains(text(),'ARA')]")
    WebElement searchButton;
    @FindBy(xpath= "//ul[@id='1']")
    WebElement productList;
    @FindBy(xpath = "//li[starts-with(@class, 'productListContent')]")
    WebElement productListContent;
    @FindBy(xpath = "//div[@class='productDetailContent']")
    WebElement productDetail;
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    WebElement acceptCookie;


//scenario 1:
    @FindBy(xpath = "//*[@id='productReviewsTab']")
    WebElement productReviewsTab;
    @FindBy(xpath = "//div[@class='arrowUpOrange']")
    WebElement orangeArrow;
    @FindBy(xpath = "//div[contains(text(),'En yeni değerlendirme')]")
    WebElement sortByNewest;
    @FindBy(css = "body.desktop.voltran-container.complete:nth-child(2) div.wrapper:nth-child(2) main.content-wrapper:nth-child(3) div.desktop.widget.AllReviews div.voltran-fragment:nth-child(1) div.hermes-voltran-body.voltran-body.Yorumlar:nth-child(2) div.hermes-Comments-module-GtxTIyZTsjhVzSOxiw4y div.hermes-Comments-module-_XCPNP_LCexLr_oIEq0t:nth-child(1) div.hermes-Comments-module-kV6VmHxTOAz2NZN1JIxw:nth-child(2) div.hermes-ReviewList-module-lDLEVdSJZHdHESARpQBj:nth-child(6) div.hermes-FiltersContainer-module-ELCvjqeomGNRLm_P2jzI div.hermes-FiltersContainer-module-mnQUatcxsv52cDedZndL div.hermes-FiltersContainer-module-yVJCt2Xel3B1vUPtdO7s div.hermes-Sort-module-X72cRt4Ta8Nj2Lb_GA6s div.hermes-Sort-module-VANnZ3_cDZVFx6SLhcdd div.hermes-Dropdown-module-oqU7GgoUy9rUfc59wGfF div.hermes-Sort-module-vJqiqyAGHsTNXjMsIwJD:nth-child(2) div.hermes-Sort-module-AUMlb9J2hoNuSMUxB3c8 > div.hermes-Sort-module-CWnJiGEufJWS8Y2TMNuF.hermes-Sort-module-F5wWWffLEvSW2rQuj0Pm:nth-child(2)")
    WebElement sortByNewest1;

    @FindBy(css = ".paginationContentHolder")
    WebElement evaluationsLoading;
    @FindBy(xpath = "//div[@class='hermes-ReviewCard-module-PbSfiSWIgfswGGBaOrw7']")
    WebElement thumbsButton;
    @FindBy(xpath = "//div[@class='thumbsUp hermes-ReviewCard-module-lOsa4wAwncdp3GgzpaaV']")
    WebElement thumbsUpButton;
    @FindBy(xpath = "//span[@class='hermes-ReviewCard-module-eFtSSTU4lYZLCnzHtzca' and contains(text(),'Teşekkür Ederiz')]")
    WebElement thankYouMessage;


//scenario 2:
    @FindBy(xpath = "//td[@id='merchantTabTrigger']")
    WebElement otherSellers;
    @FindBy(css = "#merchantTabTrigger")
    WebElement otherSellers1;


    /**
    @FindBy(css = "div.product-price")
    WebElement chosenProductPrice;
    @FindBy(css = "div.price")
    WebElement otherSellersPrices;
    @FindBy(xpath = "ancestor::div[contains(@class, 'seller-list-item')]//a")
    WebElement minPriceProductElement;
     */
//scenario 3:
    @FindBy(xpath= "//span[@class='price hepsiburada price-new-old']")
    WebElement selectedProductPrice;
    @FindBy(css= ".price hepsiburada price-new-old")
    WebElement selectedProductPrice1;

    @FindBy(xpath= "//button[@id='addToCart']")
    WebElement addTocart;
    @FindBy(xpath = "//button[contains(text(),'Sepete git')]")
    WebElement goTocart;
    @FindBy(xpath = "//div[@class='price_1D8UZ']")
    WebElement cartPrice;












}
