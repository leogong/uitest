package com.qunar.autotest.uitest.pages;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;

import java.util.List;

/**
 * @author gonglin
 */
public class Ou99bbsPage extends BasePage {

    public Ou99bbsPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * @param
     * @return
     */
    public void goToListPage() {
        List<WebElement> elements = findElements(By.xpath("//dt/a"));
        int index = (int) (System.currentTimeMillis() % elements.size());
        index = index > 29 ? 29 : index;
        WebElement webElement = elements.get(index);
        System.out.println("进入列表页面 title=" + webElement.getText() + ",url=" + webElement.getAttribute("href") + ",index=" + index);
        webElement.click();
    }

    public void goToContentPage() {
        List<WebElement> webElementList;
        try {
            webElementList = findElements(By.xpath("//tbody[contains(@id,'normalthread_')]/tr/th/a"));
        } catch (TimeoutException e) {
            try {
                webElementList = findElements(By.xpath("//h3[@class='ptn']/a"));
                System.out.println("进入美图图片页面");
            } catch (TimeoutException e1) {
                webElementList = findElements(By.xpath("//dt/a"));
                System.out.println("进入地图专区页面");
            }
        }
        int index = (int) (System.currentTimeMillis() % webElementList.size());
        WebElement element = webElementList.get(index);
        System.out.println("进入内容页面 title=" + element.getText() + ",url=" + element.getAttribute("href") + ",index=" + index);
        element.click();
        /*
        * 切换窗口
        */

        try {
            switchToWindow(webDriver, element.getText());
            System.out.println("存在新窗口，切换窗口");
        } catch (StaleElementReferenceException e) {
            System.out.println("没有弹出新窗口");
        }
    }

    public boolean leveMessage(String msg) {
        try {
            WebElement textarea = findElement(By.xpath("//textarea[@name='message']"));
            textarea.clear();
            textarea.sendKeys(msg);
            System.out.println("开始留言，留言内容:" + msg);
            try {
                WebElement answerText = findElement(By.xpath("//input[@name='secanswer']"));
                System.out.println("找到留言问题");
                answerText.click();
                WebElement answerTip = findElement(By.xpath("//div[@class='mtm sec']/div/span"));
                String answer = answerTip.getText();
                answerText.sendKeys(StringUtils.substring(answer, answer.indexOf("“") + 1, answer.lastIndexOf("”")));
                System.out.println(answerTip.getText());
                System.out.println("回答问题");
                textarea.submit();
            } catch (TimeoutException e) {
                System.out.println("不用回答问题");
            }
        } catch (TimeoutException e) {
            System.out.println("没有找到留言框，重新开始");
            return false;
        }
        return true;
    }

    public void returnHomePage() {
        System.out.println("回到首页");
        WebElement element = findElement(By.xpath("//a[@title='BBS']"));
        element.click();
    }
}
