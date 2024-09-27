

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import no.jostein.service.GameHandler;

class GameHandlerTests {

    @Test
    void getHint() {

        String answer = "water";
        GameHandler gameHandler = new GameHandler(answer);

        assertEquals("nnggg", gameHandler.getHint("otter"));


        answer = "torta";
        gameHandler = new GameHandler(answer);

        assertEquals("gygyy", gameHandler.getHint("tarot"));

        answer = "ttttt";
        gameHandler = new GameHandler(answer);

        assertEquals("gnnng", gameHandler.getHint("tarot"));
    }

}
