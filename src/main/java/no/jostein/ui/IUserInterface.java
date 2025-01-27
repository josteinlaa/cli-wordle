package no.jostein.ui;

import java.util.List;

import no.jostein.game.GameRound;
import no.jostein.model.WordleGuess;

public interface IUserInterface {
    String getUserGuess();
    void displayMenu();
    void displayGameState(List<WordleGuess> guessHistory);
    boolean getYesOrNo();
    void displayMessage(String message);
    void promptPlayAgain();
    void displayRoundOver(boolean isWon, GameRound gameRound);
}
