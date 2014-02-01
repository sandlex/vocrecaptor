package com.vocrecaptor.swing.model.dao.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.vocrecaptor.swing.model.bean.WordBean;

public class ASCIIToUTF8Recoder {

	public static void main(String[] args) {

		List<String> files = new ArrayList<String>();
//		files.add("adjectives.txt");
//		files.add("animals.txt");
//		files.add("buildings.txt");
//		files.add("clothes.txt");
//		files.add("collocations.txt");
//		files.add("compoundnouns.txt");
//		files.add("crime.txt");
//		files.add("down.txt");
//		files.add("foodandcooking.txt");
//		files.add("irregularverbs.txt");
//		files.add("jobs.txt");
//		files.add("likinganddisliking.txt");
//		files.add("loveandmarriage.txt");
//		files.add("sounds.txt");
//		files.add("take.txt");
//		files.add("transport.txt");
		files.add("sandlex.txt");

		for (String file : files) {
			List<WordBean> words = getWordsList(file);
			for (WordBean word : words) {
				submit(word, file);
			}
		}

	}

	private static final int WORD_PARTS = 5;
	private static final int WORD_CATEGORY = 0;
	private static final int WORD_DEFINITION = 1;
	private static final int WORD_TRANSLATION = 2;
	private static final int WORD_EXAMPLE = 3;
	private static final int WORD_SESSION = 4;

	private static final String ENCODING = "utf8";// "cp1251";
	private static final String EXPORT_ENCODING = "cp1251";
	private static final String IMPORT_ENCODING = "cp1251";

	static public List<WordBean> getWordsList(String file) {
		List<WordBean> wordsList = new ArrayList<WordBean>();

		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									"C:\\projects\\vocrecaptor\\workspace\\vocrecaptoreditor\\example dictionaries\\"//sampledictionaries_en-ru_cp1251\\"
											+ file), "cp1251"));

			String str;
			while ((str = inputStream.readLine()) != null) {

				if (!str.equals("")) {
					String[] res = str.split("\\|");

					wordsList.add(new WordBean(res[WORD_CATEGORY],
							res[WORD_DEFINITION], res[WORD_TRANSLATION],
							res[WORD_EXAMPLE], res[WORD_SESSION]));
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

	static public void submit(WordBean word, String file) {
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(
									"C:\\projects\\vocrecaptor\\workspace\\vocrecaptoreditor\\example dictionaries\\utf8_"
											+ file, true), "utf8"));
			StringBuffer result = new StringBuffer();
			result.append(word.getCategory().trim()).append("|");
			result.append(word.getDefinition().trim()).append("|");
			result.append(word.getTranslation().trim()).append("|");
			result.append(word.getExample().trim()).append("|");
			result.append(word.getSession().trim());
			writer.append(result.toString());
			writer.newLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}
