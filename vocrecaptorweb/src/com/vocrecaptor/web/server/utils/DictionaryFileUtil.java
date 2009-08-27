package com.vocrecaptor.web.server.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;

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

		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(dictionary.getFile()), ENCODING));

			String str;
			while ((str = inputStream.readLine()) != null) {
				String[] res = str.split("\\|");
				String group = res[WORD_CATEGORY];
				if (!result.contains(group)) {
					result.add(group);
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return result;
	}

	public static List<String> getSessions(DictionaryTransferObject dictionary) {
		List<String> sessions = new ArrayList<String>();

		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(dictionary.getFile()), ENCODING));

			String str;
			while ((str = inputStream.readLine()) != null) {
				String session = str.substring(str.lastIndexOf("|") + 1, str
						.length());
				if (!sessions.contains(session)) {
					sessions.add(session);
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return sessions;
	}

	public static List<WordBean> getWords(DictionaryTransferObject dictionary) {

		List<WordBean> wordsList = new ArrayList<WordBean>();

		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(dictionary.getFile()), ENCODING));

			String str;
			while ((str = inputStream.readLine()) != null) {
				
				if (!str.equals("")) {
					String[] res = str.split("\\|");
					// String category = bean.getCategory();
					// String session = bean.getSession();
					//
					// if (res[WORD_CATEGORY].contains(category)
					// && res[WORD_SESSION].contains(session)) {

					// TODO Filter by category and session!
					wordsList.add(new WordBean(res[WORD_DEFINITION],
							res[WORD_TRANSLATION], res[WORD_EXAMPLE]));
					// }
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return wordsList;
	}

}
