package com.kurpita.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProverbsWithDescriptionParser {

    private static final String SITE = "https://kampot.org.ua/ukraine/traducii_ta_zvuchai/zvuchai/2313-slovnik-prislvyiv-ta-prikazok-z-poyasnennyami.html";

    public static Map<String, String> parse() throws IOException {
        Element content = Jsoup.connect(SITE).get().getElementsByClass("fulnews").get(0);
        Map<String, String> proverbs = new LinkedHashMap<>();
        String proverb = null;
        for (Element item : content.getElementsByTag("p")) {
            if (!item.html().contains("<br><br>")) {
                proverb = item.text();
            } else {
                String description = item.wholeText();
                proverbs.compute(proverb, (k, d) -> d == null ? description : d + "\n" + description);
            }
        }
        return proverbs;
    }

}
