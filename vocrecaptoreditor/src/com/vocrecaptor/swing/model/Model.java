package com.vocrecaptor.swing.model;

import java.awt.TrayIcon;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import com.vocrecaptor.swing.model.bean.CategorySessionBean;
import com.vocrecaptor.swing.model.bean.ImportBean;
import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.model.dao.DAOFactory;
import com.vocrecaptor.swing.util.Configurator;
import com.vocrecaptor.swing.util.DateUtil;
import com.vocrecaptor.swing.util.UserNotifier;
import com.vocrecaptor.swing.view.updatetypes.CheckDefinitionsType;
import com.vocrecaptor.swing.view.updatetypes.CheckStorageType;
import com.vocrecaptor.swing.view.updatetypes.DisplayWordsListType;
import com.vocrecaptor.swing.view.updatetypes.GetCategoriesType;
import com.vocrecaptor.swing.view.updatetypes.GetSessionsType;
import com.vocrecaptor.swing.view.updatetypes.RecapWordListType;
import com.vocrecaptor.swing.view.updatetypes.RestoreDefinitionTimeType;
import com.vocrecaptor.swing.view.updatetypes.RestoreExampleTimeType;
import com.vocrecaptor.swing.view.updatetypes.RestoreLookAndFeelType;
import com.vocrecaptor.swing.view.updatetypes.RestoreRandomOrderType;
import com.vocrecaptor.swing.view.updatetypes.RestoreRecapWordListType;
import com.vocrecaptor.swing.view.updatetypes.RestoreReverseOrderType;
import com.vocrecaptor.swing.view.updatetypes.RestoreTranslationTimeType;
import com.vocrecaptor.swing.view.updatetypes.SearchDefinitionsType;
import com.vocrecaptor.swing.view.updatetypes.SetLookAndFeelListType;
import com.vocrecaptor.swing.view.updatetypes.SetLookAndFeelType;
import com.vocrecaptor.swing.view.updatetypes.SetReverseOrderType;
import com.vocrecaptor.swing.view.updatetypes.SetStoragePathType;
import com.vocrecaptor.swing.view.updatetypes.StartSessionType;
import com.vocrecaptor.swing.view.updatetypes.base.UpdateType;

/**
 * The model class in the MVC pattern. Contains a set of methods
 * performing business logic operations like changing model parameters, storing 
 * them outside the application and notification of views about changes. 
 * 
 * @author Alexey Peskov
 */
public class Model extends Observable {

    private List<String> categories;
    private List<String> sessions;
    protected List<WordBean> recapWordsList;
    private RecapThread recapThread = null;
    protected TrayIcon trayIcon = null;
    private String storagePath;
    private int definitionTime;
    private int translationTime;
    private int exampleTime;
    private boolean isReverseOrder;
    private boolean isRandomOrder;
    private String inputSession;
    private Map<String, String> lookAndFeelMap = new HashMap<String, String>();
    private CategorySessionBean currentCategorySessionBean;

    /**
     * Creates a new instance of Model.
     */
    public Model() {
    }

    /**
     * Sets application tray icon. 
     * @param trayIcon application tray icon
     */
    public void setTrayIcon(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }

    /**
     * Provides the means to set or reset the model to a default state.
     */
    public void init() {
        categories = new ArrayList<String>();
        sessions = new ArrayList<String>();
        recapWordsList = new ArrayList<WordBean>();

        setLookAndFeelList();
        restoreParameters();
    }

    /**
     * Receives all available look and feel types and puts them into list.
     */
    private void setLookAndFeelList() {
        for (LookAndFeelInfo lnf : UIManager.getInstalledLookAndFeels()) {
        	lookAndFeelMap.put(lnf.getName(), lnf.getClassName());
        }
        notify(new SetLookAndFeelListType(lookAndFeelMap));
    }
    
    /**
     * Restores saved values from program configuration file.
     */
    private void restoreParameters() {
        restoreStoragePath(Configurator.getInstance().getStoragePath());
        restoreLookAndFeel(Configurator.getInstance().getLookAndFeel());
        restoreRecapWordList(new CategorySessionBean(
                Configurator.getInstance().getCategory(),
                Configurator.getInstance().getSession()));
        restoreDefinitionTime(Configurator.getInstance().getDefinitionTime());
        restoreTranslationTime(Configurator.getInstance().getTranslationTime());
        restoreExampleTime(Configurator.getInstance().getExampleTime());
        restoreReverseOrder(Configurator.getInstance().getReverseOrder());
        restoreRandomOrder(Configurator.getInstance().getRandomOrder());
        restoreInputSession(Configurator.getInstance().getInputSession());
    }
    
    /**
     * Restores a previously saved storage path.
     * @param storagePath storage file name
     */
    private void restoreStoragePath(String storagePath) {
        this.storagePath = storagePath;
        String result = DAOFactory.getDAOFactory().getStorageDAO().setStoragePath(storagePath);
        notify(new SetStoragePathType(result));
    }

    /**
     * Gets saved look and feel and checks whether it available or not.
     * If ok so sets it, otherwise sets default SystemLookAndFeel (or default
     * CrossPlatform look and feel if SystemLookAndFeel unavailable).
     * @param lookAndFeelName look and feel name
     */
    private void restoreLookAndFeel(String lookAndFeelName) {
        String lookAndFeelClassName = lookAndFeelMap.get(lookAndFeelName);

        try {
            Class.forName(lookAndFeelClassName);
        } catch (Exception exc) {
            lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
            
            for (Entry<String, String> entry : lookAndFeelMap.entrySet()) {
            	if (entry.getValue().equals(lookAndFeelClassName)) {
            		lookAndFeelName = entry.getKey();
            		break;
            	}
            }
            
            try {
                UIManager.setLookAndFeel(lookAndFeelClassName);
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            Configurator.getInstance().setLookAndFeel(
            		UIManager.getLookAndFeel().getName());
        }

        notify(new SetLookAndFeelType(lookAndFeelClassName));
        notify(new RestoreLookAndFeelType(lookAndFeelName));
    }

    /**
     * Restores a previously saved recap word list.
     * @param bean CategorySessionBean containing session and category
     */
    private void restoreRecapWordList(CategorySessionBean bean) {
        currentCategorySessionBean = bean;
        loadCategories();
        loadSessions();
        recapWordsList = DAOFactory.getDAOFactory().getStorageDAO().getWordsList(bean);
        notify(new RecapWordListType(recapWordsList));
        notify(new RestoreRecapWordListType(bean));
    }
    
    /**
     * Restores a previously saved definition time value.
     * @param definitionTime definition display time
     */
    private void restoreDefinitionTime(int definitionTime) {
        this.definitionTime = definitionTime;
        notify(new RestoreDefinitionTimeType(new Integer(definitionTime)));
    }

    /**
     * Restores a previously saved translation time value.
     * @param translationTime translation display time
     */
    private void restoreTranslationTime(int translationTime) {
        this.translationTime = translationTime;
        notify(new RestoreTranslationTimeType(new Integer(translationTime)));
    }

    /**
     * Restores a previously saved example time value.
     * @param exampleTime example display time
     */
    private void restoreExampleTime(int exampleTime) {
        this.exampleTime = exampleTime;
        notify(new RestoreExampleTimeType(new Integer(exampleTime)));
    }

    /**
     * Restores a previously saved reverse order value.
     * @param isReverseOrder reverse order value
     */
    private void restoreReverseOrder(boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder;
        notify(new RestoreReverseOrderType(new Boolean(isReverseOrder)));
    }

    /**
     * Restores a previously saved random order value.
     * @param isRandomOrder random order value
     */
    private void restoreRandomOrder(boolean isRandomOrder) {
        this.isRandomOrder = isRandomOrder;
        notify(new RestoreRandomOrderType(new Boolean(isRandomOrder)));
    }
    
    /**
     * Restores a previously saved input session name.
     * @param inputSession input session name
     */
    private void restoreInputSession(String inputSession) {
        this.inputSession = inputSession;
    }

    /**
     * Notifies observers about changes.
     * @param updateType implementation of UpdateType interface
     */
    protected void notify(UpdateType updateType) {
    	setChanged();
    	notifyObservers(updateType);
    }
    
    /**
     * Starts recap thread.
     */
    public void startRecapThread() {
        if (recapThread == null) {
            recapThread = new RecapThread(this, definitionTime, translationTime,
                    exampleTime, isReverseOrder, isRandomOrder);
            recapThread.start();
        }
    }

    /**
     * Stops recap thread.
     */
    public void stopRecapThread() {
        if (recapThread != null) {
            recapThread.stopThread();
            recapThread = null;
        }
    }

    /**
     * Updates category list with actual values from storage.
     */
    public void loadCategories() {
        categories = DAOFactory.getDAOFactory().getStorageDAO().getCategories();
        notify(new GetCategoriesType(categories, currentCategorySessionBean.getCategory()));
    }

    /**
     * Returns a list of categories.
     * @return list of categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Updates session list with actual values from storage.
     */
    public void loadSessions() {
        sessions = DAOFactory.getDAOFactory().getStorageDAO().getSessions();
        notify(new GetSessionsType(sessions, currentCategorySessionBean.getSession()));
    }

    /**
     * Returns a list of sessions.
     * @return list of sessions
     */
    public List<String> getSessions() {
        return sessions;
    }

    /**
     * Adds new word to storage.
     * @param word word
     */
    public void addNewWord(WordBean word) {
        DAOFactory.getDAOFactory().getStorageDAO().submit(word);
    }

    /**
     * Loads all words for specified category and session from storage.
     * @param bean CategorySessionBean containing session and category
     */
    public void loadWordsList(CategorySessionBean bean) {
        List<WordBean> wordsList = DAOFactory.getDAOFactory().getStorageDAO().getWordsList(bean);
        notify(new DisplayWordsListType(wordsList));
    }

    /**
     * Fetches a list of words for specified category and session and sets
     * it as a current recap list.
     * @param bean CategorySessionBean containing session and category
     */
    public void setRecapWordsList(CategorySessionBean bean) {
        currentCategorySessionBean = bean;
        recapWordsList = DAOFactory.getDAOFactory().getStorageDAO().getWordsList(bean);
        Configurator.getInstance().setCategory(bean.getCategory());
        Configurator.getInstance().setSession(bean.getSession());
        notify(new RecapWordListType(recapWordsList));
    }

    /**
     * Imports a list of words from a file.
     * @param importBean category, session and file path
     */
    public void importWords(ImportBean importBean) {
        int status = DAOFactory.getDAOFactory().getStorageDAO().importWords(importBean);
        loadSessions();
        loadCategories();

        if (status == -1) {
            UserNotifier.getInstance(trayIcon).displayImportErrorMessage();
        } else if (status > 0) {
        	UserNotifier.getInstance(trayIcon).displayImportSuccessfulMessage(status);
        }
    }

    /**
     * Exports a list of words into file.
     * @param fullFileName file to export words to
     */
    public void exportWords(String fullFileName) {
        int status = DAOFactory.getDAOFactory().getStorageDAO().exportWords(fullFileName,
                DAOFactory.getDAOFactory().getStorageDAO().getWordsList(currentCategorySessionBean));
        if (status == -1) {
        	UserNotifier.getInstance(trayIcon).displayExportErrorMessage();
        } else if (status > 0) {
        	UserNotifier.getInstance(trayIcon).displayExportSuccessfulMessage(status);
        }
    }

    /**
     * Creates a new storage file with specified name.
     * @param fullFileName file name of storage
     */
    public void createStorage(String fullFileName) {
        DAOFactory.getDAOFactory().getStorageDAO().createNewStorage(fullFileName);
        setStoragePath(fullFileName);
    }

    /**
     * Starts a new session.
     */
    public void setSession() {
        // we don't need specify a session if storage is not specified or incorrect. in this case
        // we warn the user by means a warning message
        if (!isStorageNotSpecified()) {
        	notify(new StartSessionType(DateUtil.formatDate(new Date()), inputSession));
        }
    }

    /**
     * Sets current storage name when user choosing a file.
     * @param storagePath storage file name
     */
    public void setStoragePath(String storagePath) {
        String result = DAOFactory.getDAOFactory().getStorageDAO().setStoragePath(storagePath);
        Configurator.getInstance().setStoragePath(result);
        this.storagePath = result;
        notify(new SetStoragePathType(result));
        loadCategories();
        loadSessions();
        recapWordsList.clear();
    }

    /**
     * Set look and feel selected by user. Don't need to check for availability.
     * @param lookAndFeelName look and feel name
     */
    public void setLookAndFeel(String lookAndFeelName) {
        String lookAndFeelClassName = lookAndFeelMap.get(lookAndFeelName);
        Configurator.getInstance().setLookAndFeel(lookAndFeelName);
        notify(new SetLookAndFeelType(lookAndFeelClassName));
    }

    /**
     * Sets a user specified definition time value.
     * @param definitionTime definition display time
     */
    public void setDefinitionTime(Integer definitionTime) {
        this.definitionTime = definitionTime.intValue();
        if (recapThread != null) {
        	recapThread.setDefinitionTime(this.definitionTime);
        	Configurator.getInstance().setDefinitionTime(this.definitionTime);
        }
    }

    /**
     * Sets a user specified translation time value.
     * @param translationTime translation display time
     */
    public void setTranslationTime(Integer translationTime) {
        this.translationTime = translationTime.intValue();
        if (recapThread != null) {
            recapThread.setTranslationTime(this.translationTime);
            Configurator.getInstance().setTranslationTime(this.translationTime);
        }
    }

    /**
     * Sets a user specified example time value.
     * @param exampleTime example display time
     */
    public void setExampleTime(Integer exampleTime) {
        this.exampleTime = exampleTime.intValue();
        if (recapThread != null) {
            recapThread.setExampleTime(this.exampleTime);
            Configurator.getInstance().setExampleTime(this.exampleTime);
        }
    }

    /**
     * Sets a user specified reverse order value.
     * @param isReverseOrder true to display definition after translation
     */
    public void setReverseOrder(Boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder.booleanValue();
        if (recapThread != null) {
            recapThread.setReverseOrder(this.isReverseOrder);
            Configurator.getInstance().setReverseOrder(this.isReverseOrder);
            notify(new SetReverseOrderType(new Boolean(isReverseOrder)));
        }
    }

    /**
     * Sets a user specified random order value.
     * @param isRandomOrder true to display words in random order
     */
    public void setRandomOrder(Boolean isRandomOrder) {
        this.isRandomOrder = isRandomOrder.booleanValue();
        if (recapThread != null) {
            recapThread.setRandomOrder(this.isRandomOrder);
            Configurator.getInstance().setRandomOrder(this.isRandomOrder);
        }
    }

    /**
     * Searches an existing word in storage when user submits a word.
     * @param definition word definition to check
     */
    public void searchDefinitions(String definition) {
        List<WordBean> foundedDefinitions = DAOFactory.getDAOFactory().getStorageDAO().searchDefinitions(definition);
        notify(new SearchDefinitionsType(foundedDefinitions));
    }

    /**
     * Searches an existing word in storage when user checks a word by pressing Check button.
     * @param definition word definition to check
     */
    public void checkDefinitions(String definition) {
        List<WordBean> foundedDefinitions = DAOFactory.getDAOFactory().getStorageDAO().searchDefinitions(definition);
        notify(new CheckDefinitionsType(foundedDefinitions));
    }

    /**
     * Begins a new input session - a bundle of user work of entering new words.
     * @param inputSession input session name
     */
    public void startInputSession(String inputSession) {
        this.inputSession = inputSession;
        Configurator.getInstance().setInputSession(this.inputSession);
    }

    /**
     * Checks if storage is specified or not.
     */
    public void checkStorage() {
        if (isStorageNotSpecified()) {
        	notify(new CheckStorageType());
        }
    }
    
    /**
     * Returns true if storage file is specified or not.
     * @return true if storage file is specified
     */
    private boolean isStorageNotSpecified() {
    	return storagePath.isEmpty() || "-1".equals(storagePath);
    }

}
