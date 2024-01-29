package org.nocab.nocabmachine.nocab;

import java.util.ArrayList;

public class Instruction {
    Field opCode;
    ArrayList<Field> fields;

    /*
    String toString_Octal() {
        StringBuilder result = new StringBuilder(16);

        String opCodeStr = toBinStr(opCode);
        result.append(opCodeStr);

        for (Field fields : fields) {
            String fieldStr = toBinStr(fields);
            result.append(fieldStr);
        }

        String instructionAsBinary = result.toString();
        String instructionAsOctal = toOctalStr(instructionAsBinary);
    }
    */
}
