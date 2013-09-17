package com.qunar.autotest.uitest;

import cucumber.annotation.After;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

public class WebDriverFacade {

    @SuppressWarnings("unchecked")
    private static Constructor<WebDriver> getDriverConstructor(String driver) {
        try {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    if (browser != null) {
                        browser.close();
                        browser.quit();
                    }
                }
            });
            return (Constructor<WebDriver>) Thread.currentThread().getContextClassLoader().loadClass(driver).getConstructor(Capabilities.class);
        } catch (Throwable problem) {
            problem.printStackTrace();
            throw new RuntimeException("Couldn't load " + driver, problem);
        }
    }

    private static WebDriver browser;

    public static WebDriver getWebDriver(String driver, String browserName, Platform platform) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        if (browser == null) {
            Constructor<WebDriver> webDriverConstructor = getDriverConstructor(driver);
            DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", platform);
            if (browserName.equals("internet explorer")) {
                capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            } else if (browserName.equals("firefox")) {
                System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
                FirefoxProfile profile = new FirefoxProfile();
                try {
                    profile.addExtension(WebDriverFacade.class, "firebug-1.9.1-fx.xpi");
                } catch (IOException e) {
                   e.printStackTrace();
                }
                profile.setPreference("extensions.firebug.currentVersion", "1.9.1");
                capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            } else if (browserName.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver",
                        "E:\\java tools\\chromedriver\\chromedriver.exe");
            }
            browser = webDriverConstructor.newInstance(capabilities);
            browser.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
        return browser;
    }


    @After
    public void closeBrowser() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if (browser != null) {
            browser.manage().deleteAllCookies();
        }
    }
}