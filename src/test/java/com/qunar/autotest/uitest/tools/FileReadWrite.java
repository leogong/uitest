package com.qunar.autotest.uitest.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileReadWrite {

	public static Set<String> getKeyWordsList(String path) throws IOException {
		Set<String> set = new HashSet<String>();
		BufferedReader bf = new BufferedReader(new FileReader(path));
		String line = null;
		while ((line = bf.readLine()) != null) {
			set.add(line.trim());
		}
		bf.close();
		return set;
	}

	public static void writeKeyWords(String path, String word) throws IOException {
		Set<String> keywordsSet = getKeyWordsList(path);
		if (!keywordsSet.contains(word)) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			bw.write(word);
			bw.newLine();
			bw.flush();
			bw.close();
		}
	}
}
