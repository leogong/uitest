package com.qunar.autotest.uitest.pages;

import com.qunar.autotest.uitest.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class Loginou99HomePage extends BasePage {
    public Loginou99HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return "http://www.ou99.com/forum.php";
    }

    public void login(User user) throws InterruptedException, IOException, ClassNotFoundException {
        WebElement userName = findElement(By.id("ls_username"));
        userName.click();
        userName.clear();
        userName.sendKeys(user.getUsername());
        WebElement showpwd = findElement(By.id("ls_password"));
        showpwd.clear();
        showpwd.sendKeys(user.getPassword());
        showpwd.submit();
    }
}
