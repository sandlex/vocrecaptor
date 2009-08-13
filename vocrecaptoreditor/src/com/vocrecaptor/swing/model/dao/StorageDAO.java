package com.vocrecaptor.swing.model.dao;

import com.vocrecaptor.swing.model.bean.CategorySessionBean;
import com.vocrecaptor.swing.model.bean.ImportBean;
import com.vocrecaptor.swing.model.bean.WordBean;
import java.util.List;

/**
 * This interface describes a set of methods to use working with 
 * words and dictionary.
 * 
 * @author Alexey Peskov
 */
public interface StorageDAO {

	/**
	 * Returns a full list of word categories in storage file. 
	 * @return a list of categories
	 */
	public List<String> getCategories();

	/**
	 * Returns a full list of word sessions in storage file. 
	 * @return a list of sessions
	 */
	public List<String> getSessions();
	
	/**
	 * Submits (adds) a particular word to the storage file.
	 * @param word WordBean object to store
	 */
    public void submit(WordBean word);
    
    /**
     * Returns a list of words matching the specified criteria - category 
     * and session.
     * @param bean CategorySessionBean object specifying a category and session
     * @return a list of matching words
     */
    public List<WordBean> getWordsList(CategorySessionBean bean);

    /**
     * Imports an entire content of file into storage file. All the words will
     * be imported within category and session specified by importBean parameter.
     * File to import from has to have special format and its full path is also
     * specified within importBean parameter.
     * @param importBean ImportBean containing required information for import
     * @return operation status: positive number is equivalent to the number of
     * imported word, in case of any errors -1 is being returned
     */
    public int importWords(ImportBean importBean);
    
    /**
     * Exports a list of words into a file.
     * @param fullFileName a name of file to import words to
     * @param recapWordsList a list of words to import
     * @return operation status: positive number is equivalent to the number of
     * exported word, in case of any errors -1 is being returned
     */
    public int exportWords(String fullFileName, List<WordBean> recapWordsList);
    
    /**
     * Creates a new storage file.
     * @param fullFileName storage file name
     * @return -1 in case of any errors
     */
    public int createNewStorage(String fullFileName);
    
    /**
     * Sets a new storage file path.
     * @param storagePath new storage file path
     * @return -1 if specified file doesn't exists otherwise the full file name
     */
    public String setStoragePath(String storagePath);
    
    /**
     * Performs a search for specified definition within a storage file. 
     * @param definition String to search
     * @return a list of matching words
     */
    public List<WordBean> searchDefinitions(String definition);
    
}
