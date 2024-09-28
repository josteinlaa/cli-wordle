package no.jostein.ui;

import java.util.List;

import no.jostein.model.WordleGuess;

public interface IUserInterface {
    String getUserGuess();
    void displayInfo();
    void displayGameState(List<WordleGuess> guessHistory);
}
