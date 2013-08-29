package com.qunar.autotest.uitest.extjs;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Ext4Selenium {
    public static WebElement findElement(String text, ExtType type, WebDriver webDriver) {
        String id = findComponentIds(text, type, webDriver);
        if (id.contains(",")) {
            id = id.split(",")[0];
        }
        return webDriver.findElement(By.id(id));
    }

    public List<WebElement> findElements(String text, ExtType type, WebDriver webDriver) {
        List<WebElement> result = new ArrayList<WebElement>();
        String ids = findComponentIds(text, type, webDriver);
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            if (StringUtils.isNotBlank(id)) {
                result.add(webDriver.findElement(By.id(id)));
            }
        }
        return result;
    }

    public static void injectJavascript(String js, WebDriver webDriver) {
        String scriptTag = "var scriptTag = document.createElement('script');" +
                "scriptTag.id='ext-selenium-helper';" +
                "scriptTag.type='text/javascript';";
        if (webDriver instanceof InternetExplorerDriver) {
            scriptTag += "scriptTag.text = \"" + js + "\";";
        } else {
            scriptTag += "var content = document.createTextNode(\"" + js + "\");" +
                    "scriptTag.appendChild(content);";
        }
        scriptTag += "var head = document.getElementsByTagName('head')[0];" +
                "head.appendChild(scriptTag);";
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript(scriptTag);
    }

    private static void injectJavascriptHelper(WebDriver webDriver) {
        final InputStream in = Ext4Selenium.class.getResourceAsStream("ExtSeleniumHelper.js");
        String js = inputStream2UTF8(in);
        injectJavascript(js,webDriver);
    }

    private static String findComponentIds(String text, ExtType type, WebDriver webDriver) {
        injectJavascriptHelper(webDriver);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        return (String) executor.executeScript(String.format("return findComponentIds('%s', '%s');", text, type.name()));
    }

    private static String inputStream2UTF8(final InputStream in) {
        StringBuilder result = new StringBuilder();
        try {
            List<String> lines = IOUtils.readLines(in);
            for (String line : lines) {
                result.append(line);
            }
        } catch (IOException e) {
            return null;
        }
        return result.toString();
    }
}
