package com.qunar.autotest.uitest.pages;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

//import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public abstract class BasePage {
    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void go() {
        webDriver.get(getUrl());
    }

    public String getUrl() {
        return StringUtils.EMPTY;
    }

    protected WebElement findElement(final By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, 8);
        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(by);
            }
        });
        return webDriver.findElement(by);
    }

    protected List<WebElement> findElements(final By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, 8);
        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(by);
            }
        });
        return webDriver.findElements(by);
    }

    protected boolean isElementExist(final By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected Object runScript(String script) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        return executor.executeScript(script);
    }

    public void switchToWindow(WebDriver driver,String windowTitle){
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle))
                    continue;
                else {
                    driver.switchTo().window(s);
                    if (driver.getTitle().contains(windowTitle)) {
                        System.out.println("Switch to window: " + windowTitle + " successfully!");
                        break;
                    } else
                        continue;
                }
            }
        } catch (NoSuchWindowException e) {
            System.out.println("Window: " + windowTitle  + " cound not found!");
        }
    }

    public void switchToFrame(String id) {
        switchToDefaultContent();
        webDriver.switchTo().frame(id);
    }

    public void switchToDefaultContent() {
        webDriver.switchTo().defaultContent();
    }
}
