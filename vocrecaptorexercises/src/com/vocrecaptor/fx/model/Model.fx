package com.vocrecaptor.fx.model;

import com.vocrecaptor.fx.model.bean.DictionaryBean;
import com.vocrecaptor.fx.model.bean.LanguageBean;
import com.vocrecaptor.fx.model.bean.UserBean;
import com.vocrecaptor.fx.model.bean.WordBean;

/**
 * Application model class. Holds all bean objects other program
 * component (controller, views) are using. All members are public
 * to allow binding of ui components.
 *
 * @author Alexey Peskov
 */
public class Model {

    /**
    * Current user.
     */
    public var user: UserBean;
    
    /**
    * List of available languages.
     */
    public var languages: LanguageBean[];

    /**
    * Learning language index on anonymous enter.
     */
    public var learningLanguage: Integer;

    /**
    * Through language index on anonymous enter.
     */
    public var throughLanguage: Integer;

    /**
    * List of loaded user dictionaries.
     */
    public var dicts: DictionaryBean[];

    /**
    * Current user dictionary.
     */
    public var dict: DictionaryBean;
    
    /**
    * Current category of words user work with.
     */
    public var category: String = "";

    /**
    * Current session of words user work with.
     */
    public var session: String = "";
    
    /**
    * List of words matching user criteria used for
     * recaping and exercising.
     */
    public var currentWordsList: WordBean[];

    /**
    * Current word for some particular moment of recaping or
     * exercising. 
     */
    public var currentWord: WordBean;
    
    /**
    * Definition displayed on a particular moment of recaping or
     * exercising in UI.
     */
    public var definition: String;

    /**
    * Translation displayed on a particular moment of recaping or
     * exercising in UI.
     */
    public var translation: String;
    
    /**
    * Example of usage displayed on a particular moment of recaping or
     * exercising in UI.
     */
    public var example: String;

    /**
    * For list of words havin more than 10 words.
     */
    public var tenner: Integer;

    /**
    * Current word number.
     */
    public var wordNumber: Integer;

    /**
    * List of exercises.
     */
     //TODO Allow user to choose the order and number of them.
    public var exercises: String[] = ["Exercise_cards",
        "Exercise_definition_guessing", "Exercise_writing",
        "Exercise_definition_choice", "Exercise_translation_guessing",
    "Exercise_translation_choice", "Exercise_mosaic"];

    /**
     * Clears model state.
     */
    public function clearState() : Void {
        user = null;
        delete dicts;
        delete currentWordsList;
    }

    /**
     * Notification message displayed on the screen (progress, errors, etc.).
     */
    public var notification: String = "";

    /**
     * Name of the current user activity - opened window to be displayed on the
     * top of main panel.
     */
    public var activity: String = "Login";

}
