package com.vocrecaptor.swing.model.bean;

/**
 * A bean object class representing a pair of category and session user can 
 * import from specified file.  
 *
 * @author Alexey Peskov
 */
public class ImportBean extends CategorySessionBean {
    
    private String fullFileName;
    
    /**
     * Default constructor.
     */
    public ImportBean() {
    }

    /**
     * Creates a new instance of CategorySessionBean.
     * @param fullFileName file name the words are being imported from
     * @param category word category to apply
     * @param session word session to apply
     */
    public ImportBean(String fullFileName, String category, String session) {
    	super(category, session);
        this.fullFileName = fullFileName;
    }

    /**
     * Returns a file name.
     * @return file name
     */
    public String getFullFileName() {
        return fullFileName;
    }

    /**
     * Sets a file name.
     * @param fullFileName file name
     */
    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }

}
