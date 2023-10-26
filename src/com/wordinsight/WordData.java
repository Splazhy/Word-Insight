package com.wordinsight;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordData {
    @JsonProperty("word")
    private String word;
    @JsonProperty("phonetic")
    private String phonetic;
    @JsonProperty("phonetics")
    private List<Phonetic> phonetics;
    @JsonProperty("meanings")
    private List<Meaning> meanings;
    @JsonProperty("license")
    private License license;
    @JsonProperty("sourceUrls")
    private List<String> sourceUrls;

    public WordData() {
        meanings = new ArrayList<>();
    }

    public WordData(String word) {
        this.word = word;
        meanings = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public String getPartOfSpeech() {
        return meanings.get(0).partOfSpeech;
    }

    public String getDefinition() {
        return meanings.get(0).definitions.get(0).definition;
    }

    public void display() {
        System.out.println(word);
        System.out.println(phonetic);
        System.out.println();
        for (Meaning m : meanings) {
            System.out.println(m.partOfSpeech);
            for (Definition d : m.definitions) {
                System.out.println(d.definition);
                System.out.printf("\"%s\"\n", (d.example == null) ? "" : d.example);
            }
            System.out.println();
        }
    }

    public static class Phonetic {
        @JsonProperty("text")
        private String text;
        @JsonProperty("audio")
        private String audio;
        @JsonProperty("sourceUrl")
        private String sourceUrl;
        @JsonProperty("license")
        private License license;
    }

    public static class License {
        @JsonProperty("name")
        private String name;
        @JsonProperty("url")
        private String url;
    }

    public static class Definition {
        @JsonProperty("definition")
        private String definition;
        @JsonProperty("synonyms")
        private List<String> synonyms;
        @JsonProperty("antonyms")
        private List<String> antonyms;
        @JsonProperty("example")
        private String example;
    }

    public static class Meaning {
        @JsonProperty("partOfSpeech")
        private String partOfSpeech;
        @JsonProperty("definitions")
        private List<Definition> definitions;
        @JsonProperty("synonyms")
        private List<String> synonyms;
        @JsonProperty("antonyms")
        private List<String> antonyms;
    }

}
