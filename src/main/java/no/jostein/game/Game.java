package no.jostein.game;

import no.jostein.data.IDictionary;
import no.jostein.ui.IUserInterface;

public class Game {
    private IUserInterface userInterface;
    private final IDictionary dictionary;
    private int totalRounds;


    public Game(IUserInterface userInterface, IDictionary dictionary) {
        this.userInterface = userInterface;
        this.dictionary = dictionary;
        this.totalRounds = 0;
    }

    public void runGame() {
        userInterface.displayInfo();

        boolean playAgain = true;
        while (playAgain) {
            totalRounds++;
            GameRound gameRound = new GameRound(dictionary);

            // Play a single round
            playRound(gameRound);
        }

    }

    private void playRound(GameRound gameRound) {
        while (!gameRound.isRoundOver()) {
            userInterface.displayGameState(gameRound.getGuessHistory());
            gameRound.makeGuess(userInterface.getUserGuess());
        }
    }
}
