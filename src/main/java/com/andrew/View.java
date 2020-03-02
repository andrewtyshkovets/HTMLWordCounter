package com.andrew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class View {
    public static final String INPUT_URL_MSG = "Input URL address";
    public static final String PATTERN_PARSE_HTML_TO_TEXT= ">([^<(){}]*.)<";
    public static final String PATTERN2 = "[a-zA-z'&]*\\d*[а-яА-я]*";
    public static final String CHARSET_PATTERN = "charset=[\"']*([^\"'>]*)";
    public static final String NUMBER_OF_SITES = "Type how many sites you want to check";
    public static final String ERROR_MSG = "Error, something went wrong!!! Try again";
    public static final String INVALID_NUMBER_MSG = "Invalid number!!! Try again";
    public static final String INVALID_NUMBER_FORMAT_MSG = "Invalid number format!!! Try again";
    public static final String THIS_SITE_CONTAINS_MSG = "This site contains :";
    public static final String ALL_SITES_CONTAINS_MSG = "All of checked sites contains :";
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void getMsg(String str){
        System.out.println(str);
    }

    public int getSites() throws IOException,NumberFormatException {
        System.out.println(NUMBER_OF_SITES);
        int number = Integer.parseInt(bufferedReader.readLine());
        return number;
    }

    public String setURL() throws IOException {
        System.out.println(INPUT_URL_MSG+"\n");
        String address = bufferedReader.readLine();
        return address;
    }

    public void printMap(Map<String,Integer> map){
        System.out.println(map);
    }

}
