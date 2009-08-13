package com.vocrecaptor.swing.model.dao.txt;

import com.vocrecaptor.swing.model.dao.DAOFactory;
import com.vocrecaptor.swing.model.dao.StorageDAO;

/**
 * The implementation of DAO factory for plain text files.
 * 
 * @author Alexey Peskov
 */
public class TxtDAOFactory extends DAOFactory {
    
    private StorageDAO storageDAO;
    
    /**
     * Returns DAO for working with text files.
     * 
     * @return implementation of <code>StorageDAO</code> interface
     */
    @Override
    public StorageDAO getStorageDAO() {
        if (storageDAO == null) {
            storageDAO = new TxtStorageDAO();
        }
        return storageDAO;
    }
}
