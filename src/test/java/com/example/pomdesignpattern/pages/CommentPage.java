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

public class CommentPage extends BasePage {
    private WebDriver driver;

    public CommentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //scenario 1:
    @FindBy(xpath = "//*[@id='productReviewsTab']")
    WebElement productReviewsTab;
    @FindBy(xpath = ".//div[@class='wrapper']")
    WebElement commonReviewsTab;
    @FindBy(xpath = "//div[@class='arrowUpOrange']")
    WebElement orangeArrow;
    @FindBy(xpath = "//div[contains(text(),'En yeni değerlendirme')]")
    WebElement sortByNewest;
    @FindBy(xpath = "body.desktop.voltran-container.complete:nth-child(2) div.wrapper:nth-child(2) main.content-wrapper:nth-child(3) div.desktop.widget.AllReviews div.voltran-fragment:nth-child(1) div.hermes-voltran-body.voltran-body.Yorumlar:nth-child(2) div.hermes-Comments-module-GtxTIyZTsjhVzSOxiw4y div.hermes-Comments-module-_XCPNP_LCexLr_oIEq0t:nth-child(1) div.hermes-Comments-module-kV6VmHxTOAz2NZN1JIxw:nth-child(2) div.hermes-ReviewList-module-lDLEVdSJZHdHESARpQBj:nth-child(6) div.hermes-FiltersContainer-module-ELCvjqeomGNRLm_P2jzI div.hermes-FiltersContainer-module-mnQUatcxsv52cDedZndL div.hermes-FiltersContainer-module-yVJCt2Xel3B1vUPtdO7s div.hermes-Sort-module-X72cRt4Ta8Nj2Lb_GA6s div.hermes-Sort-module-VANnZ3_cDZVFx6SLhcdd div.hermes-Dropdown-module-oqU7GgoUy9rUfc59wGfF div.hermes-Sort-module-vJqiqyAGHsTNXjMsIwJD:nth-child(2) div.hermes-Sort-module-AUMlb9J2hoNuSMUxB3c8 > div.hermes-Sort-module-CWnJiGEufJWS8Y2TMNuF.hermes-Sort-module-F5wWWffLEvSW2rQuj0Pm:nth-child(2)")
    WebElement sortByNewest1;
    @FindBy(css = ".paginationContentHolder")
    WebElement evaluationsLoading;
    @FindBy(xpath = "//div[@class='hermes-ReviewCard-module-PbSfiSWIgfswGGBaOrw7']")
    WebElement thumbsButton;
    @FindBy(xpath = "//div[@class='thumbsUp hermes-ReviewCard-module-lOsa4wAwncdp3GgzpaaV']")
    WebElement thumbsUpButton;
    @FindBy(xpath = "//span[@class='hermes-ReviewCard-module-eFtSSTU4lYZLCnzHtzca' and contains(text(),'Teşekkür Ederiz')]")
    WebElement thankYouMessage;

    BasePage basePage = new BasePage(driver);

    public CommentPage chooseFirstFromThumbsButton() throws InterruptedException {
        scrollUntilToElement(commonReviewsTab, thumbsUpButton);
        Thread.sleep(2000);
        List<WebElement> thumbsButtons = driver.findElements((By.xpath(String.valueOf(thumbsButton))));
        if (!thumbsButtons.isEmpty()) {
            WebElement firstThumb = thumbsButtons.get(0);
            WebElement targetElement = firstThumb.findElement(By.xpath(String.valueOf(thumbsUpButton)));
            targetElement.click();
        }
        System.out.println("ThumbsUp seçeneğinine tıklandı");
        return this;
    }
    public CommentPage scrollUntilToReviewTab() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='wrapper']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", productReviewsTab);
        return this;
    }
    public CommentPage clickReviewTab() throws InterruptedException {
        clickAnElement(productReviewsTab);
        System.out.println("review tab görüldü ");
        Thread.sleep(10000);
        return this;
    }
    public CommentPage clickOrangeArrow() throws InterruptedException {
        scrollUntilToElement(orangeArrow,thumbsUpButton);
        clickAnElement(orangeArrow);
        Thread.sleep(5000);
        System.out.println("Filtreleme listesi açıldı(orangearrow) ");
        Thread.sleep(10000);
        return this;
    }
    public CommentPage clickNewestReviews() throws InterruptedException {
        chooseFromComboboxMoveToTwoDown(sortByNewest);
        Thread.sleep(5000);
        System.out.println("En yeniye göre sıralandı");
        return this;
    }
    public CommentPage assertReviewsLoadedAccordingToNewest() {
        Assert.assertNotNull(evaluationsLoading, "Değerlendirmeler yüklendi.");
        return this;
    }
    public CommentPage checkThanksMessage() throws InterruptedException {
        Thread.sleep(2000);
        String displayedMessage = verifyText(thankYouMessage);
        Assert.assertEquals(displayedMessage, "Teşekkür Ederiz.", "Teşekkür ederiz yazısı görünmedi.");
        System.out.println("Teşekkür Ederiz");
        return  this;
    }
}