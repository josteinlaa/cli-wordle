package no.jostein;

import no.jostein.game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        while (true) {
            game.runGame();
        }
    }
}