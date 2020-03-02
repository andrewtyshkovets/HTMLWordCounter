package com.andrew;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class);
    private View view;
    private Model model;
    private String url = null;
    private Map<String, Integer> countOfOneSiteWords = new HashMap<>();
    private Map<String, Integer> countOfAllWords = new HashMap<>();
    private String text = "";
    private int numberOfSites = 0;
    private String charsetName = "";

    public Controller() {
    }

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void runController() {
        try {
            numberOfSites = view.getSites();
            if (numberOfSites < 0) {
                logger.error(view.INVALID_NUMBER_MSG);
                runController();
            }
            for (int i = 0; i < numberOfSites; i++) {
                url = view.setURL();
                text = getText(url);
                text = parseHTML(text);
                text = makeUnderstandableWords(text);
                model = new Model(text);
                countOfOneSiteWords = model.getCountOfEachWord();
                view.getMsg(view.THIS_SITE_CONTAINS_MSG);
                view.printMap(countOfOneSiteWords);
                putWordsIntoSpareMap(countOfOneSiteWords);
            }
        } catch (NumberFormatException e) {
            logger.error(view.INVALID_NUMBER_FORMAT_MSG);
            runController();
        } catch (IOException e) {
            logger.error(view.ERROR_MSG);
            runController();
        }
        view.getMsg(view.ALL_SITES_CONTAINS_MSG);
        view.printMap(countOfAllWords);
    }

    public void runHomeTask() {
        try {
            for (int i = 1; i < 21; i++) {
                url = "https://stihi-rus.ru/World/Shekspir/" + i + ".htm";
                text = getText(url);
                text = parseHTML(text);
                text = makeUnderstandableWords(text);
                model = new Model(text);
                countOfOneSiteWords = model.getCountOfEachWord();
                view.printMap(countOfOneSiteWords);
                putWordsIntoSpareMap(countOfOneSiteWords);
            }
        } catch (IOException e) {
            logger.error(view.ERROR_MSG);
            runHomeTask();
        }
        view.printMap(countOfAllWords);
    }

    public String getText(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        charsetName = connection.getContentType();
        charsetName=getCharset(charsetName);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream(),charsetName));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();
        return response.toString();
    }

    public String parseHTML(String text) {
        String words = makeMatchingString(View.PATTERN_PARSE_HTML_TO_TEXT, text);
        return words;
    }

    public String makeUnderstandableWords(String text) {
        String words = makeMatchingString(View.PATTERN2, text);
        return words;
    }

    public String makeMatchingString(String pattern, String text) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(text);
        String words = "";
        while (matcher.find()) {
            if (pattern.equals(view.PATTERN_PARSE_HTML_TO_TEXT)) {
                words = words + text.substring(matcher.start() + 1, matcher.end() - 1) + " ";
            } else {
                words = words + text.substring(matcher.start(), matcher.end()) + " ";
            }
        }
        return words;
    }

    public void putWordsIntoSpareMap(Map<String, Integer> map) {
        String key = null;
        Integer value = null;
        Integer temp = null;
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            key = pair.getKey();
            value = pair.getValue();
            if (countOfAllWords.get(key) == null) {
                countOfAllWords.put(key, value);
            } else {
                temp = countOfAllWords.get(key);
                value += temp;
                countOfAllWords.put(key, value);
            }
        }
    }

    public String getCharset(String contentType) {
        String charset = makeMatchingString(view.CHARSET_PATTERN, contentType);
        charset = charset.replace('"', '`');
        charset = charset.replaceAll("`","");
        charset = charset.substring(8).trim();
        return charset;
    }


}
