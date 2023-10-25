package com.wordinsight;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No words given, do nothing, exiting...");
        }
        TextCapturer textCapturer = new TextCapturer();
        for (int i = 0; i < args.length; i++) {
            String jsonResponse = DictionaryFetcher.get(args[i]).get();
            DictionaryParser parser = new DictionaryParser();
            // System.out.println(jsonResponse);
            WordData word = parser.parse(jsonResponse);
            textCapturer.writeNewImage(word);
        }
    }
}
