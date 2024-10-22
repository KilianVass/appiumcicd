package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsDetailsPage extends MenuPage {
    DriverManager driverManager = new DriverManager();
    TestUtils utils = new TestUtils();
    public ProductsDetailsPage(){
        PageFactory.initElements(new AppiumFieldDecorator(driverManager.getDriver()), this);
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sauce Labs Backpack\")")
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Sauce Labs Backpack\"")
    private WebElement SLBTitle;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.\"`]")
    private WebElement SLBText;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"$29.99\")")
    private WebElement SLBPrice;

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == \"test-BACK TO PRODUCTS\"`]")
    private WebElement backToProductsBtn;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"\uF099\"`]")
    private WebElement socialBtn;


    public String getSLBTitle(){
        return (getText(SLBTitle, "price is - "));

    }
    public String getSLBPrice(){
        String price = getText(SLBPrice, "price slb is - ");
        return price;
    }
    public ProductsDetailsPage scrollToSLBPrice(){
        String price = getText(SLBPrice, "price slb is - ");
        scrollToElementByText(driverManager.getDriver(),price);
        return this;
    }
    public Boolean isSocialBtnDisplayed(){
        return socialBtn.isDisplayed();
    }
    public void scrollPage(){
        //scrollGestureiOS(driver.get());
    }
    public String getSLBText(){
        return (getText(SLBText, "Slb txt is - "));

    }
    public ProductsPage pressBackToProducts(){
        click(backToProductsBtn);
        return new ProductsPage();
    }




}


