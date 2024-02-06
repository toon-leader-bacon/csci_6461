package Workspace.Ari.v2;

import Workspace.Ari.v2.fields.*;

public class Assembler {
    ISA isa = new ISA();
    static final int end = 1024;
//    static short[] GPR = new short[4]; // general purpose registers
//    static short[] IXR = new short[3]; // index registers

    private int strToInt(String item) {
        // convert an integer string to its integer form
        try {
            return Integer.parseInt(item);
        } catch (NumberFormatException e) {
            System.out.println(item + " not an Integer");
        }
        return 0;
    }

    public String[] tokenize(String instruction) {
        // split each Instruction line by whitespace and commas
        // return as a list of strings, which will have the following format:
        //     ["operation","field 1,"field 2",..."field n"]
        return instruction.split("[ ,]+");
    }

    private Field getOpcode(String operation) {
        // query Instruction Table for operations, and return the corresponding OpCode Field
        return isa.ITable.get(operation);
    }

    private Field setIField(String[] tokens, int checkIndex) {
        // based on how many tokens are passed, determine if an 'I' token has been included
        // if so, convert 'I' token to its integer form, and return 'I' field
        return (tokens.length > checkIndex) ? new I(strToInt(tokens[checkIndex])) : new I(0);
    }

    private Field[] getFields(Field opcode, String[] tokens) {
        Field[] fields = new Field[4];

        switch (opcode.value) {
            // Special Codes //
            case (0):    //HLT (stops the machine)
                // 000000 ---- 000000
                fields = new Field[2];
                fields[0] = new Blank(4);
                fields[1] = new Field(0, 0, Field.fieldType.Other); //00
                break;
            case (45):   //TRAP code
                fields = new Field[2];
                fields[0] = new Blank(6);
                fields[1] = new Field(strToInt(tokens[1]), 4, Field.fieldType.Other); //code
                break;

            // Register, IndexReg, Address, [,I] factory //
            //Load/Store Instruction Type
            case (1):    //LDR r,x,addr[,I]
            case (2):    //STR r,x,addr[,I]
            case (3):    //LDA r,x,addr[,I]
                // Transfer Instruction type
            case (7):    //JNE r,x,addr[,I]
            case (14):   //SOB r,x,addr[,I]
            case (15):   //JGE r,x,addr[,I]
                // Arithmetic/Logical Instructions
            case (16):   //AMR r,x,addr[,I]
            case (17):   //SMR r,x,addr[,I]
                // Vector Operations
            case (35):   //FADD fr,x,addr[,I]
            case (36):   //FSUB fr,x,addr[,I]
            case (37):   //VADD fr,x,addr[,I]
            case (40):   //VSUB fr,x,addr[,I]
            case (41):   //CNVRT r,x,addr[,I]
            case (42):   //LDFR fr,x,addr[,I]
            case (43):   //STFR fr,x,addr[,I]
                // r,x,addr[,I] (I if 5 tokens)
                // GR,IX,I,ADDRS
                fields[0] = new R(strToInt(tokens[1])); //R
                fields[1] = new IX(strToInt(tokens[2])); //IX
                //if there is a token for [,I]
                fields[2] = setIField(tokens, 4);
                fields[3] = new Addr(strToInt(tokens[3])); //addr
                break;


            // IndexReg, Address, [,I] factory //
            //Load/Store Instruction Type
            case (4):    //LDX x,addr[,I]
            case (5):    //STX x,addr[,I]
                // Transfer Instruction type
            case (6):    //JZ x,addr[,I]
            case (11):   //JMA x,addr[,I]
            case (12):   //JSR x,addr[,I]
                // x,addr[,I] (I if 3 tokens)
                // --,IX,I,ADDRS
                fields[0] = new Blank(2); // --
                fields[1] = new IX(strToInt(tokens[1])); //IX
                fields[2] = setIField(tokens, 3); //I
                fields[3] = new Addr(strToInt(tokens[2])); //addr
                break;


            // Misc Formats //
            // Transfer Instruction type
            // (Have same format as load/store instructions)
            case (44):   //SETCCE r
                // r
                // GR,--,-,-----
                fields[0] = new R(strToInt(tokens[1])); //R
                fields[1] = new Blank(2);//IX
                fields[2] = new Blank(1);//I
                fields[3] = new Blank(5);//addr
                break;

            case (10):   //JCC cc,x,addr[,I]
                // cc,x,addr[,I] (I if 5 tokens)
                // CC,IX,I,ADDRS
                fields[0] = new Field(strToInt(tokens[1]), 2, Field.fieldType.Other); //CC
                fields[1] = new IX(strToInt(tokens[2])); //IX
                fields[2] = setIField(tokens, 4); //I
                fields[3] = new Addr(strToInt(tokens[3]));
                break;

            // Transfer Instruction type
            case (13):   //RFS Immed
                // Immed
                // --,--,-,ADDRS
                fields[0] = new Blank(2);//R
                fields[1] = new Blank(2);//IX
                fields[2] = new Blank(1);//I
                fields[3] = new Addr(strToInt(tokens[1]));   //Immed
                break;

            //Immediate Instructions
            case (20):   //AIR r,Immed
            case (21):   //SIR r,Immed
                // r,Immed
                // GR,--,-,ADDRS
                fields[0] = new R(strToInt(tokens[1]));//R
                fields[1] = new Blank(2);//IX
                fields[2] = new Blank(1);//I
                fields[3] = new Addr(strToInt(tokens[2]));   //Immed
                break;

            // Reg to reg instructions
            case (22):   //MLT rx,ry
            case (23):   //DVD rx,ry
            case (24):   //TRR rx,ry
            case (25):   //AND rx,ry
            case (26):   //ORR rx,ry
            case (27):   //NOT rx
                // rx,ry (Ry if 3 tokens)
                // RX,RY,------
                // rx
                // GR,--,------
                fields = new Field[3];  //replace default fields
                fields[0] = new R(strToInt(tokens[1]));   //Rx
                fields[1] = (tokens.length > 2) ? new R(strToInt(tokens[2])) : new Blank(2);   //Ry or --
                fields[2] = new Blank(6);
                break;

            // Logical shifts
            case (30):   //SRC r,count,L/R,A/L
            case (31):   //RRC r,count,L/R,A/L
                // r,count,L/R,A/L
                // GR,L,A,--,CNTS
                fields = new Field[5];  //replace default fields
                fields[0] = new R(strToInt(tokens[1]));//R
                fields[1] = new Field(strToInt(tokens[4]), 1, Field.fieldType.Other);//A/L
                fields[2] = new Field(strToInt(tokens[3]), 1, Field.fieldType.Other);//L/R
                fields[3] = new Blank(2);
                fields[4] = new Field(strToInt(tokens[2]), 4, Field.fieldType.Addr);//count
                break;

            //I/O Operations
            case (32):   //IN r,devid
            case (33):   //OUT r,devid
            case (34):   //CHK r,devid
                // r,devid
                // GR,---,DEVID
                fields = new Field[3];  //replace default fields
                fields[0] = new R(strToInt(tokens[1])); //R
                fields[1] = new Blank(3);
                fields[2] = new Field(strToInt(tokens[2]), 5, Field.fieldType.Other); //DevID
                break;

            default:
                throw new IllegalArgumentException("Unexpected Instruction");
        }
        return fields;
    }

//    private int computeEffectiveAddress(String[] tokens) {
//        return 0; //not yet implemented
//    }


    public void printListing(short location, short code, String instruction) {
        System.out.printf("%o\t%o\t%s", location, code, instruction);
    }

    public void assemble(String[] instructions) {
        int PC = 0;
        int location = 0;
        String locationForListing;
        String dataForListing;

        for (String instructionString : instructions) { //instructions are already split by line
            //split instruction into tokens
            String[] tokens = this.tokenize(instructionString);     //eg.["LDR", "1", "2", "10","1"]

            // Put output strings into a String array
            String[] listingStrings = new String[3]; // location, data, AssemblyCode

            location = PC; //location of instruction and data is always at PC
            locationForListing = Integer.toOctalString(location);

            // if LOC command, adjust PC
            if (tokens[0] == "LOC") {
                PC = strToInt(tokens[1]) - 1; //set PC to LOC's passed value
                // will increment 1 at end of loop (no prefetch)
                dataForListing = Integer.toOctalString(0);
            }
            else if(tokens[0] == "data") {
                dataForListing = Integer.toOctalString(strToInt(tokens[1]));
            }
            else {
                //else construct instruction
                //get opcode from Instruction Table
                Field opcode = this.getOpcode(tokens[0]);
                if (opcode.value == 0) { //goto Halt
                    PC = end;
                    dataForListing =  Integer.toOctalString(0);
                } else {
                    //based on opcode, determine values in each field
                    Field[] fields = getFields(opcode, tokens);
                    // and construct an Instruction object
                    Instruction instruction = new Instruction(opcode, fields);
                    dataForListing = instruction.toOctalString();

                    // print the instruction to console ** (CHANGE FOR FINAL PRODUCTION)
                    // with the following format:
                    // Listing format
                    System.out.printf("%o\t%o\t%s", location, instruction.literalRepresentation(), instructionString); //replace w write to file
                }


                //1st index holds location of instruction
                listingStrings[0] = locationForListing;
                //2nd index holds data(/instruction) held at that location
                listingStrings[1] = dataForListing;
                //3rd index holds Assembly instruction string
                listingStrings[2] = instructionString;

                //set next location of PC
                PC = (tokens[0] == "LOC") ? PC : PC + 1;

            }
        }


    }
}
