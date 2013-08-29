package com.qunar.autotest.uitest.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.*;
import org.htmlparser.util.NodeList;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.qunar.autotest.uitest.htmltags.IframeTag;
import com.qunar.autotest.uitest.model.DownLoad;
import com.qunar.autotest.uitest.model.PageBean;
import com.qunar.autotest.uitest.tools.HtmlUtils;

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

        /*
         * 增加二级目录栏目
         */
        TWO_LEVEL.put("澄海3C", "17");
        TWO_LEVEL.put("dota", "18");
        TWO_LEVEL.put("真三国无双", "21");
        TWO_LEVEL.put("历史对抗", "24");
        TWO_LEVEL.put("神话对抗", "25");
        TWO_LEVEL.put("其他对抗", "26");
        TWO_LEVEL.put("动漫对抗", "30");
        TWO_LEVEL.put("火影对抗", "32");
        TWO_LEVEL.put("武侠对抗", "33");

        TWO_LEVEL.put("守卫剑阁", "19");
        TWO_LEVEL.put("动漫防守", "22");
        TWO_LEVEL.put("神话防守", "23");
        TWO_LEVEL.put("其他防守", "27");
        TWO_LEVEL.put("火影防守", "28");
        TWO_LEVEL.put("历史防守", "29");
        TWO_LEVEL.put("武侠防守", "34");

        /*
         * 增加特殊地图
         */
        specialList.add("澄海3C");
        specialList.add("dota");
        specialList.add("真三国无双");
        specialList.add("守卫剑阁");
    }

    /**
     * 分类
     */
    public void setSort(String[] sort) {
        boolean flag = false;
        sort[2] = sort[2].toLowerCase();
        for (String specialName : specialList) {
            if (sort[2].startsWith(specialName)) {
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
            String sortValue = TWO_LEVEL.get(sort[1]);
            if (sortValue != null) {
                runScript("document.all(\"mainFrame\").contentWindow.document.getElementsByTagName(\"select\")[0].value=\""
                        + sortValue + "\"");
                flag = true;
            }
        }
        if (!flag) {
            String sortValue = ONE_LEVEL.get(sort[0]);
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

    public NodeList getMapList(String StringDate, String name, String url) throws Exception {
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
            classFilter = new HasAttributeFilter("class", "maplistbox");
            NodeFilter filter3 = new AndFilter(new NodeFilter[] { divFilter, classFilter });
            nList.add(HtmlUtils.getNodeListByFilter(urlString, filter3, "gb2312"));
            int maxSize = nList.size();
            for (int i = nList.size() - 1; i >= 0; i--) {
                Div lastMap = (Div) nList.elementAt(i);
                NodeList pList = HtmlUtils.getNodeListByFilter(pFilter, lastMap.toHtml());
                ParagraphTag p = (ParagraphTag) pList.elementAt(1);
                String dateString = p.getStringText();
                newDate = sf.parse(dateString.substring(5, dateString.length()));
                if (newDate.getTime() - oldDate.getTime() >= 0) {
                    if (i == maxSize - 1) {
                        classFilter = new HasAttributeFilter("class", "bottomtext");
                        NodeFilter divWithClass = new AndFilter(new NodeFilter[] { divFilter, classFilter });
                        NodeFilter parentFilter = new HasParentFilter(divWithClass);
                        NodeFilter newfilter6 = new AndFilter(new NodeFilter[] { parentFilter, aFilter });
                        NodeList newList = HtmlUtils.getNodeListByFilter(urlString, newfilter6, "gb2312");
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
                                new NodeFilter[] { new HasParentFilter(h3Filter), aFilter });
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

    public String getURLFromDiv(Div div) throws Exception {
        NodeList aList = HtmlUtils.getNodeListByFilter(new TagNameFilter("a"), div.toHtml());
        return ((LinkTag) aList.elementAt(0)).getLink();
    }

    public Alert getAlert() {
        return webDriver.switchTo().alert();
    }

    public PageBean getPageBean(String url) throws Exception {
        NodeFilter pFilter = new TagNameFilter("p");
        NodeFilter pParent;
        PageBean pageBean = new PageBean();
        NodeFilter aFilter = new TagNameFilter("a");
        NodeFilter classFilter = new HasAttributeFilter("class", "t-c f-14 m-10 dzpt cl");
        pParent = new HasParentFilter(new AndFilter(new NodeFilter[] { pFilter, classFilter }));
        NodeFilter aWithClass = new AndFilter(new NodeFilter[] { aFilter, pParent });
        String mapHtml = HtmlUtils.getHtmlByUrl(url, "GB2312");
        /*
         * 查询详情页
         */
        NodeList detailAList = HtmlUtils.getNodeListByFilter(aWithClass, mapHtml);
        if (detailAList == null || detailAList.elementAt(0) ==null) {
            detailAList = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[] {
                    aFilter,
                    new HasParentFilter(new AndFilter(new NodeFilter[] { pFilter,
                            new HasAttributeFilter("class", "t-c f-14 m-10") })) }), mapHtml);
        }
        String detailLink = ((LinkTag) detailAList.elementAt(0)).getLink();
        NodeFilter divFilter = new TagNameFilter("div");
        classFilter = new HasAttributeFilter("class", "infobox");
        NodeFilter divWithClass = new AndFilter(new NodeFilter[] { divFilter, classFilter });
        NodeList pList = HtmlUtils.getNodeListByFilter(pFilter, HtmlUtils.getNodeListByFilter(divWithClass, mapHtml)
                .elementAt(0).toHtml());
        ParagraphTag pSize = (ParagraphTag) pList.elementAt(0);
        String pStringText = pSize.getStringText();
        int m = pStringText.indexOf("M");
        m = m == -1 ? pStringText.indexOf(" ") : m;
        pageBean.setSoftSize(pStringText.substring(pStringText.indexOf("：") + 1, m));
        pageBean.setUpdateDate(pStringText.substring(pStringText.indexOf("间：") + 2, pStringText.length()));
        classFilter = new HasAttributeFilter("class", "title");
        divWithClass = new AndFilter(new NodeFilter[] { divFilter, classFilter });
        pParent = new HasParentFilter(divWithClass);
        NodeFilter aParent = new HasParentFilter(new AndFilter(new NodeFilter[] { pFilter, pParent }));
        NodeList sortList = HtmlUtils
                .getNodeListByFilter(new AndFilter(new NodeFilter[]{aFilter, aParent}), mapHtml);
        String[] sort = new String[3];
        sort[0] = ((LinkTag) sortList.elementAt(2)).getLinkText();
        if (sortList.size() == 4)
            sort[1] = ((LinkTag) sortList.elementAt(3)).getLinkText();
        NodeFilter h2Filter = new TagNameFilter("h2");
        classFilter = new HasAttributeFilter("class", "maptitle");
        NodeList titleList = HtmlUtils.getNodeListByFilter(new AndFilter(new NodeFilter[] { h2Filter, classFilter }),
                mapHtml);
        sort[2] = ((HeadingTag) titleList.elementAt(0)).getStringText();
        pageBean.setSort(sort);
        classFilter = new HasAttributeFilter("class", "logobox");
        NodeFilter imageFilter = new TagNameFilter("img");
        NodeFilter imageParent = new HasParentFilter(new AndFilter(new NodeFilter[] { divFilter, classFilter }));
        NodeList imageList = HtmlUtils.getNodeListByFilter(
                new AndFilter(new NodeFilter[] { imageFilter, imageParent }), mapHtml);
        pageBean.setPicURL(((ImageTag) imageList.elementAt(0)).getImageURL());
        NodeFilter iframeFilter = new TagNameFilter("iframe");
        NodeList iframeList = HtmlUtils.getNodeListByFilter(iframeFilter, mapHtml);
        String iframeLink = ((IframeTag) iframeList.elementAt(0)).getAttribute("src");
        NodeFilter liFilter = new TagNameFilter("li");
        NodeFilter aParentFilter = new HasParentFilter(liFilter);
        NodeList liList = HtmlUtils.getNodeListByFilter(iframeLink, new AndFilter(new NodeFilter[] {
                new TagNameFilter("a"), aParentFilter }), "GB2312");
        DownLoad[] dl = new DownLoad[liList.size()];
        for (int i = 0; i < liList.size(); i++) {
            LinkTag linkTag = (LinkTag) liList.elementAt(i);
            dl[i] = new DownLoad();
            String desp = linkTag.getStringText();
            dl[i].setDesp(desp);
            if (desp.startsWith("迅雷"))
                dl[i].setUrl(linkTag.getAttribute("onclick"));
            else
                dl[i].setUrl(linkTag.getLink());
        }
        pageBean.setDownload(dl);
        classFilter = new HasAttributeFilter("class", "f-14 m-10");
        NodeList despNodeList = HtmlUtils.getNodeListByFilter(detailLink, new AndFilter(pFilter, new HasParentFilter(
                new AndFilter(new NodeFilter[] { divFilter, classFilter }))), null);
        pageBean.setDesp(despNodeList.toHtml());
        return pageBean;
    }

}
