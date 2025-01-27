package no.jostein.game;

import no.jostein.data.IDictionary;
import no.jostein.ui.IUserInterface;
import no.jostein.util.GameState;

public class Game {
    private final IUserInterface userInterface;
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
                case MENU -> handleMenu();
                case PLAYING -> handlePlaying();
                case ROUND_OVER -> handleRoundOver();
            }
        }
    }

    private void handleMenu() {
        userInterface.displayMenu();
        boolean startPlay = userInterface.getYesOrNo();

        if (startPlay) {
            this.gameState = GameState.PLAYING; 
        }
    }

    private void handlePlaying() {
        playRound(new GameRound(dictionary.getRandomWord()));
        this.gameState = GameState.ROUND_OVER;
    }

    private void handleRoundOver() {
        userInterface.promptPlayAgain();
        boolean playAgain = userInterface.getYesOrNo();

        if (!playAgain) {
            this.gameState = GameState.MENU;
        } else {
            this.gameState = GameState.PLAYING;
        }
    }

    private void playRound(GameRound gameRound) {
        while (!gameRound.isRoundOver()) {
                userInterface.displayGameState(gameRound.getGuessHistory());

            try {
                
                gameRound.makeGuess(userInterface.getUserGuess());
            } catch (IllegalArgumentException e) {
                userInterface.displayMessage(e.getMessage() + " Press Enter to continue...");
                userInterface.getYesOrNo();
            }
        }

        userInterface.displayRoundOver(
                    gameRound.getGuessHistory()
                    .get(gameRound.getGuessHistory().size() - 1)
                    .getIsGuessCorrect(), gameRound);
    }
}
