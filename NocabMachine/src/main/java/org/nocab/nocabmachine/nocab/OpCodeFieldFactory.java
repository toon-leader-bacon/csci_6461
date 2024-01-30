package org.nocab.nocabmachine.nocab;

public class OpCodeFieldFactory {

    public static final int OPERATION_CODE_SIZE = 6;

    public static Field opCodeStrToField(String opCode) {
        return new Field(opCodeToValue(opCode), OPERATION_CODE_SIZE);
    }

    public static int opCodeToValue(String opCode) {
        opCode = opCode.toLowerCase().replace(":", "");
        switch (opCode) {
            /* Undocumented Instructions */
            case "data" -> {
                return Integer.parseInt("000", 8);
            }
            case "loc", "end" -> {
                return Integer.parseInt("00", 8);
            }

            /* Miscellaneous Instructions */
            case "hlt" -> {
                return Integer.parseInt("00", 8);
            }
            case "trap" -> {
                return Integer.parseInt("045", 8);
            }
            /* Load/Store Instructions */
            case "ldr" -> {
                return Integer.parseInt("01", 8);
            }
            case "str" -> {
                return Integer.parseInt("02", 8);
            }
            case "lda" -> {
                return Integer.parseInt("03", 8);
            }
            case "ldx" -> {
                return Integer.parseInt("04", 8);
            }
            case "stx" -> {
                return Integer.parseInt("05", 8);
            }
            /* Transfer Instructions */
            case "setcce" -> {
                return Integer.parseInt("44", 8);
            }
            case "jz" -> {
                return Integer.parseInt("06", 8);
            }
            case "jne" -> {
                return Integer.parseInt("07", 8);
            }
            case "jcc" -> {
                return Integer.parseInt("10", 8);
            }
            case "jma" -> {
                return Integer.parseInt("11", 8);
            }
            case "jsr" -> {
                return Integer.parseInt("12", 8);
            }
            case "rfs" -> {
                return Integer.parseInt("13", 8);
            }
            case "sob" -> {
                return Integer.parseInt("14", 8);
            }
            case "jge" -> {
                return Integer.parseInt("15", 8);
            }
            /* Arithmetic and Logical Instructions */
            case "amr" -> {
                return Integer.parseInt("16", 8);
            }
            case "smr" -> {
                return Integer.parseInt("17", 8);
            }
            case "air" -> {
                return Integer.parseInt("20", 8);
            }
            case "sir" -> {
                return Integer.parseInt("21", 8);
            }
            /* Register to Register */
            case "mlt" -> {
                return Integer.parseInt("22", 8);
            }
            case "dvd" -> {
                return Integer.parseInt("23", 8);
            }
            case "trr" -> {
                return Integer.parseInt("24", 8);
            }
            case "and" -> {
                return Integer.parseInt("25", 8);
            }
            case "orr" -> {
                return Integer.parseInt("26", 8);
            }
            case "not" -> {
                return Integer.parseInt("27", 8);
            }

            /* Shit/Rotate Operations */
            case "src" -> {
                return Integer.parseInt("30", 8);
            }
            case "rrc" -> {
                return Integer.parseInt("31", 8);
            }

            /* I/O Operations */
            case "in" -> {
                return Integer.parseInt("32", 8);
            }
            case "out" -> {
                return Integer.parseInt("33", 8);
            }
            case "chk" -> {
                return Integer.parseInt("34", 8);
            }

            /* Floating Point Instructions/Vector Operations */
            case "fadd" -> {
                return Integer.parseInt("35", 8);
            }
            case "fsub" -> {
                return Integer.parseInt("36", 8);
            }
            case "vadd" -> {
                return Integer.parseInt("37", 8);
            }
            case "vsub" -> {
                return Integer.parseInt("40", 8);
            }
            case "cnvrt" -> {
                return Integer.parseInt("41", 8);
            }
            case "ldfr" -> {
                return Integer.parseInt("42", 8);
            }
            case "stfr" -> {
                return Integer.parseInt("43", 8);
            }
            default -> throw new IllegalArgumentException("Unknown Operation Code: " + opCode);
        }
    }
}
