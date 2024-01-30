package org.nocab.nocabmachine.nocab;


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

    void blab(String fileToRead) {
        // Parse the source program line by line
        // Split each line on tab characters
        // First token = memory location (or empty)
        // Second token = opCode+Fields
        // extra tokens are all comments/ un-needed


        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileToRead));
            String line = reader.readLine();

            // Loop over each line in the file
            while (line != null) {
                System.out.println("Processing line: " + line);
                ArrayList<String> tokens = tokenizeLine(line);
                System.out.println("Finished Processing line: " + line);

                // grab next line from file
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Encountered an error when reading the file: " + e);
        }
    }

    ArrayList<String> tokenizeLine(String line) {
        ArrayList<String> tokens = tokenizeLineOnWhitespace(line);


        // Extract the memory location (if provided)
        // NOTE: The first token may be the empty string
        String memoryLocation = Utility.isNumeric(tokens.get(0)) ? (tokens.get(0)) : ("0");

        // Extract the OpCode
        String operationCode = tokens.get(1);

        // Extract the fields:
        // String fields = processFields(operationCode, tokens.get(2));
        String fields = tokens.get(2);

        // Everything else can be ignored
        String comments = processComments(tokens.subList(3, tokens.size()));
        return new ArrayList<>() {{
            add(memoryLocation);
            add(operationCode);
            add(fields);
            add(comments);
        }};
    }

    ArrayList<Field> processFields(String opCode, String fields) {
        FieldProcessor processor = getFieldProcessor(opCode);
        return processor.process(fields);
    }

    FieldProcessor getFieldProcessor(String opCode) {
        return switch (opCode.toLowerCase()) {
            case "data" -> {
                yield new DataFieldProcessor();
            }
            case "ldr", // LoaD Register from memory
                    "str", // STore Register to memory
                    "lda", // Load Register with Address
                    "ldx", // Load inDeX register from memory
                    "stx" // STore indeX register to memory
                    -> {
                yield new LdrFieldProcessor();
            }
            case "hlt" -> {
                yield new HltFieldProcessor();
            }
            case "trap" -> {
                yield new TrapFieldProcessor();
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
        String regexMatch = "[ \\t\\n]+"; // match on 1 or more whitespace characters
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


}
