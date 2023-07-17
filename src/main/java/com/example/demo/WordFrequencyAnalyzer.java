package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import interfaces.IWordFrequencyAnalyzer;

@Component
//hiermee geef je aan dat het als een spring "bean" behandeld moet worden
public class WordFrequencyAnalyzer implements IWordFrequencyAnalyzer {

	@Override
	// bereken hoogste frequentie functie
	public int calculateHighestFrequency(String text) {
		// Bouw de frequentiemap op
		Map<String, Integer> frequencyMap = buildFrequencyMap(text);

		// Initialiseer de maximale frequentie
		int currentMaxFrequency = 0;

		// Doorloop alle frequenties in de map en vind de maximale frequentie
		// Is de frequentie hoger dan de maximum frequentie set de nieuwe value voor max frequentie
		for (int frequency : frequencyMap.values()) {
			if (frequency > currentMaxFrequency) {
				currentMaxFrequency = frequency;
			}
		}

		// Geef de maximale frequentie terug
		return currentMaxFrequency;
	}

	@Override
	// bereken frequentie per woord. 
	public int calculateFrequencyForWord(String text, String word) {
		// Bouw de frequentiemap op
		Map<String, Integer> frequencyMap = buildFrequencyMap(text);

		// Haal de frequentie op voor het opgegeven woord (lowercase)
		int frequency = frequencyMap.getOrDefault(word.toLowerCase(), 0);

		// Geef de frequentie van het woord terug
		return frequency;
	}

	@Override
	public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
		// Bouw de frequentiemap op
		Map<String, Integer> frequencyMap = buildFrequencyMap(text);

		// Maak een heap voor WordFrequency-data (), gesorteerd op frequentie (sleutel) in dalende volgorde ,
		// Bij gelijke frequentie op woord in oplopende alfabetische volgorde
		
		//comparing INT zet ze op volgorde van frequentie - thencomparing i.c.m. CASE_INSENSITIVE_ORDER vergelijkt woorden op alfabetische volgorde
		// en negeert daarbij evt hoofdletters. 
		
		PriorityQueue<WordFrequency> wordFrequencyHeap = new PriorityQueue<>(
				Comparator.comparingInt(WordFrequency::getFrequency).reversed().thenComparing(WordFrequency::getWord,
						String.CASE_INSENSITIVE_ORDER));

		// Voeg elk woord en de bijbehorende frequentie toe aan de wachtrij
		for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
			wordFrequencyHeap.offer(new WordFrequency(entry.getKey(), entry.getValue()));
		}

		// Maak een lijst om de 'n' meest voorkomende woorden op te slaan
		List<WordFrequency> mostFrequentNWords = new ArrayList<>();

		// Haal de 'n' meest voorkomende woorden uit de wachtrij en voeg ze toe aan de
		// Stop als int n bereikt is. Maximum wordt bepaald in main. 
		for (int i = 0; i < n && !wordFrequencyHeap.isEmpty(); i++) {
			mostFrequentNWords.add(wordFrequencyHeap.poll());
		}

		// Geef de lijst met de 'n' meest voorkomende woorden terug
		return mostFrequentNWords;
	}

	private Map<String, Integer> buildFrequencyMap(String text) {
		Map<String, Integer> frequencyMap = new HashMap<>();

		// zelfde regex functie voor het herkennen van woorden i.p.v. random cijfers of
		// karakters.
		String[] words = text.split("[^a-zA-Z]+");

		// loop voor ieder woord in de array words, vertaal het woord naar lowercase en
		// map dit woord met zijn frequentie.
		// default frequentie = 0 | get = ++
		for (String word : words) {
			String lowercaseWord = word.toLowerCase();
			frequencyMap.put(lowercaseWord, frequencyMap.getOrDefault(lowercaseWord, 0) + 1);
		}

		return frequencyMap;
	}
}
