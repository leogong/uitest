package com.qunar.autotest.uitest.pages;

import com.qunar.autotest.uitest.context.DataContext;
import com.qunar.autotest.uitest.model.User;
import com.qunar.autotest.uitest.tools.SerializeToFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class LoginShoeHomePage extends BasePage {
    public LoginShoeHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return "http://" + DataContext.getUrl();
//        return "http://www.so.com";
    }

    public void login() throws InterruptedException, IOException, ClassNotFoundException {
        User user = SerializeToFile.getUser();
        WebElement adminName = findElement(By.id("AdminName"));
        adminName.clear();
        adminName.sendKeys(user.getUsername());
        WebElement passWord = findElement(By.id("PassWord"));
        passWord.clear();
        passWord.sendKeys(user.getPassword());
        WebElement checkCode = findElement(By.id("checkcode"));
        checkCode.clear();
        checkCode.click();
        Thread.sleep(1000 * 5);
        findElement(By.name("submit")).click();
//        chrome 测试出现乱码，修改使用firefox
//        WebElement element = findElement(By.xpath("//*[@id=\"input\"]"));
//        element.click();
//        element.clear();
//        element.sendKeys("我");
    }
}
