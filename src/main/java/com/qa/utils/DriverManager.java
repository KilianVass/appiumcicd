package com.qa.utils;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;

public class DriverManager {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver(){
        return driver.get();
    }
    public void setDriver(AppiumDriver driver2){
        driver.set(driver2);
    }
    public void initializeDriver() throws Exception {
        GlobalParams params = new GlobalParams();
        PropertyManager props = new PropertyManager();

        AppiumDriver driver = null;

        if(driver == null){
            try{
                utils.log().info("Initializing appium driver");
                driver = CapabilitiesManager.getCaps();
                if(driver == null){
                    throw new Exception("Driver is null");
                }
                utils.log().info("Driver is initialized");
                this.driver.set(driver);
            }catch (IOException e){
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure");
                throw e;
            }
        }



    }

}
