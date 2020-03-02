package com.andrew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class View {
    public static final String INPUT_URL_MSG = "url";
    public static final String PATTERN_PARSE_HTML_TO_TEXT= ">([^<(){}]*.)<";
    public static final String PATTERN2 = "[a-zA-z'&]*\\d*[а-яА-я]*";
    public static final String CHARSET_PATTERN = "charset=[\"']*([^\"'>]*)";
    public static final String NUMBER_OF_SITES = "sites";
    public static final String ERROR_MSG = "error";
    public static final String INVALID_NUMBER_MSG = "invalidNumber";
    public static final String INVALID_NUMBER_FORMAT_MSG = "invalidNumberFormat";
    public static final String THIS_SITE_CONTAINS_MSG = "siteContent";
    public static final String ALL_SITES_CONTAINS_MSG = "allsiteContent";
    public static final String CHOOSE_LANGUAGE_MSG = "Type 1 to choose Ukrainian, type anything else to choose English";
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void getMsg(String str){
        System.out.println(str);
    }

    public int getSites(ResourceBundle rb) throws IOException,NumberFormatException {
        System.out.println(rb.getString("sites"));
        int number = Integer.parseInt(bufferedReader.readLine());
        return number;
    }

    public String setURL(ResourceBundle rb) throws IOException {
        System.out.println(rb.getString("url"));
        String address = bufferedReader.readLine();
        return address;
    }

    public void printMap(Map<String,Integer> map){
        System.out.println(map);
    }

    public Locale setLang() throws IOException {
        getMsg(CHOOSE_LANGUAGE_MSG);
        String userInput = bufferedReader.readLine();
        String country;
        String language;
        if (userInput.equals("1")) {
            country = "UA";
            language = "ua";
        } else {
            country = "EN";
            language = "en";
        }
        Locale lang = new Locale(language,country);
        return lang;
    }

}
