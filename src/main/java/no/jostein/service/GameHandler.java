package no.jostein.service;

import java.util.HashMap;

import no.jostein.data.IDictionary;
import no.jostein.model.WordleGuess;


public class GameHandler {

    private static final int MAX_GUESS = 5;
    private static final int WORD_LENGTH = 5;

    private String answer;

    private WordleGuess[] history;

    private IDictionary dictionary;


    public GameHandler(IDictionary dictionary) {
        this.dictionary = dictionary;
        this.answer = dictionary.getRandomWord();
        this.history = new WordleGuess[MAX_GUESS];

    }

    public GameHandler(String answer) {
        this.answer = answer;
    }


    public String getHint(String guess) {
        StringBuilder hintStr = new StringBuilder();
        HashMap<Character, Integer> charCount = new HashMap<>();

        char guessChar;
        char answerChar;

        for (char c : answer.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            guessChar = guess.charAt(i);
            answerChar = this.answer.charAt(i);

            if (guessChar == answerChar) {
                hintStr.append("g"); // Correct character in the correct position
                charCount.put(guessChar, charCount.get(guessChar) - 1);
            } 
            else {
                hintStr.append("n"); // placeholder
            }
        }
        
        for (int i = 0; i < WORD_LENGTH; i++) {
            guessChar = guess.charAt(i);
            
            if (hintStr.charAt(i) != 'g' && charCount.getOrDefault(guessChar, 0) > 0) {
                hintStr.setCharAt(i, 'y'); // Correct character, wrong position
                charCount.put(guessChar, charCount.get(guessChar) - 1); // Decrement frequency for wrong-position match
            }
        }

        return hintStr.toString();
    }

    public void makeGuess(String newGuess, int turn) {
        //isValidWord(newGuess) &&
        if ( turn < MAX_GUESS) {
            this.history[turn] = new WordleGuess(newGuess, getHint(newGuess));
        }
    }

    public WordleGuess[] getHistory() {
        return this.history;
    }

}
