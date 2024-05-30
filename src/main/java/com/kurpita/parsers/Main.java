package com.kurpita.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Main {

    private static final ObjectMapper json = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Map<String, String> proverbsWithDescription = ProverbsWithDescriptionParser.parse();
        save(proverbsWithDescription, "proverbs-with-description.json");

        Map<String, List<String>> categorizedProverbs = CategorizedProverbsParser.parse();
        save(categorizedProverbs, "categorized-proverbs.json");
    }

    private static void save(Object data, String filename) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            IOUtils.write(json.valueToTree(data).toPrettyString(), outputStream, StandardCharsets.UTF_8);
        }
    }

}
