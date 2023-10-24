package com.wordinsight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DictionaryParser {
    private ObjectMapper objectMapper;

    public DictionaryParser() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Parse JSON String into a <code>Word</code>
     * object
     *
     * @throws IllegalArgumentException cannot parse given JSON to <code>Word</code>
     */
    public WordData parse(String json) {
        try {
            WordData[] wordArr = objectMapper.readValue(json, WordData[].class);
            return wordArr[0];
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Invalid JSON String for Word object");
    }
}
