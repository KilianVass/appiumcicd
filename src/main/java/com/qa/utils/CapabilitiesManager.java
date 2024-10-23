package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class CapabilitiesManager {
    static TestUtils utils = new TestUtils();

    public static AppiumDriver getCaps() throws Exception {
        AppiumDriver driver;
        String sessionId;
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();
        URL url = new URL(props.getProperty("appiumURL")+params.getPort());
        utils.log().info(url);

        try{
            utils.log().info("Getting capabilities");
            switch (params.getPlatformName()){
                case "Android":
                    String appUrlRelativePath = props.getProperty("androidAppLocation");
                    String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                            "test" + File.separator + "resources" + File.separator + appUrlRelativePath;
                    utils.log().info(appUrl);
                    UiAutomator2Options optionsAnd = new UiAutomator2Options()
                            .setAppPackage(props.getProperty("androidAppPackage"))
                            .setAppActivity(props.getProperty("androidAppActivity"))
                            .setAvd(params.getDeviceName())
                            .setApp(appUrl)
                            .setSystemPort(Integer.parseInt(params.getSystemPort()))
                            .setChromedriverPort(Integer.parseInt(params.getChromeDriverPort()))
                            .setUdid(params.getUdid())
                            .setAvdLaunchTimeout(Duration.ofSeconds(Integer.parseInt(props.getProperty("andAvdLaunchTimeout"))))
                            .setAutomationName(props.getProperty("androidAutomationName"))
                            .setNewCommandTimeout(Duration.ofSeconds(120));
                    System.out.println(url);

                    driver = new AndroidDriver(url, optionsAnd);
                    sessionId = driver.getSessionId().toString();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;


                case "iOS":
                    String iOSAppUrlRelativePath = props.getProperty("iOSAppLocation");
                    String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                            "test" + File.separator + "resources" + File.separator + iOSAppUrlRelativePath;
                    XCUITestOptions optionsIos = new XCUITestOptions()
                            .setDeviceName(params.getDeviceName())
                            .setApp(iOSAppUrl)
                            .setUdid(params.getUdid())
                            .setAutomationName(props.getProperty("iOSAutomationName"))
                            .setBundleId(props.getProperty("iOSBundleId"))
                            .setWdaLocalPort(Integer.parseInt(params.getWdaLocalPort()))
                            .setNewCommandTimeout(Duration.ofSeconds(120));
                    System.out.println(url);


                    driver = new IOSDriver(url,optionsIos);
                    sessionId = driver.getSessionId().toString();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;

                default:
                    throw new Exception("Invalid platform! - " + params.getPlatformName());


            }
            return driver;
        }catch (Exception e){
            e.printStackTrace();
            utils.log().fatal("Failed to load server!! ABORT" + e.toString());
            throw e;
        }
    }
}
