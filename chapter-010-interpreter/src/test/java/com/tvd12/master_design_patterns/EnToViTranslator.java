package com.tvd12.master_design_patterns;

import java.util.HashMap;
import java.util.Map;

class WordTranslator implements Translator {

    private final Map<String, String> dictionary =
        loadDictionary();

    private Map<String, String> loadDictionary () {
        final Map<String, String> example = new HashMap<>();
        example.put("hello", "Xin Chào");
        example.put("world", "Thế giới");
        return example;
    }

    @Override
    public String translate(String input) {
        return dictionary.get(input);
    }
}

public class EnToViTranslator
    implements Translator {

    private final Translator wordTranslator =
        new WordTranslator();

    @Override
    public String translate(String input) {
        String[] enWords = input.split(" ");
        String[] viWords = new String[enWords.length];
        for (int i = 0; i < enWords.length; ++i) {
            viWords[i] = wordTranslator
                .translate(enWords[i]);
        }
        return String.join(" ", viWords);
    }
}
