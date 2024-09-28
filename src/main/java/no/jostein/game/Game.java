package no.jostein.game;

import java.util.Scanner;

import no.jostein.service.GameHandler;
import no.jostein.ui.CLIUserInterface;
import no.jostein.ui.IUserInterface;

public class Game {
    private GameHandler gameHandler;
    private IUserInterface userInterface;

    public Game(GameHandler gameHandler, IUserInterface userInterface) {
        this.gameHandler = gameHandler;
        this.userInterface = userInterface;
    }

    public void runGame() {
        CLIUserInterface gr = new CLIUserInterface();
        String guess;

        while (true) {
            System.out.println("NEW GAME. Guess the word:");
            for (int i = 0; i < 5; i++) {
                guess = gr.getUserGuess();
                gh.makeGuess(guess, i);
                gr.clearTerminal();
                gr.printTurn(gh.getHistory());
        }
            }
    }
}
