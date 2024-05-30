package com.kurpita.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CategorizedProverbsParser {

    private static final String SITE = "https://kampot.org.ua/traducii_ta_zvuchai/zvuchai/page,%s,2185-ukrayinsk-prislvya.html";

    public static Map<String, List<String>> parse() throws IOException {
        Map<String, List<String>> data = new LinkedHashMap<>();
        for (int i = 1; i <= 11; i++) {
            String url = String.format(SITE, i);
            Document site = Jsoup.connect(url).get();
            String title = site.getElementsByTag("h2").get(0).ownText();
            List<String> proverbs = new ArrayList<>();
            data.put(title, proverbs);

            Element list = site.getElementsByClass("spusok_2").get(0);
            for (Element proverbElement : list.children().get(0).children()) {
                proverbs.add(proverbElement.text());
            }
        }
        return data;
    }

}
