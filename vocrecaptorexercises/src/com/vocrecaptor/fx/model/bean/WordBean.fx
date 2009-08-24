package com.vocrecaptor.fx.model.bean;

/**
 * Model bean object representing a single word entity.
 *
 * @author Alexey Peskov
 */
public class WordBean {

    /**
     * Word id.
     */
    //public var id: Integer;

    /**
     * Definition part.
     */
    public var definition: String;
    
    /**
     * Translation part.
     */
    public var translation: String;

    /**
     * Example of usage.
     */
    public var example: String;

    /**
     * Word status (changes when user learned words etc.).
     */
    //public var status: Integer;
}
