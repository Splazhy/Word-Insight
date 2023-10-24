package com.wordinsight;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String jsonResponse = DictionaryFetcher.get(args[i]).get();
            DictionaryParser parser = new DictionaryParser();
            // System.out.println(jsonResponse);
            WordData word = parser.parse(jsonResponse);
            word.display();
        }
    }
}
