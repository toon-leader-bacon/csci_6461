package org.nocab.nocabmachine.akm.v2;

import org.nocab.nocabmachine.akm.v2.ISA;

public class Assembler {
    ISA isa = new ISA();
    static short location; // location
    static short[] GPR = new short[4]; // general purpose registers
    static short[] IXR = new short[3]; // index registers

    private int strToInt(String item) {
        try {
            return Integer.parseInt(item);
        }
        catch(NumberFormatException e) {
            System.out.println(item + " not found");
        }
        return 0;
    }

    public String[] tokenize(String instruction) {
        return instruction.split("[ ,]+");
    }

    private Field getOpcode(String command) {
        return isa.ITable.get(command);
    }

    private Field[] getFields(Field opcode, String[] tokens) {
        Field[] fields = new Field[tokens.length - 1];
        switch (opcode.value) {

            //Load/Store Instruction Type
            case (1):
            case (2):
            case (3):
            case (4):
            case (5):
            // Transfer Instruction type
            case (44):
            case (6):
            case (7):
            case (10):
            case (11):
            case (12):
            case (13):
            case (14):
            case (15):
                fields[0] = new Field(strToInt(tokens[1]), 2); //R
                fields[1] = new Field(strToInt(tokens[2]), 2); //IX
                fields[3] = new Field(strToInt(tokens[3]), 5); //addr
                if (tokens[4] != null) //if there is a token for [,I]
                    fields[2] = new Field(1, 1); //set I
                else
                    fields[2] = new Field(0, 1); //set no I
                break;

            // Arithmetic/Logical Instructions
            case (16):
            case (17):
            case (20):
            case (21):
                fields[0];


    }

        return fields;
    }

    private int computeEffectiveAddress(String[] tokens) {

    }


    public void assemble(String[] instructions) {
        int PC = 0;
        for (String I : instructions) {
            //split instruction into tokens
            String[] tokens = this.tokenize(I);
            //["LDR", "1", "2", "1","10"]

            //get opcode from Instruction Table
            Field opcode = this.getOpcode(tokens[0]);

            if (opcode.value == 0) {
                PC = strToInt(tokens[1]);
            }
            else {
                //based on opcode, determine values in each field
                Field[] fields = getFields(opcode,tokens);
                Instruction instr = new Instruction(opcode,fields);
                // print the instruction to console ** (CHANGE FOR FINAL PRODUCTION)
                System.out.printf("%o\t%o\t%s",PC,instr,I);
            }


            //set next location of PC
            PC = (tokens[0] == "LOC") ? PC : PC + 1 ;

        }
    }




    






}
