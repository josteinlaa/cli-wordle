package no.jostein.ui;

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
        for (WordleGuess wg : history) {
            if (wg != null) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(getColorCode(wg.getHint().charAt(j)));
                    System.out.print((Character) wg.getGuess().charAt(j));
                }
                System.out.print("\n");
                System.out.print(ColorCode.RESET);
                System.out.flush(); 
            }
        }
       
    }

    public void clearTerminal() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    @Override
    public String getUserGuess() {
        return scanner.nextLine().trim();
    }

    @Override
    public void displayInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void displayGameState() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
