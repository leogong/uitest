package com.qunar.autotest.uitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author gonglin
 */
public class you55bbsPage extends BasePage {

    public you55bbsPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * @param
     * @return
     */
    public void goToListPage() {
        List<WebElement> elements = findElements(By.xpath("//a[@class='mr10']"));
        /*
         * 后面三个为事务管理
         */
        int index = (int) (System.currentTimeMillis() % elements.size());
        index = index > 9 ? index - 3 : index;
        index = index < 2 ? index + 2 : index;
        WebElement webElement = elements.get(index);
        System.out.println("进入列表页面 title=" + webElement.getText() + ",url=" + webElement.getAttribute("href") + ",index=" + index);
        webElement.click();
        try {
            List<WebElement> secondList = findElements(By.xpath("//a[contains(@id,'fn_')]"));
            index = (int) (System.currentTimeMillis() % secondList.size());
            WebElement secondElement = secondList.get(index);
            System.out.println("存在二级页面,进入二级页面 title=" + secondElement.getText() + ",url=" + secondElement.getAttribute("href") + ",index=" + index);
            secondElement.click();
        } catch (TimeoutException e) {
            System.out.println("没有二级页面");
        }
    }

    public void goToContentPage() {
        List<WebElement> webElementList = findElements(By.xpath("//tr[@class='tr4']/following-sibling::*/td[2]/a"));
        int index = (int) (System.currentTimeMillis() % webElementList.size());
        WebElement element = webElementList.get(index);
        System.out.println("进入内容页面 title=" + element.getText() + ",url=" + element.getAttribute("href") + ",index=" + index);
        element.click();
    }

    public boolean leveMessage(String msg) {
        try {
            WebElement textarea = findElement(By.tagName("textarea"));
            textarea.click();
            textarea.clear();
            textarea.sendKeys(msg);
            System.out.println("开始留言，留言内容:" + msg);
            textarea.submit();
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void returnHomePage() {
        System.out.println("回到首页");
        WebElement element = findElement(By.xpath("//a[text()='社区首页']"));
        element.click();
    }
}
