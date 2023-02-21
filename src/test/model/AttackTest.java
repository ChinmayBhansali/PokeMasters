package model;

import model.attacks.RockThrow;
import model.attacks.Tackle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AttackTest {
    private Attack a1;
    private Attack a2;

    @BeforeEach
    void setup() {
        a1 = new Tackle();
        a2 = new RockThrow();
    }

    @Test
    void testConstructor() {
        assertEquals("Tackle", a1.getName());
        assertEquals(40, a1.getPower());

        assertEquals("Rock Throw", a2.getName());
        assertEquals(50, a2.getPower());
    }
}