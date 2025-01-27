package no.jostein.model;

import no.jostein.util.LetterState;

public class WordleGuess {
    private final String guess;
    private final LetterState[] hint;
    private final int wordLen;
    private final boolean isGuessCorrect;

    
    public WordleGuess(String guess, LetterState[] hint, int wordLen) {
        this.guess = guess;
        this.hint = hint;
        this.wordLen = wordLen;
        this.isGuessCorrect = java.util.Arrays.stream(hint).allMatch(letterState -> letterState == LetterState.CORRECT_LETTER);
    }

    public String getGuess() {
        return guess;
    }

    public LetterState[] getHint() {
        return hint;
    }
    
    public boolean getIsGuessCorrect() {
        return isGuessCorrect;
    }

    public int getWordLen() {
        return wordLen;
    }

}
