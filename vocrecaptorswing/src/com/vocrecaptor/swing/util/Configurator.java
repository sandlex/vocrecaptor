package com.vocrecaptor.swing.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class represents a configuration manager working with application
 * configuration file. Vocrecaptor keeps it settings in 
 * standard properties file (a list of key value string
 * where each string represents a particular setting). On a first launch
 * application creates a property file with default values. Later
 * when user changes some settings (e.g. current session or category,
 * delay, current dictionary) it will be saved in property file and restored
 * after next application launch. Property file is created in application
 * directory next to jar file. 
 * 
 * @author Alexey Peskov
 */
public class Configurator {
    
	// Property file name.
    private static final String fileName = "vocrecaptor.properties";
    
    // Application properties stored in property file. The "key" value.
    private static final String STORAGE_PATH = "StoragePath";
    private static final String LOOK_AND_FEEL = "LookAndFeel";
    private static final String CATEGORY = "Category";
    private static final String SESSION = "Session";
    private static final String DEFINITION_TIME = "DefinitionTime";
    private static final String TRANSLATION_TIME = "TranslationTime";
    private static final String EXAMPLE_TIME = "ExampleTime";
    private static final String REVERSE_ORDER = "ReverseOrder";
    private static final String RANDOM_ORDER = "RandomOrder";
    private static final String LOCALE = "Locale";
    private static final String INPUT_SESSION = "InputSession";
    
    // Actual values to store in property file.
    private String storagePath;
    private String lookAndFeel;
    private String category;
    private String session;
    private int definitionTime;
    private int translationTime;
    private int exampleTime;
    private boolean isReverseOrder;
    private boolean isRandomOrder;
    private String locale;
    private String inputSession;
    
    private static Configurator instance = null;
    private Properties config = null;

    /** 
     * Creates a new instance of Configurator.
     * If property file exists its values will be loaded
     * otherwise file will be created with default values.
     */
    private Configurator() {
        
        config = new Properties();
        InputStream is;
        try {
            is = new FileInputStream(fileName);
            config.load(is);
        } catch (FileNotFoundException ex) {
            store();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        storagePath = config.getProperty(STORAGE_PATH, "");
        lookAndFeel = config.getProperty(LOOK_AND_FEEL, "");
        category = config.getProperty(CATEGORY, "");
        session = config.getProperty(SESSION, "");
        definitionTime = new Integer(config.getProperty(DEFINITION_TIME, "1")).intValue();
        translationTime = new Integer(config.getProperty(TRANSLATION_TIME, "1")).intValue();
        exampleTime = new Integer(config.getProperty(EXAMPLE_TIME, "1")).intValue();
        isReverseOrder = new Boolean(config.getProperty(REVERSE_ORDER, "false")).booleanValue();
        isRandomOrder = new Boolean(config.getProperty(RANDOM_ORDER, "false")).booleanValue();
        locale = config.getProperty(LOCALE, "en");
        inputSession = config.getProperty(INPUT_SESSION, "");

        setStoragePath(storagePath);
        setLookAndFeel(lookAndFeel);
        setCategory(category);
        setSession(session);
        setDefinitionTime(definitionTime);
        setTranslationTime(translationTime);
        setExampleTime(exampleTime);
        setReverseOrder(isReverseOrder);
        setRandomOrder(isRandomOrder);
        setLocale(locale);
        setInputSession(inputSession);
    }
    
    /**
     * Returns an instance of Configurator.
     * 
     * @return an instance of Configurator
     */
    public static Configurator getInstance() {
        if (instance == null) {
            instance = new Configurator();
        }
        
        return instance;
    }
    
    /**
     * Stores real parameters into a property file. 
     */
    private void store() {
        FileOutputStream out;
        try {
            out = new FileOutputStream(fileName);
            config.store(out, "");
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Returns a path to the storage file.
     * @return path to the storage file
     */
    public String getStoragePath() {
        return storagePath;
    }
    
    /**
     * Sets and stores in configuration file a path to the storage file.
     * @param storagePath path to the storage file 
     */
    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
        config.setProperty(STORAGE_PATH, storagePath);
        store();
    }
    
    /**
     * Returns a look and feel name.
     * @return look and feel name
     */
    public String getLookAndFeel() {
        return lookAndFeel;
    }
    
    /**
     * Sets and stores in configuration file look and feel name.
     * @param lookAndFeel look and feel name
     */
    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
        config.setProperty(LOOK_AND_FEEL, lookAndFeel);
        store();
    }
    
    /**
     * Returns the last recaped category.
     * @return last recaped category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Sets and stores in configuration file the last recaped category
     * @param category the last recaped category
     */
    public void setCategory(String category) {
        this.category = category;
        config.setProperty(CATEGORY, category);
        store();
    }
    
    /**
     * Returns the last recaped session.
     * @return last recaped session
     */
    public String getSession() {
        return session;
    }
    
    /**
     * Sets and stores in configuration file the last recaped session
     * @param session the last session category
     */
    public void setSession(String session) {
        this.session = session;
        config.setProperty(SESSION, session);
        store();
    }
    
    /**
     * Returns the last definition display time value.
     * @return last definition display time value
     */
    public int getDefinitionTime() {
        return definitionTime;
    }
    
    /**
     * Sets and stores in configuration file the last definition display time value.
     * @param definitionTime last definition display time value
     */
    public void setDefinitionTime(int definitionTime) {
        this.definitionTime = definitionTime;
        config.setProperty(DEFINITION_TIME, String.valueOf(definitionTime));
        store();
    }
    
    /**
     * Returns the last translation display time value.
     * @return last translation display time value
     */
    public int getTranslationTime() {
        return translationTime;
    }
    
    /**
     * Sets and stores in configuration file the last translation display time value.
     * @param translationTime last translation display time value
     */
    public void setTranslationTime(int translationTime) {
        this.translationTime = translationTime;
        config.setProperty(TRANSLATION_TIME, String.valueOf(translationTime));
        store();
    }
    
    /**
     * Returns the last example display time value.
     * @return last example display time value
     */
    public int getExampleTime() {
        return exampleTime;
    }
    
    /**
     * Sets and stores in configuration file the last example display time value.
     * @param exampleTime last example display time value
     */
    public void setExampleTime(int exampleTime) {
        this.exampleTime = exampleTime;
        config.setProperty(EXAMPLE_TIME, String.valueOf(exampleTime));
        store();
    }
    
    /**
     * Returns the last recap reverse order flag value.
     * @return last recap reverse order flag value
     */
    public boolean getReverseOrder() {
        return isReverseOrder;
    }
    
    /**
     * Sets and stores in configuration file the last recap reverse order flag value
     * @param isReverseOrder last recap reverse order flag value
     */
    public void setReverseOrder(boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder;
        config.setProperty(REVERSE_ORDER, String.valueOf(isReverseOrder));
        store();
    }

    /**
     * Returns the last recap random order flag value.
     * @return last recap random order flag value
     */
    public boolean getRandomOrder() {
        return isRandomOrder;
    }
    
    /**
     * Sets and stores in configuration file the last recap random order flag value
     * @param isRandomOrder last recap random order flag value
     */
    public void setRandomOrder(boolean isRandomOrder) {
        this.isRandomOrder = isRandomOrder;
        config.setProperty(RANDOM_ORDER, String.valueOf(isRandomOrder));
        store();
    }

    /**
     * Returns the last used locale.
     * @return last used locale
     */
    public String getLocale() {
        return locale;
    }
    
    /**
     * Sets and stores in configuration file the last used locale.
     * @param locale last used locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
        config.setProperty(LOCALE, locale);
        store();
    }

    /**
     * Returns the last input session.
     * @return last input session
     */
    public String getInputSession() {
        return inputSession;
    }
    
    /**
     * Sets and stores in configuration file the last input session.
     * @param inputSession last input session
     */
    public void setInputSession(String inputSession) {
        this.inputSession = inputSession;
        config.setProperty(INPUT_SESSION, inputSession);
        store();
    }
    
}
