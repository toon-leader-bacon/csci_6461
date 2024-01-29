package org.nocab.nocabmachine.nocab;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    @org.junit.jupiter.api.Test
    void toBinStringTests() {
        Field underTest = new Field((short)1, (short)1);

        assertEquals(underTest.toBinString(), "1");
    }
}