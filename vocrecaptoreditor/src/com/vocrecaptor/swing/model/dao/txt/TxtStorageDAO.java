package com.vocrecaptor.swing.model.dao.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.vocrecaptor.swing.model.bean.CategorySessionBean;
import com.vocrecaptor.swing.model.bean.ImportBean;
import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.model.dao.StorageDAO;

/**
 * This the the implementation of <code>StorageDAO<code> interface for
 * text files. Contains a set of methods that the application use to
 * work with storage files and words.
 * 
 * @author Alexey Peskov
 */
public class TxtStorageDAO implements StorageDAO {

	// TODO create test for each method

	private static final int WORD_PARTS = 5;
	private static final int WORD_CATEGORY = 0;
	private static final int WORD_DEFINITION = 1;
	private static final int WORD_TRANSLATION = 2;
	private static final int WORD_EXAMPLE = 3;
	private static final int WORD_SESSION = 4;

	private static final String ENCODING = "utf8";//"cp1251";
	private static final String EXPORT_ENCODING = "cp1251";
	private static final String IMPORT_ENCODING = "cp1251";

	private String storagePath;

	/**
	 * Default constructor.
	 */
	public TxtStorageDAO() {
	}

	public List<String> getCategories() {
		List<String> groups = new ArrayList<String>();

		if (storagePath == "-1") {
			return groups;
		}
		
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new FileInputStream(storagePath), ENCODING));

			String str;
			while ((str = inputStream.readLine()) != null) {
				String[] res = str.split("\\|");
				String group = res[WORD_CATEGORY];
				if (!groups.contains(group)) {
					groups.add(group);
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

		return groups;
	}

	public List<String> getSessions() {
		List<String> sessions = new ArrayList<String>();

		if (storagePath == "-1") {
			return sessions;
		}
		
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new FileInputStream(storagePath), ENCODING));

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

	public void submit(WordBean word) {
		BufferedWriter writer = null;

		if (storagePath == "-1") {
			return;
		}

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(storagePath, true), ENCODING));
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

	public int exportWords(String fullFileName, List<WordBean> recapWordsList) {
		BufferedWriter writer = null;
		int result = 0;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fullFileName, true), EXPORT_ENCODING));

			for (WordBean word : recapWordsList) {
				writer.append(word.toString());
				writer.newLine();
				result++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			result = -1;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return result;
	}

	public List<WordBean> getWordsList(CategorySessionBean bean) {
		List<WordBean> wordsList = new ArrayList<WordBean>();

		if (storagePath == "-1") {
			return wordsList;
		}
		
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new FileInputStream(storagePath), ENCODING));

			String str;
			while ((str = inputStream.readLine()) != null) {

				if (!str.equals("")) {
					String[] res = str.split("\\|");
					String category = bean.getCategory();
					String session = bean.getSession();

					if (res[WORD_CATEGORY].contains(category)
							&& res[WORD_SESSION].contains(session)) {
						wordsList.add(new WordBean(res[WORD_CATEGORY],
								res[WORD_DEFINITION], res[WORD_TRANSLATION],
								res[WORD_EXAMPLE], res[WORD_SESSION]));
					}
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

	public int importWords(ImportBean importBean) {
		int status = 0;

		if (storagePath == "-1") {
			return -1;
		}
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(importBean.getFullFileName()),
					IMPORT_ENCODING));
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(storagePath, true), ENCODING));

			String str;
			while ((str = reader.readLine()) != null) {
				if (!str.startsWith("#")) { // not comment
					String[] res = str.split("\\t"); // has parts delimited with
														// tabs
					if (res.length == 2 || res.length == 3) { // or 3 parts
																// (with
																// example)
						StringBuffer result = new StringBuffer();
						result.append(importBean.getCategory()).append("|");
						result.append(res[0].trim()).append("|");
						result.append(res[1].trim()).append("|");
						if (res.length == 3) {
							result.append(res[2].trim());
						}
						result.append("|");
						result.append(importBean.getSession().trim());
						writer.append(result.toString());
						writer.newLine();
						status++;
					}
				}
			}
		} catch (FileNotFoundException ex) {
			status = -1;
			ex.printStackTrace();
		} catch (IOException ex) {
			status = -1;
			ex.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					status = -1;
					ex.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return status;
	}

	public String setStoragePath(String path) {
		File file = new File(path);
		if (!file.exists() || !isCorrectFormat(file)) {
			storagePath = "-1";
		} else {
			storagePath = file.getAbsolutePath();
		}

		return storagePath;
	}

	public int createNewStorage(String fullFileName) {
		BufferedWriter writer = null;
		int result = 0;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fullFileName, true), ENCODING));
		} catch (IOException ex) {
			ex.printStackTrace();
			result = -1;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		return result;
	}

	public List<WordBean> searchDefinitions(String definition) {
		List<WordBean> wordsList = new ArrayList<WordBean>();

		if (storagePath == "-1") {
			return wordsList;
		}
		
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new FileInputStream(storagePath), ENCODING));

			String str;
			while ((str = inputStream.readLine()) != null) {

				if (!str.equals("")) {
					String[] res = str.split("\\|");

					if (res[WORD_DEFINITION].contains(definition)) {
						wordsList.add(new WordBean(res[WORD_CATEGORY],
								res[WORD_DEFINITION], res[WORD_TRANSLATION],
								res[WORD_EXAMPLE],
								res[WORD_SESSION]));
					}
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

	/**
	 * Checks if the storage file specified has a correct format.
	 * 
	 * @param file
	 *            file to check
	 * @return true if all lines consists of WORD_PARTS=5 parts
	 */
	private boolean isCorrectFormat(File file) {
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), ENCODING));

			String str;
			while ((str = inputStream.readLine()) != null) {

				if ("".equals(str) || str.split("\\|").length != WORD_PARTS) {
					return false;
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

		return true;
	}
	
}
