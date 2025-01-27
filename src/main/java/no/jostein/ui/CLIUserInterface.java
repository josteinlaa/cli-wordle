package no.jostein.ui;

import java.util.List;
import java.util.Scanner;

import no.jostein.game.GameRound;
import no.jostein.model.WordleGuess;
import no.jostein.util.ColorCode;
import no.jostein.util.LetterState;

public class CLIUserInterface implements IUserInterface {

    private final Scanner scanner = new Scanner(System.in);


    // Runtime shutdown hook, to gracefully close the scanner
    public CLIUserInterface() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (scanner != null) {
                scanner.close();
            }
        }));
    }

    private ColorCode LetterStateTColorCode(LetterState ls) {
        return switch (ls) {
            case CORRECT_LETTER -> ColorCode.GREEN;
            case IN_ANSWER_WRONG_POSITION -> ColorCode.YELLOW;
            default -> ColorCode.RESET;
        };
    }

    private void changeColorCodePrintChar(LetterState ls, char c) {
        System.out.print(LetterStateTColorCode(ls));
        System.out.print(c);
        System.out.print(ColorCode.RESET);
    }

    private void printHistoryWithHint(List<WordleGuess> guessHistory){
        for (WordleGuess wg : guessHistory) {
            if (wg != null) {
                for (int j = 0; j < wg.getWordLen(); j++) {
                    changeColorCodePrintChar(wg.getHint()[j], wg.getGuess().charAt(j));
                }
                System.out.print("\n");
                System.out.flush(); 
            }
        }
    }

    private void clearTerminal() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    @Override
    public String getUserGuess() {
        try {
            return scanner.nextLine().trim();
        } catch (Exception e) {
            return "";
        }
        
    }

    @Override
    public void displayMenu() {
        clearTerminal();
        System.out.println("Welcome to Wordle!");
        System.out.println("Guess the 5-letter word in 5 attempts.");
        System.out.println("Hints: [Green color] = correct letter & position, [Yellow color] = correct letter, wrong position, [No color] = letter not in word.");
        System.out.println("Press ctrl-c at any time to exit game. Would you like to start? Type 'y'.\n");
    }

    @Override
    public void displayGameState(List<WordleGuess> guessHistory) {
        clearTerminal();
        displayMessage("Guess the word:");
        printHistoryWithHint(guessHistory);
    }

    @Override
    public boolean getYesOrNo() {
        try {
            return scanner.nextLine().equalsIgnoreCase("y");
        } catch (Exception e) {
           return false;
        }
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void promptPlayAgain() {
        System.out.println("Play again? Type 'y'.");
    }

    @Override
    public void displayRoundOver(boolean isWon, GameRound gameRound) {
        if (isWon) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("You lost! The word was: " + gameRound.getAnswer());
        }
    }
}
