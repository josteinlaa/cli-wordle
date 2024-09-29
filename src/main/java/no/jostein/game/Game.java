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

                    boolean playAgain = userInterface.getYesOrNo();
                    System.out.println("gmmm");
                    if (playAgain) {
                        this.gameState = GameState.PLAYING; 
                    }
                    break;
                case PLAYING:
                    playRound(new GameRound(dictionary.getRandomWord()));
                    break;
                case ROUND_OVER:
                    break;
            }
        }
    }

    private void playRound(GameRound gameRound) {
        while (!gameRound.isRoundOver()) {
            userInterface.displayGameState(gameRound.getGuessHistory());
            gameRound.makeGuess(userInterface.getUserGuess());
        }
    }
}
