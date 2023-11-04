package com.jeditor.languages;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class LangJava {
    private static final HashMap<String, KeyWords> keyWords;

    static public class KeyWords {
        public String[] words;
        public Color color;

        public KeyWords(String[] words, Color color) {
            this.words = words;
            this.color = color;
        }
    }

    static {
        keyWords = new HashMap<>();

        String[] primaryKeyWords = {
                "assert", "default", "do", "enum", "switch", "case", "break", "continue", "native", "try", "catch",
                "finally", "while", "for", "if", "else", "instanceof", "new", "return", "super", "this", "synchronized",
                "throw", "transient"
        };
        String[] secondaryKeyWords = {
                "abstract", "boolean", "byte", "char", "class", "double", "extends", "final", "float", "implements",
                "interface", "long", "public", "private", "protected", "short", "static", "strictfp", "throws", "volatile"
        };
        String[] specialKeyWords = {
                "import", "package", "null"
        };

        keyWords.put("primaryKeyWords", new KeyWords(primaryKeyWords, new Color(219, 68, 55)));
        keyWords.put("secondaryKeyWords", new KeyWords(secondaryKeyWords, new Color(15, 157, 88)));
        keyWords.put("specialKeyWords", new KeyWords(specialKeyWords, new Color(255, 0, 255)));
    }

    public static HashMap<String, KeyWords> getKeyWords() {
        return keyWords;
    }
}
