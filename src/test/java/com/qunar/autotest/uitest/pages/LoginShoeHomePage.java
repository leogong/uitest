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
        return "http://" + DataContext.getUrl();
//        return "http://www.baidu.com";
    }

    public void login() throws InterruptedException, IOException, ClassNotFoundException {
        User user = SerializeToFile.getUser();
        findElement(By.id("AdminName")).sendKeys(user.getUsername());
        findElement(By.id("PassWord")).sendKeys(user.getPassword());
        findElement(By.id("checkcode")).click();
        Thread.sleep(1000 * 5);
        findElement(By.name("submit")).click();
//        chrome 测试出现乱码，修改使用firefox
//        WebElement element = findElement(By.xpath("//*[@id=\"kw\"]"));
//        element.click();
//        element.clear();
//        element.sendKeys("我");
    }
}
