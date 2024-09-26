package no.jostein;

enum Colors {
    //Color end string, color reset
    RESET("\033[0m"),
   // RED
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m");   // YELLOW

    private final String code;

    Colors(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}

public class GameRenderer {
    private Colors getColorCode(Character c) {
        switch (c) {
            case 'g':
                return Colors.GREEN;
            case 'y':
                return Colors.YELLOW;
            default:
                return Colors.RESET;
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
                System.out.print(Colors.RESET);
                System.out.flush(); 
            }
        }
       
    }

    public void printGameStart() {}

    public void printNewGame() {}

    public void printWon() {}

    public void printLost() {}

    public void clearTerminal() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public void printNotAWord() {}

    public void printWordLength() {}
}
