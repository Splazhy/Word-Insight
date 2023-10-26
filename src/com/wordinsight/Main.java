package com.wordinsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DictionaryParser parser = new DictionaryParser();
        TextCapturer capturer = new TextCapturer();
        String string = scanner.nextLine();
        while (!string.equals("!")) {
            String jsonResponse = DictionaryFetcher.get(string).get();
            // System.out.println(jsonResponse);
            WordData word = parser.parse(jsonResponse);
            capturer.writeNewImage(word);
            string = scanner.nextLine();
        }

        scanner.close();
    }
}
