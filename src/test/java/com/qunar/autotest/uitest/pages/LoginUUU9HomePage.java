package com.qunar.autotest.uitest.pages;

import com.qunar.autotest.uitest.context.DataContext;
import com.qunar.autotest.uitest.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginUUU9HomePage extends BasePage {
    public LoginUUU9HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return "http://bbs.uuu9.com/";
    }

    public void login(User user) throws InterruptedException, IOException, ClassNotFoundException {
        findElement(By.id("satoplogind")).click();
        findElement(By.id("satopusername")).clear();
        findElement(By.id("satopusername")).sendKeys(user.getEmail());
        findElement(By.name("password")).clear();
        findElement(By.name("password")).sendKeys(user.getPassword());
        findElement(By.xpath("//input[@type='submit'][1]")).click();
    }
}
