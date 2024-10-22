package com.qa.pages;

import com.qa.utils.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends MenuPage {
    DriverManager driverManager = new DriverManager();

    public ProductsPage(){
        PageFactory.initElements(new AppiumFieldDecorator(driverManager.getDriver()), this);
    }

    @AndroidFindBy (xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"PRODUCTS\"`]")
    private WebElement productsTitleText;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sauce Labs Backpack\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"test-Item title\"`][1]")
    private WebElement SLBTitle;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"$29.99\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"test-Price\"`][1]")
    private WebElement SLBPrice;



    //Si no navegamos a otra pagina, devolvemos el mismo page, si navegaramos a otra, devolveriamos la otra page
    public String getTitle(){
        return (getText(productsTitleText, "title is - "));

    }

    public String getSLBTitle(){
        return (getText(SLBTitle, "title slb is - "));

    }
    public String getSLBPrice(){
        return (getText(SLBPrice, "slb price is - "));

    }
    public ProductsDetailsPage pressSLBTitle(){
        click(SLBTitle);
        return new ProductsDetailsPage();
    }




}


