package com.vocrecaptor.swing.model.dao;

import com.vocrecaptor.swing.model.dao.txt.TxtDAOFactory;

/**
 * The factory in abstract factory pattern. Allows to use different types
 * of factories depending on the data storing mechanism (text files, database,
 * xml files and so on).
 * 
 * @author Alexey Peskov
 */
public abstract class DAOFactory {
    
    // Should be a singleton
    private static DAOFactory instance;
    
    /**
     * Returns a required type of DAO factory - factory for working with
     * text files in this case.
     * @return <code>DAOFactory</code> instance
     */
    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new TxtDAOFactory();
        }
        return instance;
    }
    
    /**
     * Returns DAO.
     * @return implementation of <code>StorageDAO</code> interface
     */
    public abstract StorageDAO getStorageDAO();
}
