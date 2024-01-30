package org.nocab.nocabmachine.nocab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpCodeFieldFactoryTest {

    @Test
    void opCodeStrToField() {
    }

    @Test
    void opCodeToValue() {
        assertEquals(6, OpCodeFieldFactory.opCodeToValue("jz"));
    }
}