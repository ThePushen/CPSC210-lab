package ca.ubc.cpsc210.colour;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColourTest {
    Colour c1;
    Colour c2;
    Colour c3;

    @BeforeEach
    void runBefore() {
        c1 = new Colour(50, 50, 50);
        c2 = new Colour(254, 101, 11);
        c3 = new Colour(30, 100, 200);

    }

    @Test
    void testIsGreyScale() {
        assertTrue(c1.isGreyScale());
        assertFalse(c2.isGreyScale());
        assertFalse(c3.isGreyScale());

    }

    @Test
    void testToHex() {
        assertEquals(Integer.toHexString((50 * 256 + 50) * 256 + 50), c1.toHex());
        assertEquals(Integer.toHexString((254 * 256 + 101) * 256 + 11), c2.toHex());
        assertEquals(Integer.toHexString((30 * 256 + 100) * 256 + 200), c3.toHex());
    }
}