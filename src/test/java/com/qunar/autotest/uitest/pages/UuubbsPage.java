package com.qunar.autotest.uitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author gonglin
 */
public class UuubbsPage extends BasePage {

    public UuubbsPage(WebDriver webDriver) {
        super(webDriver);
    }


    /**
     * @param sort   有两种类型
     * @param index1 0-25
     * @param index2 0-11
     */
    public boolean goToListPage(long sort, long index1, long index2) {
        System.out.println("sort=" + sort + ",index1=" + index1 + ",index2=" + index2);
//        sort = 1;
//        index1 = 27;
//        index2 = 3;
        if (sort == 0) {
            /*
             *  前4个不在列表页面中
             */
            WebElement element = findElements(By.className("clearfix")).get((int) index1).findElements(By.tagName("a")).get(1);
            if (element.getText().equals("其他热门推荐")) {
                System.out.println("进入“其他热门推荐”，重新选择");
                return false;
            } else {
                System.out.println("进入列表页面 title=" + element.getText() + ",url=" + element.getAttribute("href"));
                element.click();
            }
        } else {
            /*
             * 13，14版块是事务处理
             */
            WebElement element = findElements(By.xpath("//div[contains(@class,'textbox')]")).get((int) index2).findElements(By.tagName("a")).get(0);
            System.out.println("进入列表页面 title=" + element.getText() + ",url=" + element.getAttribute("href"));
            element.click();
        }
        /*
         * 有可能在二级栏目选择
         */
        try {
            List<WebElement> webElementList = findElements(By.xpath("//div[@id='subforum']//div[2]/table/tbody/tr/th/div/h2/a"));
            WebElement element = webElementList.get((int) (System.currentTimeMillis() % webElementList.size()));
            System.out.println("存在二级页面，进入二级页面 title=" + element.getText() + ",url=" + element.getAttribute("href"));
            element.click();
        } catch (TimeoutException e) {
            System.out.println("没有二级页面");
        }
        return true;
    }

    public void goToContentPage() {
        List<WebElement> webElementList = findElements(By.xpath("//tbody[contains(@id,'normalthread_')]/tr/th/span[1]/a"));
        WebElement element = webElementList.get((int) (System.currentTimeMillis() % webElementList.size()));
        System.out.println("进入内容页面 title=" + element.getText() + ",url=" + element.getAttribute("href"));
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
        WebElement element = findElement(By.xpath("//div[@id='nav']/a[1]"));
        element.click();
    }
}
