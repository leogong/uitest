package com.qunar.autotest.uitest.pages;

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
import org.openqa.selenium.WebDriver;

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
                runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"select\")[0].value=\""
                        + TWO_LEVEL.get(specialName) + "\"");
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
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"select\")[0].value=\""
                    + sortValue + "\"");
        }
    }

    public ShoeHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void topNav(String navName) {
        /*
         * 通过JS控制IFRAME中元素
         */
        if (navName.equals("软件管理")) {
            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[2].children[1].children[0].children[0].children[1].click()");
        } else if (navName.equals("添加软件")) {
            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[2].children[1].children[0].children[0].children[0].click()");
        } else if (navName.equals("生成HTML")) {
            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[2].children[1].children[0].children[7].children[0].click()");
        } else if (navName.equals("生成首页")) {
            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[0].children[1].children[0].children[6].children[0].click()");
        } else if (navName.equals("生成地图")) {
            runScript("document.all(\"leftFrame\").contentWindow.document.body.children[0].children[0].children[1].children[0].children[1].children[0].children[6].children[1].click()");
        }
    }

    public void setAllHits(int num) {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementById(\"AllHits\").value=\"" + num
                + "\"");
    }

    public void setGood(int num) {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"good\")[0].value=\"" + num
                + "\"");
    }

    /**
     * 加粗、加颜色
     */
    public void setSpecial(String color) {
        /*
         * 默认为红色
         */
        String value = "1";
        if ("蓝色".equals(color))
            value = "2";
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"ColorMode\")[0].value=\""
                + value + "\"");

        /*
         * 字体直接加粗
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"FontMode\")[0].value=\"1\"");

    }

    public void setUpdateDate(String date) {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementById(\"SoftTime\").value=\"" + date
                + "\"");
    }

    /**
     * 点击常规设置按钮
     */
    public void generalSettings(int page) {
        if (page == 1)
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"btn1\")[0].click()");
        else if (page == 2)
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"btn_1\")[0].click()");

    }

    public void setSoftName(String name) {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementById(\"searchwordbox\").value=\""
                + name + "\"");
    }

    public void setSoftSize(double size) {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementById(\"filesize\").value=\"" + size
                + "\"");
    }

    public void setKBORMB(String sizeFlag) {
        int flag = 1;
        if (sizeFlag.equals("KB"))
            flag = 0;
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"SizeUnit\")[" + flag
                + "].click()");
    }

    public void setTags(String tags) {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementById(\"Tagstring\").value=\"" + tags
                + "\"");
    }

    public void setDownloadURL(DownLoad[] download) {
        for (int i = 0; i < download.length; i++) {
            /*
             * 不使用下载服务器
             */
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"downsid\")[" + i
                    + "].value=\"0\"");
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"DownAddress\")[" + i
                    + "].value=\"" + download[i].getUrl() + "\"");
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"SiteName\")[" + i
                    + "].value=\"" + download[i].getDesp() + "\"");
        }
    }

    public void setDesp(String desp) {
        /*
         * 点击代码
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"iframe\")[0].contentWindow.document.getElementById(\"NewCloud_CODE\").click()");
        /*
         * 添加内容
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"iframe\")[0].contentWindow.document.getElementsByTagName(\"textarea\")[0].value=\""
                + desp + "\"");

        /*
         * 点击设计
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"iframe\")[0].contentWindow.document.getElementById(\"NewCloud_EDIT\").click()");

    }

    /**
     * 点击其它属性按钮
     */
    public void otherSettings(int page) {
        if (page == 1)
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"btn2\")[0].click()");
        else if (page == 2)
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"btn_2\")[0].click()");
    }

    /**
     * 点击保存按钮
     */
    public void save(int page) {
        if (page == 1)
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"btn3\")[0].click()");
        else if (page == 2)
            runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"btn_3\")[0].click()");
    }

    public void goOn() {
        runScript("document.all(\"mainFrame\").contentWindow.document.body.childNodes[7].childNodes[1].childNodes[2].childNodes[1].childNodes[5].childNodes[0].click()");
    }

    /**
     * 置顶推荐
     */
    public void otherOption() {
        /*
         * 置顶
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"isTop\")[0].click()");
        /*
         * 推荐
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"isBest\")[0].click()");
    }

    public void addMoreDLURL(int num) {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"no\")[0].value=\"" + num
                + "\"");
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"no\")[0].nextSibling.nextSibling.click()");
    }

    public void setPicURL(String url) {
        /*
         * 软件预览图
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"Previewimg\")[0].value=\""
                + url + "\"");
        /*
         * 软件缩略图
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"SoftImage\")[0].value=\""
                + url + "\"");
    }

    public String getSoftLatestDate() throws ParseException {
        return (String) runScript("return document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"all\")[0].parentNode.nextSibling.nextSibling.childNodes[11].innerText");
    }

    public String getSoftLatestName() throws ParseException {

        return (String) runScript("return document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"all\")[0].parentNode.nextSibling.nextSibling.childNodes[3].childNodes[3].innerText");
    }

    public void createHTMLSort() {
        /*
         * 点击生成所有列表页面
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"maketype\")[1].click()");
        /*
         * 点击开始生成所有HTML
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"submit_button\")[0].click()");
    }

    public void createHTMLContent() {
        /*
         * 点击开始生成所有内容页面
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"maketype\")[3].click()");
        /*
         * 点击开始生成所有HTML
         */
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"submit_button\")[1].click()");
    }

    public void creataOtherList() {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"submit_button2\")[0].click()");
    }

    public void createsiteMap() {
        runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByName(\"submit_button\")[6].click()");
        runScript("document.all(\"mainFrame\").contentWindow.document.body.getElementsByTagName(\"a\")[0].click()");
    }

    public void switchToFrame(String id) {
        webDriver.switchTo().frame(id);
    }

    public void switchtoDefaultContent() {
        webDriver.switchTo().defaultContent();
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
            NodeList listNode = HtmlUtils.getNodeListByFilter(urlString, filter3, "gb2312");
            NodeList maplist = HtmlUtils.getNodeListByFilter(new TagNameFilter("li"), listNode.toHtml());

            nList.add(maplist);
            int maxSize = nList.size();
            for (int i = nList.size() - 1; i >= 0; i--) {
                Node lastMap = nList.elementAt(i);
                NodeList pList = HtmlUtils.getNodeListByFilter(pFilter, lastMap.toHtml());
                ParagraphTag p = (ParagraphTag) pList.elementAt(1);
                String dateString = p.getStringText().substring(p.getStringText().indexOf("</b>")+4);
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
        String total = HtmlUtils.getHtmlByUrl(url, "GB2312");
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
        NodeList pTagList = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("p"), new HasParentFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("id", "content")}))}), total);
        NodeList aTagList = HtmlUtils.getNodeListByFilter(new TagNameFilter("a"), pTagList.toHtml());
        for (int i = 0; i < aTagList.size(); i++) {
            String link = ((LinkTag) aTagList.elementAt(i)).getLink();
            pageBean.setDesp(pageBean.getDesp() + String.format(imgHtml, link.substring(link.indexOf("url=") + 4)));
        }
        //describe
        Node desNode = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[]{new TagNameFilter("div"), new HasAttributeFilter("class", "introduction")}), content.toHtml()).elementAt(0);
        pageBean.setDesp(pageBean.getDesp() + desNode.toHtml());
        //download
        NodeFilter iframeFilter = new TagNameFilter("iframe");
        NodeList iframeList = HtmlUtils.getNodeListByFilter(iframeFilter, content.toHtml());
        String iframeLink = ((IframeTag) iframeList.elementAt(0)).getAttribute("src");
        Node ddTotalNode = HtmlUtils.getNodeListByFilter(iframeLink, new TagNameFilter("dd"), "GB2312").elementAt(1);
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
        getMapList("2013-06-19","RG传奇4.0","http://war3.uuu9.com/Soft/List_22.shtml");
    }
}
