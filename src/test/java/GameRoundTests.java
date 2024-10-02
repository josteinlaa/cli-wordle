import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.jostein.game.GameRound;

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
        assertEquals("ggggg", gameHandler.getHint("apple"));
    }

    @Test
    void testNoMatches() {
        gameHandler = new GameRound("water");
        assertEquals("nnnnn", gameHandler.getHint("polis"));
    }

    @Test
    void testCorrectAndWrongPositions() {
        gameHandler = new GameRound("crate");
        assertEquals("yggyg", gameHandler.getHint("trace"));
    }

    @Test
    void testRepeatedLettersInGuess() {
        gameHandler = new GameRound("apple");
        assertEquals("nggny", gameHandler.getHint("ppppl"));
    }

    @Test
    void testGuessWithRepeatedLettersInAnswer() {
        gameHandler = new GameRound("ttttt");
        assertEquals("gnnng", gameHandler.getHint("tarot"));
    }

}

