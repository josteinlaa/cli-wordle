package no.jostein;

import no.jostein.data.FileDictionary;
import no.jostein.game.Game;
import no.jostein.ui.CLIUserInterface;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new CLIUserInterface(), new FileDictionary());
        game.runGame();
    }
}