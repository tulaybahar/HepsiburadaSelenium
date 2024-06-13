package com.example.pomdesignpattern.pages;

import com.example.pomdesignpattern.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;
import java.util.Random;

public class ProductSearchPage extends BasePage {
    public WebDriver driver;

    public ProductSearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //ortak
    @FindBy(xpath = "//div[starts-with(@class,'searchBoxOld')]")
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


    BasePage basePage = new BasePage(driver);

    public ProductSearchPage chooseRandomFromProductList(WebElement element) {
        List<WebElement> products = driver.findElements(By.xpath(String.valueOf(element)));
        if (products.isEmpty()) {
            System.out.println("Arama sonuçları bulunamadı.");
        }
        WebElement randomProduct = products.get(new Random().nextInt(products.size()));
        randomProduct.click();
        return this;
    }
    public ProductSearchPage chooseRandomFromProductList1(){
        ProductSearchPage result = this;
        List<WebElement> products = driver.findElements(By.xpath("//li[starts-with(@class, 'productListContent')]"));
        if (products.isEmpty()) {
            System.out.println("Arama sonuçları bulunamadı.");
        } else {
            WebElement randomProduct = products.get(new Random().nextInt(products.size()));
            randomProduct.click();
        }
        return result;
    }
    public ProductSearchPage keywordIsSearched() throws InterruptedException {
        basePage.clickAnElement(searchBox);
        basePage.searchText(searchBox1, "Iphone");
        searchButton.click();
        return this;
    }
    public ProductSearchPage keywordIsSearched1() throws InterruptedException {
        Thread.sleep(1000);
            searchBox.click();
        Thread.sleep(1000);
            searchBox1.click();
        Thread.sleep(1000);
            searchBox1.sendKeys("iphone");
        Thread.sleep(1000);
            searchButton.click();
            return this;
    }
    public ProductSearchPage assertKeywordIsSearched() throws InterruptedException {
        Assert.assertNotNull(productList, "Ürün listesi görüldü");
        System.out.println("ürün listesi görüldü ");
        Thread.sleep(1000);
        return this;
    }
    public ProductSearchPage assertProductDetailLoaded (){
        Assert.assertNotNull(productDetail, "Ürün detayları görüldü");
        System.out.println("ürün detayı görüldü ");
        return this;
    }


}