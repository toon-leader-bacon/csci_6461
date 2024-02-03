package org.nocab.nocabmachine.nocab;

import org.junit.jupiter.api.Test;
import org.nocab.nocabmachine.nocab.DataStructures.OpCode;

class OpCodeFieldFactoryTest {

    @Test
    void opCodeStrToField() {
    }

    @Test
    void opCodeToValue() {
        assertEquals(6, OpCode.opCodeToValue("jz"));
    }
}