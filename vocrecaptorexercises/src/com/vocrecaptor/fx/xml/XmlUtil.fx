package com.vocrecaptor.fx.xml;

import com.vocrecaptor.fx.model.bean.DictionaryBean;
import com.vocrecaptor.fx.model.bean.WordBean;
import java.io.ByteArrayInputStream;
import javafx.data.pull.PullParser;
import javafx.data.pull.Event;
import javafx.data.xml.QName;

/**
 * This is util class for parsing xml-string coming as a server response.
 *
 * @author Alexey Peskov
 */
public class XmlUtil {

    /**
     * Parses a string containing list of user dictionaries.
     */
    public function parseDictsXmlString(xmlString : String) : DictionaryBean[] {

        var is: ByteArrayInputStream = new ByteArrayInputStream
            (xmlString.getBytes());
        var dicts: DictionaryBean[];
        var dict: DictionaryBean;

        try {
            PullParser {
                input: is;
                onEvent: function (event : Event) {
                    if (event.type == PullParser.START_ELEMENT and event.level == 1) {
                        dict = new DictionaryBean;
                    } else if (event.type == PullParser.END_ELEMENT) {
                        if (event.level == 1) {
                            insert dict into dicts;
                        }

                        var nodeName: String = event.qname.name;
                        if (nodeName == "id") {
                            dict.id = Integer.parseInt(event.text);
                        } else if (nodeName == "desc") {
                            dict.description = event.text;
                        }
                    }
                }
            }.parse();
        } finally {
            is.close();
        }

        return dicts;
    }

    /**
     * Parses a string containing list of categories and sessions.
     */
    public function parseListXmlString(xmlString : String) : String[] {
        var is: ByteArrayInputStream = new ByteArrayInputStream
            (xmlString.getBytes());
        var values = [""];
        var value: String;

        try {
            PullParser {
                input: is;
                onEvent: function (event : Event) {
                    if (event.type == PullParser.START_ELEMENT and event.level == 1) {
                    } else if (event.type == PullParser.END_ELEMENT) {
                        var nodeName: String = event.qname.name;
                        if (nodeName == "v") {
                            value = event.text;
                            insert value into values;
                        }
                    }
                }
            }.parse();
        } finally {
            is.close();
        }

        return values;
    }

    /**
     * Parses a string containing list of words.
     */
    public function parseWordsXmlString(xmlString : String) : WordBean[] {
        var is: ByteArrayInputStream = new ByteArrayInputStream
            (xmlString.getBytes());
        var words: WordBean[];
        var word: WordBean;

        try {
            PullParser {
                input: is;
                onEvent: function (event : Event) {
                    if (event.type == PullParser.START_ELEMENT and event.level == 1) {
                        word = new WordBean;
                        //var qAttr: QName = QName {name: "i"};
                        //word.id = Integer.parseInt(event.getAttributeValue(qAttr));
                    } else if (event.type == PullParser.END_ELEMENT) {
                        if (event.level == 1) {
                            insert word into words;
                        }

                        var nodeName: String = event.qname.name;
                        if (nodeName == "d") {
                            word.definition = event.text;
                        } else if (nodeName == "t") {
                            word.translation = event.text;
                        } else if (nodeName == "e") {
                            word.example = event.text;
                        } /*else if (nodeName == "s") {
                            word.status = Integer.parseInt(event.text);
                        }*/
                    }
                }
            }.parse();
        } finally {
            is.close();
        }

        return words;
    }

}
