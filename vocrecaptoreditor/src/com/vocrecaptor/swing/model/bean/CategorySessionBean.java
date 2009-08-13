package com.vocrecaptor.swing.model.bean;

/**
 * A bean object class representing a pair of category and session user can 
 * choose for recap.   
 * 
 * @author Alexey Peskov
 */
public class CategorySessionBean {

    private String category;
    private String session;

    /**
     * Default constructor.
     */
    public CategorySessionBean() {
    }

    /**
     * Creates a new instance of CategorySessionBean.
     * @param category word category
     * @param session word session
     */
    public CategorySessionBean(String category, String session) {
        this.category = category;
        this.session = session;
    }

    /**
     * Returns a category.
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets a category.
     * @param category category value
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns a session.
     * @return category
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets a session.
     * @param session session value
     */
    public void setSession(String session) {
        this.session = session;
    }
    
}
