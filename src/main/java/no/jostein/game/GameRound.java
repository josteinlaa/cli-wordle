package no.jostein.game;

import java.util.ArrayList;
import java.util.HashMap;

import no.jostein.data.IDictionary;
import no.jostein.model.WordleGuess;


public class GameRound {
    private static final int MAX_GUESSES = 5; 
    private static final int WORD_LENGTH = 5;

    private ArrayList<WordleGuess> guessHistory;
    private IDictionary dictionary;
    private String answer;
    private int guessesRemaining;

    public GameRound(IDictionary dictionary) {
        this.dictionary = dictionary;
        this.answer = dictionary.getRandomWord();
        this.guessHistory = new ArrayList<>();
        this.guessesRemaining = MAX_GUESSES;
    }

    public GameRound(String answer) {
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

    public void makeGuess(String guess) {
        if (guessesRemaining <= 0) {
            throw new IllegalStateException("No guesses remaining");
        }

        if (guess.length() != WORD_LENGTH) {
            throw new IllegalArgumentException("Guess must be " + WORD_LENGTH + " characters long");
        }

        /*if (!dictionary.isValidWord(guess)) {
            throw new IllegalArgumentException("Invalid word");
        }*/

        guessHistory.add(new WordleGuess(guess, getHint(guess), guess.length()));
        guessesRemaining--;
    }

    public boolean isRoundOver() {
        return guessesRemaining == 0 || guessHistory.stream().anyMatch(WordleGuess::getIsGuessCorrect);
    }

    public int getGuessesRemaining() {
        return guessesRemaining;
    }

    public ArrayList<WordleGuess> getGuessHistory() {
        return this.guessHistory;
    }
}
