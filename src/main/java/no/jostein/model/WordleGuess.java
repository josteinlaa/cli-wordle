package no.jostein.model;

public class WordleGuess {
    private String guess;
    private String hint;
    private boolean isGuessCorrect;

    
    public WordleGuess(String guess, String hint) {
        this.guess = guess;
        this.hint = hint;
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

}
