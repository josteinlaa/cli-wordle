package no.jostein;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;


public class GameHandler {

    private static final int MAX_GUESS = 5;
    private static final int WORD_LENGTH = 5;

    private HashSet<String> wordList;

    private String answer;

    private WordleGuess[] history; 


    public GameHandler() {
        // TODO ikke her! singleton? i Game kanskje også answer of wordlist i constructor, en ny handler per nye game?
        this.wordList = readWordsFromFile();
        this.answer = selectRandomWord();
        this.history = new WordleGuess[MAX_GUESS];

    }

    public GameHandler(String answer) {
        this.answer = answer;
    }
    
    
    private HashSet<String> readWordsFromFile() {
        HashSet<String> words = new HashSet<>();
        BufferedReader reader;

        String fs = File.separator;
        
        try {
            reader = new BufferedReader(new FileReader("src" + fs + "main" + fs + "resources" + fs + "words" + fs + "words.txt"));
            String line = reader.readLine();

            while (line != null) {
                //TODO not length 5 word, exeption handling
                words.add(line);
                line = reader.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

    private String selectRandomWord() {
        Random random = new Random();
        int randIdx = random.nextInt(this.wordList.size());

        return this.wordList.toArray()[randIdx].toString();
    }

    private boolean isValidWord(String word) {
        return this.wordList.contains(word);
    }

    public String getHint(String guess) {
        StringBuilder hintStr = new StringBuilder();
        HashMap<Character, Integer> charCount = new HashMap<>();

        char guessChar;
        char answerChar;

        for (char c : answer.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            guessChar = guess.charAt(i);
            answerChar = this.answer.charAt(i);

            if (guessChar == answerChar) {
                hintStr.append("g"); // Correct character in the correct position
                charCount.put(guessChar, charCount.get(guessChar) - 1);
            } 
            else {
                hintStr.append("n"); // placeholder
            }
        }
        
        for (int i = 0; i < WORD_LENGTH; i++) {
            guessChar = guess.charAt(i);
            
            if (hintStr.charAt(i) != 'g' && charCount.getOrDefault(guessChar, 0) > 0) {
                hintStr.setCharAt(i, 'y'); // Correct character, wrong position
                charCount.put(guessChar, charCount.get(guessChar) - 1); // Decrement frequency for wrong-position match
            }
        }

        return hintStr.toString();
    }

    public void makeGuess(String newGuess, int turn) {
        //isValidWord(newGuess) &&
        if ( turn < MAX_GUESS) {
            this.history[turn] = new WordleGuess(newGuess, getHint(newGuess));
        }
    }

    public WordleGuess[] getHistory() {
        return this.history;
    }

}
