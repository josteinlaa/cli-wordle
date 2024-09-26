package no.jostein;

public class WordleGuess {
    private String guess;
    private String hint;

    
    public WordleGuess(String guess, String hint) {
        this.guess = guess;
        this.hint = hint;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getHint() {
        return hint;
    }
    
    public void setHint(String hint) {
        this.hint = hint;
    }


}
