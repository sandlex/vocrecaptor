package com.vocrecaptor.swing.model.bean;

/**
 * A bean class representing a word.
 *
 * @author Alexey Peskov
 */
public class WordBean {
    
    private String category;
    private String definition;
    private String translation;
    private String example;
    private String session;

    /**
     * Default constructor.
     */
    public WordBean() {
    }
    
    /**
     * Creates an instance of WordBean object.
     * @param category word category
     * @param definition definition
     * @param translation translation
     * @param example example
     * @param session session
     */
    public WordBean(String category, String definition, String translation,
            String example, String session) {
        setCategory(category);
        setDefinition(definition);
        setTranslation(translation);
        setExample(example);
        setSession(session);
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
     * @param category category
     */
    public void setCategory(String category) {
        this.category = category.replaceAll("|", "");
    }
    
    /**
     * Returns a definition.
     * @return definition
     */
    public String getDefinition() {
        return definition;
    }
    
    /**
     * Sets a definition.
     * @param definition definition
     */
    public void setDefinition(String definition) {
        this.definition = definition.replaceAll("|", "");
    }
    
    /**
     * Returns a translation.
     * @return translation
     */
    public String getTranslation() {
        return translation;
    }
    
    /**
     * Sets a translation.
     * @param translation translation
     */
    public void setTranslation(String translation) {
        this.translation = translation.replaceAll("|", "");
    }
    
    /**
     * Returns an example.
     * @return example
     */
    public String getExample() {
        return example;
    }
    
    /**
     * Sets an example.
     * @param example example
     */
    public void setExample(String example) {
        this.example = example.replaceAll("|", "");
    }
    
    /**
     * Returns a session.
     * @return session
     */
    public String getSession() {
        return session;
    }
    
    /**
     * Sets a session.
     * @param session session
     */
    public void setSession(String session) {
        this.session = session;
    }
    
    public String toString() {
        StringBuffer str = new StringBuffer(definition);
        str.append("\t").append(translation).append("\t").append(example);
        
        return str.toString();
    }

}
