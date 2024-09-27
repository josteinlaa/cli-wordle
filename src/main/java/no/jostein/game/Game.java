package no.jostein.game;

import java.util.Scanner;

import no.jostein.service.GameHandler;
import no.jostein.ui.GameRenderer;

public class Game {
    public void runGame() {
        GameHandler gh = new GameHandler();
        GameRenderer gr = new GameRenderer();
        Scanner scanner = new Scanner(System.in);
        String guess;

      

        while (true) {
            System.out.println("NEW GAME. Guess the word:");
            for (int i = 0; i < 5; i++) {
                guess = scanner.nextLine();
                gh.makeGuess(guess, i);
                gr.clearTerminal();
                gr.printTurn(gh.getHistory());
        }
            }
    }
}
