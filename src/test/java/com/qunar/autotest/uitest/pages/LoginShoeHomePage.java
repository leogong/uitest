package com.qunar.autotest.uitest.pages;

import com.qunar.autotest.uitest.context.DataContext;
import com.qunar.autotest.uitest.model.User;
import com.qunar.autotest.uitest.tools.SerializeToFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginShoeHomePage extends BasePage {
    public LoginShoeHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return "http://" + DataContext.getUrl() + "/admin/admin_login.asp";
    }

    public void login() throws InterruptedException, IOException, ClassNotFoundException {
        User user = SerializeToFile.getUser();
        findElement(By.id("AdminName")).sendKeys(user.getUsername());
        findElement(By.id("PassWord")).sendKeys(user.getPassword());
        findElement(By.id("checkcode")).click();
        Thread.sleep(1000 * 5);
        findElement(By.name("submit")).click();
    }
}
