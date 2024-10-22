package com.qa.pages;

import com.qa.utils.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuPage extends BasePage {
    DriverManager driverManager = new DriverManager();

    public MenuPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driverManager.getDriver()), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(1)")
    @iOSXCUITFindBy(accessibility = "test-Menu")
    private WebElement settingsBtn;

    public SettingsPage pressSettingsBtn() {

        switch (params.getPlatformName()) {
            case "Android":
                utils.log().info("Estoy en switch android");
                click(settingsBtn);
                System.out.println(settingsBtn);
                break;
            case "iOS":
                utils.log().info("Estoy en switch iOS");
                tapByCoordinates(settingsBtn, 43, 60);
                System.out.println(settingsBtn);
                break;

        }
        return new SettingsPage();

    }
}
