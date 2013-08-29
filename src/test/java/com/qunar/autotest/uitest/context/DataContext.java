package com.qunar.autotest.uitest.context;

import com.qunar.autotest.uitest.tools.FileReadWrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataContext {
    private static Map<String, String> dataMap = new HashMap<String, String>();
    private static List<String> keyWords = new ArrayList<String>();
    private static String urlMessage = "[url=www.39shoe.com][color=White]%s[/color][/url]";
    private static String commonMessage="求地图，求美女图片2，哈哈";

    static {
        try {
            keyWords.addAll(FileReadWrite.getKeyWordsList("src/main/resources/com/qunar/autotest/uitest/keywords.txt"));
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
