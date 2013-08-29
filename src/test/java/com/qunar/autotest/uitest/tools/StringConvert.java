package com.qunar.autotest.uitest.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringConvert {
	private static List<String> charStringList = new ArrayList<String>();
	private static List<String> wordList = new ArrayList<String>();

	static {
		charStringList.add("'");
		charStringList.add("\"");

		wordList.add("炸弹");
		wordList.add("枪手");
	}

	/**
	 * 将双引号变成单引号
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String stringFilter(String str) throws PatternSyntaxException {
		// String regEx =
		// "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		String regEx = "[\"]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		p = Pattern.compile("\t|\r|\n");
		m = p.matcher(m.replaceAll("'").trim());
		return m.replaceAll("");

	}

	/**
	 * 过滤特殊字体
	 * 
	 * @param str
	 * @return
	 */
	public static String filterKeyWords(String str, Set<String> keyWordList) {
		int index = -1;
		if (keyWordList != null && !keyWordList.isEmpty()) {
			for (String keyWord : keyWordList) {
				while ((index = str.indexOf(keyWord)) != -1) {
					str = str.substring(0, index) + "魔" + str.substring(index + 1, str.length());
				}
			}
		}
		return str;
	}
}
