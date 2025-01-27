import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.jostein.game.GameRound;
import no.jostein.util.LetterState;

class GameRoundTests {

    private static final String DEFAULT_ANSWER = "apple";
    private GameRound gameHandler;

    @BeforeEach
    void setup() {
        gameHandler = new GameRound(DEFAULT_ANSWER);
    }

    @Test
    void testAllCorrect() {
        gameHandler = new GameRound("apple");
        LetterState[] expected = new LetterState[] {
            LetterState.CORRECT_LETTER, 
            LetterState.CORRECT_LETTER, 
            LetterState.CORRECT_LETTER, 
            LetterState.CORRECT_LETTER, 
            LetterState.CORRECT_LETTER
        };
        assertArrayEquals(expected, gameHandler.getHint("apple"));
    }

    @Test
    void testNoMatches() {
        gameHandler = new GameRound("water");
        LetterState[] expected = new LetterState[] {
            LetterState.NOT_IN_ANSWER, 
            LetterState.NOT_IN_ANSWER, 
            LetterState.NOT_IN_ANSWER, 
            LetterState.NOT_IN_ANSWER, 
            LetterState.NOT_IN_ANSWER
        };
        assertArrayEquals(expected, gameHandler.getHint("polis"));
    }

    @Test
    void testCorrectAndWrongPositions() {
        gameHandler = new GameRound("crate");
        LetterState[] expected = new LetterState[] {
            LetterState.IN_ANSWER_WRONG_POSITION, 
            LetterState.CORRECT_LETTER, 
            LetterState.CORRECT_LETTER, 
            LetterState.IN_ANSWER_WRONG_POSITION, 
            LetterState.CORRECT_LETTER
        };
        assertArrayEquals(expected, gameHandler.getHint("trace"));
    }

    @Test
    void testRepeatedLettersInGuess() {
        gameHandler = new GameRound("apple");
        LetterState[] expected = new LetterState[] {
            LetterState.NOT_IN_ANSWER, 
            LetterState.CORRECT_LETTER, 
            LetterState.CORRECT_LETTER, 
            LetterState.NOT_IN_ANSWER, 
            LetterState.IN_ANSWER_WRONG_POSITION
        };
        assertArrayEquals(expected, gameHandler.getHint("ppppl"));
    }

    @Test
    void testGuessWithRepeatedLettersInAnswer() {
        gameHandler = new GameRound("ttttt");
        LetterState[] expected = new LetterState[] {
            LetterState.CORRECT_LETTER, 
            LetterState.NOT_IN_ANSWER, 
            LetterState.NOT_IN_ANSWER, 
            LetterState.NOT_IN_ANSWER, 
            LetterState.CORRECT_LETTER
        };
        assertArrayEquals(expected, gameHandler.getHint("tarot"));
    }
}

