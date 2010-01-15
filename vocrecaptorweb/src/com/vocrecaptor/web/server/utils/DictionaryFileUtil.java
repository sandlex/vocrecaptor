package com.vocrecaptor.web.server.utils;

import java.util.ArrayList;
import java.util.List;

import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;
import com.vocrecaptor.web.client.remote.transferobjects.WordBean;
import com.vocrecaptor.web.client.remote.transferobjects.WordsPage;

public class DictionaryFileUtil {

	// TODO Export classes from Editor to jar and use from here
	// TODO Sync with Vocrecaptorswing

	public static final int WORD_PARTS = 5;
	private static final int WORD_CATEGORY = 0;
	private static final int WORD_DEFINITION = 1;
	private static final int WORD_TRANSLATION = 2;
	private static final int WORD_EXAMPLE = 3;
	private static final int WORD_SESSION = 4;

	private static final String ENCODING = "utf8";

	public static List<String> getCategories(DictionaryTransferObject dictionary) {

		List<String> result = new ArrayList<String>();

		String[] strs = dictionary.getFile().split("\n");
		for (int i = 0; i < strs.length; i++) {
			String[] res = strs[i].split("\\|");
			String group = res[WORD_CATEGORY];
			if (!result.contains(group)) {
				result.add(group);
			}
		}

		return result;
	}

	public static List<String> getSessions(DictionaryTransferObject dictionary) {
		List<String> sessions = new ArrayList<String>();

		String[] strs = dictionary.getFile().split("\n");
		for (int i = 0; i < strs.length; i++) {
			if (!"".equals(strs[i])) {
				String session = strs[i].substring(
						strs[i].lastIndexOf("|") + 1, strs[i].length());
				if (!sessions.contains(session)) {
					sessions.add(session);
				}
			}
		}

		return sessions;
	}

	public static List<WordBean> getWords(DictionaryTransferObject dictionary) {

		List<WordBean> wordsList = new ArrayList<WordBean>();

		String[] strs = dictionary.getFile().split("\n");
		for (int i = 0; i < strs.length; i++) {
			if (!"".equals(strs[i])) {
				String[] res = strs[i].split("\\|");
				wordsList.add(new WordBean(res[WORD_DEFINITION],
						res[WORD_TRANSLATION], res[WORD_EXAMPLE]));
			}
		}

		return wordsList;
	}

	public static List<WordBean> getWords(DictionaryTransferObject dictionary,
			String category, String session) {

		List<WordBean> wordsList = new ArrayList<WordBean>();

		String[] strs = dictionary.getFile().split("\n");
		for (int i = 0; i < strs.length; i++) {
			if (!"".equals(strs[i])) {
				String[] res = strs[i].split("\\|");
				if (res[WORD_CATEGORY].contains(category) //FIXME what if category name includes smaller name?
						&& res[WORD_SESSION].contains(session)) {
					wordsList.add(new WordBean(res[WORD_DEFINITION],
							res[WORD_TRANSLATION], res[WORD_EXAMPLE]));
				}
			}
		}

		return wordsList;
	}

	public static WordsPage get15StartingFrom(
			DictionaryTransferObject dictionary, Integer page) {

		List<WordBean> wordsList = new ArrayList<WordBean>();

		String[] strs = dictionary.getFile().split("\n");
		for (int i = 0; i < strs.length; i++) {

			if (i >= 15 * page && i < 15 * (page + 1)) {
				if (!"".equals(strs[i])) {
					String[] res = strs[i].split("\\|");
					wordsList.add(new WordBean(res[WORD_DEFINITION],
							res[WORD_TRANSLATION], res[WORD_EXAMPLE]));
				}
			}
		}

		while (wordsList.size() < 15) {
			wordsList.add(new WordBean("", "", ""));
		}

		return new WordsPage(Math.round(strs.length / 15), strs.length,
				wordsList);
	}

	public static String getDifferences(String currentFile, String newFile) {

		StringBuffer result = new StringBuffer();
		String[] strs = newFile.split("\n");
		for (int i = 0; i < strs.length; i++) {
			if (!currentFile.contains(strs[i])) {
				result.append(strs[i]).append("\n");
			}
		}

		return result.toString();
	}

}
