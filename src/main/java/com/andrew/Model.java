package com.andrew;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private String siteContent;
    private String[] siteContentArray;
    private Map<String, Integer> countOfEachWord = new HashMap<String, Integer>();

    public Model() {
    }

    public Model(String siteContent) {
        this.siteContent = siteContent;
        this.siteContentArray = makeArrayFromString(siteContent);
        this.countOfEachWord = countWords(siteContentArray);
    }

    public String getSiteContent() {
        return siteContent;
    }

    public void setSiteContent(String siteContent) {
        this.siteContent = siteContent;
    }

    public String[] getSiteContentArray() {
        return siteContentArray;
    }

    public void setSiteContentArray(String[] siteContentArray) {
        this.siteContentArray = siteContentArray;
    }

    public Map<String, Integer> getCountOfEachWord() {
        return countOfEachWord;
    }

    public void setCountOfEachWord(Map<String, Integer> countOfEachWord) {
        this.countOfEachWord = countOfEachWord;
    }

    public String[] makeArrayFromString(String str) throws IllegalArgumentException {
        if (str != null) {
            str = str.toLowerCase();
            siteContentArray = str.split(" ");
        } else {
            throw new IllegalArgumentException();
        }
        return siteContentArray;
    }

    public Map<String, Integer> countWords(String[] arr) {
        for (String str : arr) {
            Integer freq = countOfEachWord.get(str);
            if(str.equals("")) {
                continue;

            } else {
                countOfEachWord.put(str, (freq == null) ? 1 : freq + 1);
            }
        }
        return countOfEachWord;
    }
}
