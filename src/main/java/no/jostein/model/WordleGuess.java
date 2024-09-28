package no.jostein.model;

public class WordleGuess {
    private String guess;
    private String hint;
    private int wordLen;
    private boolean isGuessCorrect;

    
    public WordleGuess(String guess, String hint, int wordLen) {
        this.guess = guess;
        this.hint = hint;
        this.wordLen = wordLen;
        this.isGuessCorrect = hint.chars().allMatch(c -> c == 'g');
    }

    public String getGuess() {
        return guess;
    }

    public String getHint() {
        return hint;
    }
    
    public boolean getIsGuessCorrect() {
        return isGuessCorrect;
    }

    public int getWordLen() {
        return wordLen;
    }

}
