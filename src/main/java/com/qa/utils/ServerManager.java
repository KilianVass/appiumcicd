package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.output.TeeOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ServerManager {
    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer(){
        return server.get();
    }

    public void startServer() throws FileNotFoundException {
        utils.log().info("Starting appium server");
        AppiumDriverLocalService server = getAppiumService();
        server.start();
        if (server == null || !server.isRunning()){
            utils.log().fatal("Appium server not started. ABORT!!!");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
        }
        int port = server.getUrl().getPort();
        utils.log().info("Appium server started on port: " + port);

        server.clearOutPutStreams();

        /*PrintStream console = System.out;
        FileOutputStream fos = new FileOutputStream("appium_logs.txt"); // Optional, for file logging
        TeeOutputStream myOut = new TeeOutputStream(console, fos);
        PrintStream teeStream = new PrintStream(myOut, true); // autoFlush
        System.setOut(teeStream);
        System.setErr(teeStream);*/

        this.server.set(server);
        GlobalParams.setPort(port);
        utils.log().info("Appium server started");

    }


    public AppiumDriverLocalService getAppiumServerDefault(){
        return AppiumDriverLocalService.buildDefaultService();
    }


    public AppiumDriverLocalService getAppiumService(){
        GlobalParams params = new GlobalParams();
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/opt/homebrew/bin/node"))
                .withAppiumJS(new File("/opt/homebrew/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withLogFile(new File(params.getPlatformName() + "_" + params.getDeviceName() + File.separator +
                        "Server.log"))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE));

    }
}