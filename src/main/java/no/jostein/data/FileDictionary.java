package no.jostein.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileDictionary implements IDictionary {

    private Set<String> words;

    public FileDictionary() {
        this.words = loadWordsFromFile();  // Load words on initialization
    }


    @Override
    public boolean isValidWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getRandomWord() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Set<String> loadWordsFromFile() {
        HashSet<String> readWords = new HashSet<>();
        BufferedReader reader;

        String fs = File.separator;
        
        try {
            reader = new BufferedReader(new FileReader("src" + fs + "main" + fs + "resources" + fs + "words" + fs + "words.txt"));
            String line = reader.readLine();

            while (line != null) {
                //TODO not length 5 word, exeption handling
                readWords.add(line);
                line = reader.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readWords;
    }

}
