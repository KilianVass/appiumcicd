package com.qa.pages;

import com.google.common.collect.ImmutableMap;
import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

import static java.sql.DriverManager.getDriver;

public class BasePage {
    private AppiumDriver driver;
    TestUtils utils = new TestUtils();
    GlobalParams params = new GlobalParams();
    Properties props = new Properties();

    public BasePage(){
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

    }
    public void waitForVisibility(WebElement e){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(WebElement e){
        waitForVisibility(e);
        e.click();
    }
    public void click(WebElement e, String msg){
        waitForVisibility(e);
        utils.log().info(msg);
        e.click();
    }
    public void tapByCoordinates(WebElement e, int x, int y){
        utils.log().info("tapped on :" +x+"and y:"+y);
        driver.executeScript("mobile: tap", ImmutableMap.of(
                "x", x,
                "y", y
        ));
    }
    public void sendKeys(WebElement e, String txt){
        waitForVisibility(e);
        e.sendKeys(txt);
    }
    public void sendKeys(WebElement e, String txt, String msg){
        waitForVisibility(e);
        utils.log().info(msg);
        e.sendKeys(txt);
    }
    public WebElement scrollToElementByText(AppiumDriver driver, String text) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"));"));
    }

    public WebElement scrollToTextContains_Android(AppiumDriver driver,String text) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"));

    }


    public WebElement scrollToId_Android(AppiumDriver driver,String id) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().resourceIdMatches(\".*id.*\"))"));

    }

    public static void scrollGestureiOS(AppiumDriver driver){
        WebElement element = driver.findElement(AppiumBy.className("XCUIElementTypeScrollView"));
        driver.executeScript("mobile: scroll", ImmutableMap.of(
                "element",((RemoteWebElement) element).getId(),"direction", "down"
        ));
    }

    public String getAttribute(WebElement e, String attribute){
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }
    public String getText(WebElement e, String msg){
        String txt = null;
        switch (params.getPlatformName()){
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
        }
        utils.log().info(msg + txt);

        return txt;
    }

    public void clearTxt(WebElement e){
        waitForVisibility(e);
        e.clear();
    }

    public void closeApp(){
        switch (params.getPlatformName()){
            case "Android":
                ((InteractsWithApps) driver).terminateApp(props.getProperty("androidAppPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) driver).terminateApp(props.getProperty("iOSBundleId"));

        }
    }
    public void launchApp(){
        switch (params.getPlatformName()){
            case "Android":
                ((InteractsWithApps) driver).activateApp(props.getProperty("androidAppPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) driver).activateApp(props.getProperty("iOSBundleId"));

        }
    }
}
