package no.jostein.ui;

import java.util.List;

import no.jostein.model.WordleGuess;

public interface IUserInterface {
    String getUserGuess();
    void displayMenu();
    void displayGameState(List<WordleGuess> guessHistory);
    boolean getYesOrNo();
    void displayMessage(String message);
}
