package com.example.demo;

import interfaces.IWordFrequency;

public class WordFrequency implements IWordFrequency {
    private String word;
    private int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    @Override
    // return het woord voor de listview
    public String getWord() {
        return word;
    }

    @Override
    // return de frequentie van het woord
    public int getFrequency() {
        return frequency;
    }
}