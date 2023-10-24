package com.wordinsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        while (!string.equals("!")) {
            String jsonResponse = DictionaryFetcher.get(string).get();
            DictionaryParser parser = new DictionaryParser();
            // System.out.println(jsonResponse);
            WordData word = parser.parse(jsonResponse);
            word.display();
            string = scanner.nextLine();
        }

        scanner.close();
    }
}
