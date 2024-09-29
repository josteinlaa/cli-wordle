package no.jostein.ui;

import java.util.List;
import java.util.Scanner;

import no.jostein.model.WordleGuess;
import no.jostein.util.ColorCode;



public class CLIUserInterface  implements IUserInterface {

    private Scanner scanner = new Scanner(System.in);

    private ColorCode getColorCode(Character c) {
        switch (c) {
            case 'g':
                return ColorCode.GREEN;
            case 'y':
                return ColorCode.YELLOW;
            default:
                return ColorCode.RESET;
        }
    }

    public void printTurn(WordleGuess[] history){
        
       
    }

    private void clearTerminal() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    @Override
    public String getUserGuess() {
        return scanner.nextLine().trim();
    }

    @Override
    public void displayMenu() {
        clearTerminal();
        System.out.println("Welcome to Wordle!");
        System.out.println("Guess the 5-letter word in 5 attempts.");
        System.out.println("Hints: [Green color] = correct letter & position, [Yellow color] = correct letter, wrong position, [No color] = letter not in word.");
        System.out.println("Type 'y' to start. Good luck! Press ctrl-c at any time to exit game.\n");
    }

    @Override
    public void displayGameState(List<WordleGuess> guessHistory) {
        clearTerminal();
        System.out.println("Guess the word:");
        for (WordleGuess wg : guessHistory) {
            if (wg != null) {
                for (int j = 0; j < wg.getWordLen(); j++) {
                    System.out.print(getColorCode(wg.getHint().charAt(j)));
                    System.out.print((Character) wg.getGuess().charAt(j));
                }
                System.out.print("\n");
                System.out.print(ColorCode.RESET);
                System.out.flush(); 
            }
        }
    }

    @Override
    public boolean getYesOrNo() {
        return scanner.nextLine().equalsIgnoreCase("y");
    }

    @Override
    public void displayMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
