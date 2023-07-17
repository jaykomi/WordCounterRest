package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word-frequency")
// word frecuenqy is de url voor de functies
public class DemoController {

    private final WordFrequencyAnalyzer analyzer;

    // functies aanspreken op basis van naming
    // per functie individuele parameters meesturen
    
    //  http://localhost:8080/word-frequency/highest-frequency?text=Dit%20is%20een%20test
    public DemoController(WordFrequencyAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    @GetMapping("/highest-frequency")
    public int calculateHighestFrequency(@RequestParam String text) {
        return analyzer.calculateHighestFrequency(text);
    }

    @GetMapping("/frequency-for-word")
    public int calculateFrequencyForWord(@RequestParam String text, @RequestParam String word) {
        return analyzer.calculateFrequencyForWord(text, word);
    }

    @PostMapping("/most-frequent-words")
    public List<WordFrequency> calculateMostFrequentNWords(@RequestParam String text, @RequestParam int n) {
        return analyzer.calculateMostFrequentNWords(text, n);
    }
}