package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.ServerManager;
import com.qa.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {
    TestUtils utils = new TestUtils();
    DriverManager driverManager = new DriverManager();
    public LoginPage(){
        PageFactory.initElements(new AppiumFieldDecorator(driverManager.getDriver()), this);
    }

    @AndroidFindBy (accessibility = "test-Username")
    @iOSXCUITFindBy(accessibility = "test-Username")
    private WebElement usernameTxtFld;
    @AndroidFindBy (accessibility = "test-Password")
    @iOSXCUITFindBy(accessibility = "test-Password")
    private WebElement passwordTxtFld;
    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"test-LOGIN\")")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private WebElement loginBtn;
    @AndroidFindBy (uiAutomator = "new UiSelector().text(\"Username and password do not match any user in this service.\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == \"test-Error message\"`]")
    private WebElement errorMsg;


//Si no navegamos a otra pagina, devolvemos el mismo page, si navegaramos a otra, devolveriamos la otra page
    public LoginPage enterUsername(String username){
        clearTxt(usernameTxtFld);
        sendKeys(usernameTxtFld,username, "login with: "+ username);
        return this;
    }

    public LoginPage enterPassword(String password){
        clearTxt(passwordTxtFld);
        sendKeys(passwordTxtFld,password,"password is : "+ password);
        return this;
    }
    public ProductsPage login(String username, String password){
        enterUsername(username);
        enterPassword(password);
        return pressLoginBtn();

    }
    public ProductsPage pressLoginBtn(){
        click(loginBtn,"Press login button");
        return new ProductsPage();
    }
    public String getText(){

        return getText(errorMsg, "error text is - ");
    }



}


