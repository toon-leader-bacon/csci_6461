package org.nocab.nocabmachine.nocab;


import org.nocab.nocabmachine.nocab.DataStructures.Instruction;
import org.nocab.nocabmachine.nocab.DataStructures.SourceProgramLine;
import org.nocab.nocabmachine.nocab.FieldProcessors.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SourceProgramReader {

    public static final String END_MEMORY_LOCATION_STR = "1024";

    public SourceProgramReader() {
    }

    void blab(String fileToRead, boolean repeatSourceProgramLine) {
        // Parse the source program line by line
        // Split each line on tab characters
        // First token = memory location (or empty)
        // Second token = opCode+Fields
        // extra tokens are all comments/ un-needed


        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileToRead));
            String line = reader.readLine();

            int nextMemoryLocation = 0;
            // Loop over each line in the file
            while (line != null) {
                if (line.isEmpty()) {
                    line = reader.readLine();
                    continue;
                }
                //System.out.println("Processing line: " + line);
                SourceProgramLine spLine = buildSourceProgramLine(line);

                // Figure out the memory location part
                if (spLine.memoryLocation == null) {
                    // LOC instructions will have a null memory location
                    // Simply use the "next memory location" as this line's location
                    spLine.memoryLocation = new BinaryNumber(nextMemoryLocation);
                } else {
                    // Otherwise, this instruction has a memory location provided.
                    // Use it, and remember it
                    nextMemoryLocation = spLine.memoryLocation.toIntBase10();
                }
                // No matter what, increment the next memory location pointer
                nextMemoryLocation++;


                String assemble = spLine.assemble();
                if (repeatSourceProgramLine) {
                    assemble += "\t" + line;
                }
                assemble = assemble.replace("\n", "");
                assemble += "\n";
                System.out.print(assemble);

                // grab next line from file
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Encountered an error when reading the file: " + e);
        }
    }


    SourceProgramLine buildSourceProgramLine(String line) {
        ArrayList<String> tokens = tokenizeLineOnWhitespace(line);

        // Extract the memory location (if provided)
        // NOTE: The first token may be the empty string
        BinaryNumber memLocBinary = null;
        if (Utility.isNumeric(tokens.getFirst())) {
            memLocBinary = new BinaryNumber(Integer.parseInt(tokens.getFirst()));
        }

        // Extract the OpCode
        String operationCodeName = getOperationCodeName(tokens);
        String fields = (operationCodeName.equalsIgnoreCase("hlt")) ? "" : getFields(tokens);
        // Build the instruction structure
        Instruction instruction = new Instruction(
                OpCodeFieldFactory.opCodeStrToField(operationCodeName),
                processFields(operationCodeName, fields)
        );

        // Everything else can be ignored
        String comments = (tokens.size() >= 3) ?
                processComments(tokens.subList(3, tokens.size())) :
                "";
        return new SourceProgramLine(memLocBinary, instruction, comments);

    }

    ArrayList<Field> processFields(String opCode, String fields) {
        FieldProcessor processor = getFieldProcessor(opCode);
        return processor.process(fields);
    }

    FieldProcessor getFieldProcessor(String opCode) {
        opCode = opCode.toLowerCase().replace(":", "");
        return switch (opCode) {
            /* Undocumented Instructions */
            case "data" -> {
                yield new DataFieldProcessor();
            }
            case "loc", "end" -> {
                yield new LocFieldProcessor();
            }

            /* Miscellaneous Instructions. Pg 11*/
            case "hlt" -> {
                yield new HltFieldProcessor();
            }
            case "trap" -> {
                yield new TrapFieldProcessor();
            }
            /* Load/Store Instructions. Pg 14*/
            case "ldr", // LoaD Register from memory
                    "str", // STore Register to memory
                    "lda" // Load Register with Address
                    -> {
                yield new RXAIFieldProcessor(true, true, true);
            }
            case "ldx", // Load inDeX register from memory
                    "stx" // STore indeX register to memory
                    -> {
                yield new RXAIFieldProcessor(false, true, true);
            }
            /* Transfer Instructions pg 15*/
            case "setcce" // Condition Code
                    -> {
                yield new RXAIFieldProcessor(true, false, false);
            }
            case "jz", //  06oct: Jump if Zero
                    "jma", // 11oct: unconditional JuMp to Address
                    "jsr" // 12oct: Jump and Save Return
                    -> {
                yield new RXAIFieldProcessor(false, true, true);
            }
            case "jne", // 07oct: Jump if Not Equal
                    "jcc", // 10oct: Jump if Condition Code
                    "sob", // 14oct: Subtract One and Branch
                    "jge" // 15oct: Jump Greater than or Equal to
                    -> {
                yield new RXAIFieldProcessor(true, true, true);
            }
            case "rfs" // 14oct: Return From Subroutine
                    -> {
                // TODO: address is actually optional here
                yield new RXAIFieldProcessor(false, false, true);
            }
            /* Arithmetic and Logical Instructions pg 16 */
            case "amr", // 16oct:  Add Memory to Register
                    "smr" // Subtract Memory from Register
                    -> {
                yield new RXAIFieldProcessor(true, true, true);
            }
            case "air", // Add Immediate to Register
                    "sir" // Subtract Immediate from Registers
                    -> {
                yield new RXAIFieldProcessor(true, false, true);
            }
            /* Register to Register operations  pg 18*/
            case "mlt", // 22oct: Multiply register by register
                    "dvd", // 23oct: divide register by register
                    "trr", // test the equality of register and register
                    "and", // Logical and of register and register
                    "orr" // logical or of register and register
                    -> {
                yield new RegisterToRegisterFieldProcessor();
            }
            case "not" // logical not of register
                    -> {
                yield new RegisterToRegisterFieldProcessor_Not();
            }
            /* Shift/Rotate Operations gp 18 */
            case "src", // Shift Register by Count
                    "rrc" // Rotate Register by Count
                    -> {
                yield new BitwiseOperationFieldProcessor();
            }
            /* I/O Operations */
            case "in", // Input Character to Register from Device
                    "out", // Output Character to Device from Register
                    "chk" // Check Device Status to Register
                    -> {
                yield new IOFieldProcessor();
            }
            /* Floating Point Instructions/Vector Operations pg 21 */
            case "fadd", // Floating add memory to register
                    "fsub", // Floating subtract memory from register
                    "vadd", // vector add
                    "vsub", // vector subtract
                    "cnvrt", // convert to fixed/floating point
                    "ldfr", // Load floating register from memory
                    "stfr" // store floating register to memory
                    -> {
                yield new RXAIFieldProcessor(true, true, true);
            }
            default -> {
                throw new IllegalStateException("Unexpected op code: " + opCode);
            }
        };
    }

    void processLDR(String fields) {
        /* Fields should have 3 or 4 values
         * The 'I' field below is optional
         * Example:
         *   LDR
         *   r, x, address[,I]
         *
         * The bit size of each field is shown below
         * R	IX	I	Address
         * 11 	00 	0 	01111
         */

        String[] fieldTokens = fields.split(",");
        if (fieldTokens.length == 3) {
            String r = fieldTokens[0];
            String ix = fieldTokens[1];
            String i = "0";
            String address = fieldTokens[2];
        } else if (fieldTokens.length == 4) {
            String r = fieldTokens[0];
            String ix = fieldTokens[1];
            String i = fieldTokens[2];
            String address = fieldTokens[3];
        } else {
            throw new IllegalArgumentException("Instruction processing did NOT have the expected fields: " + fields);
        }

    }

    String processComments(List<String> comments) {
        StringBuilder result = new StringBuilder();
        for (String str : comments) {
            if (str.isEmpty()) {
                continue;
            }
            result.append(str);
            result.append(' '); // Add a space between tokens to make it readable
        }
        return result.toString().trim();
    }

    ArrayList<String> tokenizeLineOnWhitespace(String line) {
        String regexMatch = "[ \t\n]+"; // match on 1 or more whitespace characters


        ArrayList<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == ' ' || c == '\t' || c == '\n') {
                // If the character is a token, then flush the buffer into the result
                result.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            // Else the character is NOT a whitespace character, add it to the buffer
            sb.append(c);
        }
        result.add(sb.toString());
        return result;

    }

    String getOperationCodeName(ArrayList<String> tokens) {
        if (tokens.getFirst().isEmpty()) {
            // If the first token is empty, then the op code is
            // expected to be the first non-empty token fond
            return getNthNonEmpty(tokens, 1);
        }

        // else the first token is _not_ empty.
        // Then get the 2nd non-empty token
        return getNthNonEmpty(tokens, 2);
    }

    String getFields(ArrayList<String> tokens) {
        if (tokens.getFirst().isEmpty()) {
            // If the first token is empty,
            // then the fields are expected to be the 2nd non-empty token
            return getNthNonEmpty(tokens, 2);
        }
        // Else the first toke is /not/ empty.
        // Return the 3rd non-empty token as the fields
        return getNthNonEmpty(tokens, 3);
    }

    String getNthNonEmpty(ArrayList<String> tokens, int n) {
        int nonEmptySeen = 0;
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }
            if (nonEmptySeen + 1 == n) {
                return token;
            }
            nonEmptySeen++;
        }
        throw new IllegalArgumentException("Line does not have enough expected tokens");
    }

}
