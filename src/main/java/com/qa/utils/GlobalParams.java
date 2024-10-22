package com.qa.utils;

import net.bytebuddy.implementation.bytecode.Throw;

public class GlobalParams {
    private static ThreadLocal<String> platformName = new ThreadLocal<String>();
    private static ThreadLocal<String> udid = new ThreadLocal<String>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<String>();
    private static ThreadLocal<String> systemPort = new ThreadLocal<String>();
    private static ThreadLocal<String> chromeDriverPort = new ThreadLocal<String>();
    private static ThreadLocal<String> wdaLocalPort = new ThreadLocal<String>();
    private static ThreadLocal<String> webKitDebugProxyPort = new ThreadLocal<String>();
    private static ThreadLocal<Integer> port = new ThreadLocal<Integer>();

    public static void setPort(int port1){
        port.set(port1);
    }
    public int getPort(){
        return port.get();
    }

    public void setPlatformName(String platformName1){
        platformName.set(platformName1);
    }
    public String getPlatformName(){
        return platformName.get();
    }
    public void setUdid(String udid1){
        udid.set(udid1);
    }
    public String getUdid(){
        return udid.get();
    }
    public void setDeviceName(String deviceName1){
        deviceName.set(deviceName1);
    }
    public String getDeviceName(){
        return deviceName.get();
    }
    public void setSystemPort(String systemPort1){
        systemPort.set(systemPort1);
    }
    public String getSystemPort(){
        return systemPort.get();
    }
    public void setChromeDriverPort(String chromeDriverPort1){
        chromeDriverPort.set(chromeDriverPort1);
    }
    public String getChromeDriverPort(){
        return chromeDriverPort.get();
    }
    public void setWdaLocalPort(String wdaLocalPort1){
        wdaLocalPort.set(wdaLocalPort1);
    }
    public String getWdaLocalPort(){
        return wdaLocalPort.get();
    }
    public void setWebKitDebugProxyPort(String webKitDebugProxyPort1){
        webKitDebugProxyPort.set(webKitDebugProxyPort1);
    }
    public String getWebKitDebugProxyPort(){
        return webKitDebugProxyPort.get();
    }

    public void initializeGlobalParams(){
        TestUtils utils = new TestUtils();
        GlobalParams params = new GlobalParams();
        params.setPlatformName(System.getProperty("platformName", "Android"));
        params.setUdid(System.getProperty("udid", "emulator-5554"));
        params.setDeviceName(System.getProperty("deviceName", "Pixel_8_Pro"));

        utils.log().info("platform is :" + params.getPlatformName());

        switch (params.getPlatformName()){
            case "Android":
                params.setSystemPort(System.getProperty("systemPort", "10000"));
                params.setChromeDriverPort(System.getProperty("chromeDriverPort", "11000"));
                break;
            case "iOS":
                params.setWdaLocalPort(System.getProperty("wdaLocalPort", "10001"));
                params.setWebKitDebugProxyPort(System.getProperty("webKitDebugProxyPort", "11001"));
                break;
            default:
                throw new IllegalStateException("Invalid Platform Name!");
        }


    }

}
