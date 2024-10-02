package no.jostein.game;

import no.jostein.data.IDictionary;
import no.jostein.ui.IUserInterface;
import no.jostein.util.GameState;

public class Game {
    private IUserInterface userInterface;
    private final IDictionary dictionary;
    private GameState gameState;


    public Game(IUserInterface userInterface, IDictionary dictionary) {
        this.userInterface = userInterface;
        this.dictionary = dictionary;
        this.gameState = GameState.MENU;
    }

    public void runGame() {
        while (true) {
            switch (this.gameState) {
                case MENU:
                    userInterface.displayMenu();
                    boolean startPlay = userInterface.getYesOrNo();

                    if (startPlay) {
                        this.gameState = GameState.PLAYING; 
                    }

                    break;
                case PLAYING:
                    playRound(new GameRound(dictionary.getRandomWord()));
                    this.gameState = GameState.ROUND_OVER;
                    break;
                case ROUND_OVER:
                    userInterface.displayMessage("Play again? Type 'y'.");
                    boolean playAgain = userInterface.getYesOrNo();

                    if (!playAgain) {
                        this.gameState = GameState.MENU;
                    } else {
                        this.gameState = GameState.PLAYING;
                    }
                    
                    break;
            }
        }
    }

    private void playRound(GameRound gameRound) {
        while (!gameRound.isRoundOver()) {
            userInterface.displayGameState(gameRound.getGuessHistory());
            try {
                gameRound.makeGuess(userInterface.getUserGuess());
            } catch (Exception e) {
                userInterface.displayMessage(e.getMessage() + " Press Enter to continue...");
                userInterface.getYesOrNo();
            }
        }

        if (gameRound.getGuessesRemaining() > 0) {
            userInterface.displayMessage("You WON!");
        } else {
            userInterface.displayMessage("You LOST!");
        }
    }
}
