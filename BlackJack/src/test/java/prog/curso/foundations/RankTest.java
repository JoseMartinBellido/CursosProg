package prog.curso.foundations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    void getValueKing() {
        assertEquals(10, Rank.KING.getValue());
    }
}