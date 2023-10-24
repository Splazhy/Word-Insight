package com.wordinsight;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.net.HttpURLConnection;

public class DictionaryFetcher {
    /**
     * Fetch a JSON String from given word
     * <p>
     * the API is provided by <link>https://dictionaryapi.dev/</link>
     *
     * @param word
     * @return an <code>Optional</code> of JSON String
     */
    public static Optional<String> get(String word) {
        try {
            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                return Optional.of(response.toString());
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("the word \"" + word + "\" doesn't exist on the API");
            }
            System.out.println("error code: " + responseCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}