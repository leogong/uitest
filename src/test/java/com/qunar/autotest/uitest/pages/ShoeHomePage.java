package com.qunar.autotest.uitest.pages;

import com.qunar.autotest.uitest.context.DataContext;
import com.qunar.autotest.uitest.htmltags.IframeTag;
import com.qunar.autotest.uitest.model.DownLoad;
import com.qunar.autotest.uitest.model.PageBean;
import com.qunar.autotest.uitest.tools.HtmlUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.*;
import org.htmlparser.util.NodeList;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gonglin
 */
public class ShoeHomePage extends BasePage {
    /**
     * 一级目录
     */
    private static Map<String, String> ONE_LEVEL = new HashMap<String, String>();
    /**
     * 二级目录
     */
    private static Map<String, String> TWO_LEVEL = new HashMap<String, String>();
    /**
     * 特殊地图
     */
    private static List<String> specialList = new ArrayList<String>();

    private static String imgHtml = "<p align=\"center\"><img src=\"%s\" /></p>";

    static {
        /*
         * 增加一级目录栏目
         */
        ONE_LEVEL.put("对抗地图", "1");
        ONE_LEVEL.put("防守地图", "2");
        ONE_LEVEL.put("TD塔类图", "3");
        ONE_LEVEL.put("标准战役", "4");
        ONE_LEVEL.put("角色剧情", "6");
        ONE_LEVEL.put("工具补丁", "7");
        ONE_LEVEL.put("其它类型", "8");
        ONE_LEVEL.put("补丁", "15");
        ONE_LEVEL.put("地图包", "16");
        ONE_LEVEL.put("生存地图", "35");

        /*
         * 增加二级目录栏目
         */
        TWO_LEVEL.put("澄海3C", "38");
        TWO_LEVEL.put("dota", "39");
        TWO_LEVEL.put("真三国无双", "40");

        /*
         * 增加特殊地图
         */
        specialList.add("澄海3C");
        specialList.add("dota");
        specialList.add("真三国无双");
    }

    /**
     * 分类
     */
    public void setSort(PageBean pageBean) {
        boolean flag = false;
        String title = pageBean.getTitle();
        for (String specialName : specialList) {
            if (title.startsWith(specialName)) {
                findElement(By.xpath("//select[@id=\"classid\"]")).findElement(By.xpath("//option[@value=\"" + TWO_LEVEL.get(specialName) + "\"]")).click();
                otherSettings(1);
                setSpecial("红色");
                generalSettings(2);
                otherOption();
                flag = true;
                break;
            }
        }
        if (!flag) {
            String sortValue = ONE_LEVEL.get(pageBean.getSortLevel());
            findElement(By.xpath("//select[@id=\"classid\"]")).findElement(By.xpath("//option[@value=\"" + sortValue + "\"]")).click();

        }
    }

    public ShoeHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void topNav(String navName) {
        if (navName.equals("软件管理")) {
            findElement(By.xpath("//a[@id=\"class2\"]")).click();
        } else if (navName.equals("添加软件")) {
            findElement(By.xpath("//a[@id=\"add2\"]")).click();
        } else if (navName.equals("生成HTML")) {
            findElement(By.xpath("//a[@id=\"createHtml2\"]")).click();
        } else if (navName.equals("生成首页")) {
            findElement(By.xpath("//a[@id=\"createIndex\"]")).click();
        } else if (navName.equals("生成地图")) {
            findElement(By.xpath("//a[@id=\"siteMap\"]")).click();
        }

        /*
         * 通过JS控制IFRAME中元素
         */
//        if (navName.equals("软件管理")) {
//            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[2].children[1].children[0].children[0].children[1].click()");
//        } else if (navName.equals("添加软件")) {
//            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[2].children[1].children[0].children[0].children[0].click()");
//        } else if (navName.equals("生成HTML")) {
//            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[2].children[1].children[0].children[7].children[0].click()");
//        } else if (navName.equals("生成首页")) {
//            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[0].children[1].children[0].children[6].children[0].click()");
//        } else if (navName.equals("生成地图")) {
//            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[0].children[1].children[0].children[6].children[1].click()");
//        }

    }

    public void setAllHits(int num) {
        findElement(By.xpath("//input[@id=\"AllHits\"]")).sendKeys(String.valueOf(num));
    }

    public void setGood(int num) {
        findElement(By.xpath("//input[@name=\"good\"]")).sendKeys(String.valueOf(num));
    }

    /**
     * 加粗、加颜色
     */
    public void setSpecial(String color) {
        /*
         * 默认为红色
         */
        String value = "1";
        if ("蓝色".equals(color)) {
            value = "2";
        }
        findElement(By.xpath("//select[@id=\"ColorMode\"]")).findElement(By.xpath("//option[@value=\"" + value + "\"]")).click();

        /*
         * 字体直接加粗
         */
        findElement(By.xpath("//select[@id=\"FontMode\"]")).findElement(By.xpath("//option[@value=\"1\"]")).click();
    }

    public void setUpdateDate(String date) {
        findElement(By.xpath("//input[@name=\"SoftTime\"]")).sendKeys(date);
    }

    /**
     * 点击常规设置按钮
     */
    public void generalSettings(int page) {
        if (page == 1)
            findElement(By.xpath("//input[@id=\"generalBtn1\"]")).click();
        else if (page == 2)
            findElement(By.xpath("//input[@id=\"generalBtn_1\"]")).click();
    }

    public void setSoftName(String name) throws UnsupportedEncodingException {
        findElement(By.xpath("//*[@id=\"searchwordbox\"]")).sendKeys(new String(name.getBytes(), "utf8"));
    }

    public void setSoftSize(double size) {
        findElement(By.xpath("//input[@name=\"SoftSize\"]")).sendKeys(String.valueOf(size));
    }

    public void setKbOrMb(String sizeFlag) {
        int flag = 1;
        if (sizeFlag.equals("KB"))
            flag = 0;
        findElements(By.xpath("//input[@name=\"SizeUnit\"]")).get(flag).click();
    }

    public void setTags(String tags) {
        findElement(By.xpath("//input[@id=\"Tagstring\"]")).sendKeys(tags);
    }

    public void setDownloadURL(DownLoad[] download) {
        for (int i = 0; i < download.length; i++) {
            /*
             * 不使用下载服务器
             */
            findElement(By.xpath("//select[@name=\"downsid\"]")).findElement(By.xpath("//option[@value=\"0\"]")).click();
            findElement(By.xpath("//input[@id=\"DownAddress" + i + "\"]")).sendKeys(download[i].getUrl());
            findElements(By.xpath("//input[@name=\"SiteName\"]")).get(i).sendKeys(download[i].getDesp());
        }
    }

    public void setDesp(String desp) {
        /*
         * 点击代码
         */
//        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"iframe\")[0].contentWindow.document.getElementById(\"NewCloud_CODE\").click()");
        /*
         * 添加内容
         */
//        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"iframe\")[0].contentWindow.document.getElementsByTagName(\"textarea\")[0].value=\""
//                + desp + "\"");

        /*
         * 点击设计
         */
//        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"iframe\")[0].contentWindow.document.getElementById(\"NewCloud_EDIT\").click()");
        // 直接添加HTML内容
        switchToFrame(findElement(By.xpath("//iframe[@id=\"content___Frame\"]")));
        switchToFrame(findElement(By.xpath("//iframe")));
        findElement(By.xpath("//body")).sendKeys(desp);
        switchToFrame("mainFrame");
    }

    /**
     * 点击其它属性按钮
     */
    public void otherSettings(int page) {
        if (page == 1)
            findElement(By.xpath("//input[@id=\"otherSetting1\"]")).click();
        else if (page == 2)
            findElement(By.xpath("//input[@id=\"otherSetting2\"]")).click();
    }

    /**
     * 点击保存按钮
     */
    public void save(int page) {
        if (page == 1)
            findElement(By.xpath("//input[@name=\"btn3\"]")).click();
        else if (page == 2)
            findElement(By.xpath("//input[@name=\"btn_3\"]")).click();
    }

    public void goOn() {
        findElement(By.xpath("//a[@id=\"addPost2\"])")).click();
    }

    /**
     * 置顶推荐
     */
    public void otherOption() {
        /*
         * 置顶
         */
        findElement(By.xpath("//input[@id=\"isTop\"]")).click();
        /*
         * 推荐
         */
        findElement(By.xpath("//input[@id=\"isBest\"]")).click();
    }

    public void addMoreDLURL(int num) {
        WebElement element = findElement(By.xpath("//input[@name=\"no\"]"));
        element.clear();
        element.sendKeys(String.valueOf(num));
        findElement(By.xpath("//input[@id=\"addDLBtn\"]")).click();
    }

    public void setPicURL(String url) {
        /*
         * 软件预览图
         */
        findElement(By.xpath("//input[@id=\"Previewimg\"]")).sendKeys(url);
        /*
         * 软件缩略图
         */
        findElement(By.xpath("//input[@id=\"ImageUrl\"]")).sendKeys(url);
    }

    public String getSoftLatestDate() throws ParseException {
        return findElement(By.xpath("//td[@id=\"lastDate1\"]")).getText();
//        return (String) runScript("return document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"all\")[0].parentNode.nextSibling.nextSibling.childNodes[11].innerText");
    }

    public String getSoftLatestName() throws ParseException {
        return findElement(By.xpath("//a[@id=\"lastName1\"]")).getText();
//        return (String) runScript("return document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"all\")[0].parentNode.nextSibling.nextSibling.childNodes[3].childNodes[3].innerText");
    }

    public void createHTMLSort() {
        /*
         * 点击生成所有列表页面
         */
        findElement(By.xpath("//input[@id=\"generateAllSortList\"]")).click();
        /*
         * 点击开始生成所有HTML
         */
        findElement(By.xpath("//input[@id=\"sortSubmit\"]")).click();
    }

    public void createHTMLContent() {
        /*
         * 点击开始生成所有内容页面
         */
        findElement(By.xpath("//input[@id=\"generateAllContentList\"]")).click();
        /*
         * 点击开始生成所有HTML
         */
        findElement(By.xpath("//input[@id=\"contentSubmit\"]")).click();
    }

    public void createOtherList() {
        findElement(By.xpath("//input[@id=\"generateOtherHtml\"]")).click();
    }

    public void createSiteMap() {
        findElement(By.xpath("//input[@id=\"generateSiteMap\"]")).click();
        /*
         * 点击生成地图后进入的新页面
         */
        findElement(By.xpath("//a[1]")).click();
    }

    public static NodeList getMapList(String StringDate, String name, String url) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date oldDate = sf.parse(StringDate);
        Date newDate;
        String urlString = url;
        NodeList nList = new NodeList();
        NodeFilter pFilter = new TagNameFilter("p");
        NodeFilter divFilter = new TagNameFilter("div");
        NodeFilter h3Filter = new TagNameFilter("h3");
        NodeFilter aFilter = new TagNameFilter("a");
        NodeFilter classFilter;
        boolean flag;
        do {
            flag = false;
            classFilter = new HasAttributeFilter("class", "maplist");
            NodeFilter filter3 = new AndFilter(new NodeFilter[]{divFilter, classFilter});
            NodeList listNode = HtmlUtils.getNodeListByFilter(urlString, filter3, "gbk");
            NodeList maplist = HtmlUtils.getNodeListByFilter(new TagNameFilter("li"), listNode.toHtml());

            nList.add(maplist);
            int maxSize = nList.size();
            for (int i = nList.size() - 1; i >= 0; i--) {
                Node lastMap = nList.elementAt(i);
                NodeList pList = HtmlUtils.getNodeListByFilter(pFilter, lastMap.toHtml());
                ParagraphTag p = (ParagraphTag) pList.elementAt(1);
                String dateString = p.getStringText().substring(p.getStringText().indexOf("</b>") + 4);
                newDate = sf.parse(dateString);
                if (newDate.getTime() - oldDate.getTime() >= 0) {
                    if (i == maxSize - 1) {
                        NodeList newList = HtmlUtils.getNodeListByFilter(new TagNameFilter("a"), listNode.toHtml());
                        for (int j = 0; j < newList.size(); j++) {
                            LinkTag linktag = (LinkTag) newList.elementAt(j);
                            if (linktag.getLinkText().equals("下一页")) {
                                urlString = "http://war3.uuu9.com" + linktag.getLink();
                                System.out.println("go to next page : " + urlString);
                                flag = true;
                                break;
                            }
                        }
                        if (flag)
                            break;
                    } else if (newDate.getTime() - oldDate.getTime() == 0) {
                        NodeFilter nodefilter = new AndFilter(
                                new NodeFilter[]{new HasParentFilter(h3Filter), aFilter});
                        NodeList nameList = HtmlUtils.getNodeListByFilter(nodefilter, lastMap.toHtml());
                        LinkTag aTag = (LinkTag) nameList.elementAt(0);
                        if (aTag.getStringText().toLowerCase().equals(name)) {
                            nList.remove(i);
                            break;
                        } else
                            nList.remove(i);
                    }
                } else if (newDate.getTime() - oldDate.getTime() < 0) {
                    nList.remove(i);
                }
            }
        } while (flag);

        return nList;
    }

    public String getURLFromDiv(Node div) throws Exception {
        NodeList aList = HtmlUtils.getNodeListByFilter(new TagNameFilter("a"), div.toHtml());
        return ((LinkTag) aList.elementAt(0)).getLink();
    }

    public Alert getAlert() {
        return webDriver.switchTo().alert();
    }

    public static PageBean getPageBean(String url) throws Exception {
//

        PageBean pageBean = new PageBean();
        //content part
        String total = HtmlUtils.getHtmlByUrl(url, "GBK");
        Node content = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"),
                new HasAttributeFilter("class", "content")}), total).elementAt(0);
        //title
        NodeList nodeListByFilter = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("class", "toptext")}), content.toHtml());
        if (nodeListByFilter.elementAt(0) == null) {
            nodeListByFilter = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("class", "content")}), content.toHtml());
        }
        Node titleNode = nodeListByFilter.elementAt(0);
        NodeList h1 = HtmlUtils.getNodeListByFilter(new TagNameFilter("h1"), titleNode.toHtml());
        if (h1.elementAt(0) == null) {
            h1 = HtmlUtils.getNodeListByFilter(new TagNameFilter("h2"), titleNode.toHtml());
        }
        pageBean.setTitle(((HeadingTag) h1.elementAt(0)).getStringText());
        //image
        Node picNode = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("class", "picbox l")}), content.toHtml()).elementAt(0);
        if (picNode == null) {
            System.out.println("skip this old one!");
            return null;
        }
        pageBean.setPicURL(((ImageTag) HtmlUtils.getNodeListByFilter(new TagNameFilter("img"), picNode.toHtml()).elementAt(0)).getImageURL());
        //softSize
        Node blockNode = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("class", "mation r")}), content.toHtml()).elementAt(0);
        NodeList span = HtmlUtils.getNodeListByFilter(new TagNameFilter("span"), blockNode.toHtml());
        Node softSizeNode = span.elementAt(0);
        String softSize = softSizeNode.toPlainTextString();
        pageBean.setSoftSize(softSize.substring(softSize.indexOf("：") + 1, softSize.indexOf("MB")));
        //update date
        String dateString = span.elementAt(4).toPlainTextString();
        String date = dateString.substring(dateString.indexOf("：") + 1);
        pageBean.setUpdateDate(date);
        //pic content
//        NodeList pTagList = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("p"), new HasParentFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("id", "content")}))}), total);
//        NodeList aTagList = HtmlUtils.getNodeListByFilter(new TagNameFilter("a"), pTagList.toHtml());
//        for (int i = 0; i < aTagList.size(); i++) {
//            String link = ((LinkTag) aTagList.elementAt(i)).getLink();
//            pageBean.setDesp(pageBean.getDesp() + String.format(imgHtml, link.substring(link.indexOf("url=") + 4)));
//        }
        //describe
        Node desNode = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("class", "introduction")}), content.toHtml()).elementAt(0);
        pageBean.setDesp(desNode.toPlainTextString().replaceAll("\\w+\\.\\w+\\.com(/.*)?", DataContext.getUrl().substring(0, 14)));
        //download
        NodeFilter iframeFilter = new TagNameFilter("iframe");
        NodeList iframeList = HtmlUtils.getNodeListByFilter(iframeFilter, content.toHtml());
        String iframeLink = ((IframeTag) iframeList.elementAt(0)).getAttribute("src");
        Node ddTotalNode = HtmlUtils.getNodeListByFilter(iframeLink, new TagNameFilter("dd"), "GBK").elementAt(1);
        NodeList ddList = HtmlUtils.getNodeListByFilter(new TagNameFilter("a"), ddTotalNode.toHtml());
        DownLoad[] ddlist = new DownLoad[ddList.size()];
        for (int i = 0; i < ddList.size(); i++) {
            LinkTag dd = (LinkTag) ddList.elementAt(i);
            DownLoad d = new DownLoad();
            d.setDesp(dd.getLinkText());
            d.setUrl(dd.getLink());
            ddlist[i] = d;
        }
        pageBean.setDownload(ddlist);
        //sort level
        LinkTag sortLevel = (LinkTag) HtmlUtils.getNodeListByFilter(new TagNameFilter("a"), HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("class", "bord detail m-10")}), total).toHtml()).elementAt(2);

        pageBean.setSortLevel(sortLevel.getLinkText());


//        System.out.println(pageBean);


        return pageBean;
    }

    public static void main(String[] args) throws Exception {
        String aa = "地图简介：巨虾经典克隆，不可不玩！ http://dnarpg.uuu9.com QQ群：256449311 游戏介绍 新版本科隆将带您突破爽的极限 多样对战塑造崭新快感！ 无限武器道具接近暗黑系统！ 超武侠世界,创造三国神将！ 踏上你的王者之路，体验勇者的幻想惊奇冒险？ 更新如下： “八面玲珑”全面刷怪模式，挑战全新防守方式，所以版本号1.2直接跳级为1.8。 在原由防守的35波数基础上，特别加入了首创新生版对抗防守双倍波数测试模式！ 1.增加键盘奥义冰，雷！ 2.新增加敌对英雄装备掉落率！ 3.农民价格调整至350！ 4.调整同盟关系，初始设置玩家结盟关系！ 5.扩大刷怪距离！ 关注下一个特别2.0版本的预新资料。 大幅度引用FOOTMAN战术类型，原始基地采用刷兵模式，基地升级刷新兵种，基地死亡将游戏结束，2v2v2v2精彩pk模式。打造百人千人大混战！ 科隆何等强大的装备合成升级种类，强大的佣兵刷新多配合系统，英雄踏建造防卸优势等等。 想必不用我多说，光想想就有一种玩的冲劲.... 由于时间因素原因，特别2.0版本再一次将无法制作完成发布，可能再次要流创！ 目前复创版1.82两个滋生版本供玩家娱乐下载 科隆-勇者的幻想防守对抗测试版1.0 http://bbs.uuu9.com/thread-10295635-1-3.html 科隆-勇者的幻想自控对抗测试版1.0 http://bbs.uuu9.com/thread-10301559-1-2.html...";
        System.out.println(aa.replaceAll("\\w+\\.\\w+\\.com(/.*)?", "www.39shoe.com"));
    }
}
