package com.qa.pages;


import com.qa.utils.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends BasePage {
    DriverManager driverManager = new DriverManager();
    public SettingsPage(){
        PageFactory.initElements(new AppiumFieldDecorator(driverManager.getDriver()), this);
    }
    @AndroidFindBy(accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-LOGOUT\"]")
    private WebElement logoutBtn;
    public LoginPage pressLogoutBtn(){
        System.out.println(logoutBtn);
        click(logoutBtn);
        return new LoginPage();
    }
}
