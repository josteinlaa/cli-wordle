

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import no.jostein.game.GameRound;

class GameHandlerTests {

    @Test
    void getHint() {

        String answer = "water";
        GameRound gameHandler = new GameRound(answer);

        assertEquals("nnggg", gameHandler.getHint("otter"));


        answer = "torta";
        gameHandler = new GameRound(answer);

        assertEquals("gygyy", gameHandler.getHint("tarot"));

        answer = "ttttt";
        gameHandler = new GameRound(answer);

        assertEquals("gnnng", gameHandler.getHint("tarot"));
    }

}
