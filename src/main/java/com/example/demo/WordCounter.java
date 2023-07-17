package com.example.demo;

public class WordCounter {
	
    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        // trim text om leading & ending spaties te negeren. 
        // regex voor het matchen van text tussen a-z en A-Z
        String[] words = text.trim().split("[^a-zA-Z]+");
        // return de lengte van de array words voor de tekst. 
        return words.length;
    }
}
