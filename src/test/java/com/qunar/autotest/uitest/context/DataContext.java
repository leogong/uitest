package com.qunar.autotest.uitest.context;

import com.qunar.autotest.uitest.tools.FileReadWrite;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataContext {
    private static Map<String, String> dataMap = new HashMap<String, String>();
    private static List<String> keyWords = new ArrayList<String>();
    private static String urlMessage = StringUtils.EMPTY;
    private static String commonMessage = "求地图，求美女图片2，哈哈";

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DataContext.url = url;
    }

    private static String url = StringUtils.EMPTY;

    static {
        try {
            keyWords.addAll(FileReadWrite.getKeyWordsList("src/main/resources/com/qunar/autotest/uitest/keywords.txt"));
            url = FileReadWrite.getKeyWordsList("src/main/resources/com/qunar/autotest/uitest/url.txt").iterator().next();
            urlMessage = "[url=" + url + "][color=White]%s[/color][/url]";
        } catch (IOException e) {
            System.out.println("读取keywords文件失败");
        }
    }

    public static String getDataMap(String key) {
        return dataMap.get(key);
    }

    public static void setDataMap(String key, String value) {
        dataMap.put(key, value);
    }

    public static List<String> getKeyWords() {
        return keyWords;
    }

    public static String getUrlMessage() {
        return urlMessage;
    }

    public static String getCommonMessage() {
        return commonMessage;
    }
}
