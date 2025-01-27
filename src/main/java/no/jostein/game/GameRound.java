package no.jostein.game;

import java.util.ArrayList;
import java.util.HashMap;

import no.jostein.model.WordleGuess;
import no.jostein.util.LetterState;


public class GameRound {
    private static final int MAX_GUESSES = 5; 
    private static final int WORD_LENGTH = 5;

    private ArrayList<WordleGuess> guessHistory;
    private final String answer;
    private int guessesRemaining;

    public GameRound(String answer) {
        this.answer = answer;
        this.guessHistory = new ArrayList<>();
        this.guessesRemaining = MAX_GUESSES;
    }

    private HashMap<Character, Integer> getCharFrequencies(String s) {
        HashMap<Character, Integer> answerCharFreqencies = new HashMap<>();

        for (char c : s.toCharArray()) {
            answerCharFreqencies.put(c, answerCharFreqencies.getOrDefault(c, 0) + 1);
        }

        return answerCharFreqencies;
    }

    private void decreaseCharFrequency(HashMap<Character, Integer> answerCharFreqencies, char c) {
        answerCharFreqencies.put(c, answerCharFreqencies.get(c) - 1);
    }

    private boolean charSomewhereInAnswer(char guessChar, HashMap<Character, Integer> answerCharFreqencies) {
        return answerCharFreqencies.getOrDefault(guessChar, 0) > 0;
    }

    public LetterState[] getHint(String guess) {
        LetterState[] hint = new LetterState[WORD_LENGTH];

        HashMap<Character, Integer> answerCharFreqencies = getCharFrequencies(answer);

        char guessChar;
        char answerChar;

        for (int i = 0; i < WORD_LENGTH; i++) {
            guessChar = guess.charAt(i);
            answerChar = this.answer.charAt(i);

            if (guessChar == answerChar) {
                hint[i] = LetterState.CORRECT_LETTER; // Correct character in the correct position
                decreaseCharFrequency(answerCharFreqencies, guessChar);
            } 
            else {
                hint[i] = LetterState.NOT_IN_ANSWER; // char not in word (could be placeholder)
            }
        }
        
        for (int i = 0; i < WORD_LENGTH; i++) {
            guessChar = guess.charAt(i);
            
            if (hint[i] != LetterState.CORRECT_LETTER && charSomewhereInAnswer(guessChar, answerCharFreqencies)) {
                
                hint[i] = LetterState.IN_ANSWER_WRONG_POSITION; // Correct character, wrong position
                decreaseCharFrequency(answerCharFreqencies, guessChar); // Decrement frequency for wrong-position match
            }
        }

        return hint;
    }

    public void makeGuess(String guess) throws IllegalArgumentException { 

        if (guess.length() != WORD_LENGTH) {
            throw new IllegalArgumentException("Guess must be " + WORD_LENGTH + " characters long");
        }

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

    public String getAnswer() {
        return this.answer;
    }
}
