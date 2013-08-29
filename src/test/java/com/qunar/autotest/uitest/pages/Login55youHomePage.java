package com.qunar.autotest.uitest.pages;

import com.qunar.autotest.uitest.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class Login55youHomePage extends BasePage {
    public Login55youHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return "http://bbs.55you.com/";
    }

    public void login(User user) throws InterruptedException, IOException, ClassNotFoundException {
        findElement(By.id("nav_pwuser")).click();
        findElement(By.id("nav_pwuser")).clear();
        findElement(By.id("nav_pwuser")).sendKeys(user.getUsername());
        WebElement showpwd = findElement(By.id("showpwd"));
        showpwd.clear();
        showpwd.sendKeys(user.getPassword());
        showpwd.submit();
    }
}
