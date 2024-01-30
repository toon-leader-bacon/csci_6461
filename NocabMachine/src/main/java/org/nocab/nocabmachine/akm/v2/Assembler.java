package org.nocab.nocabmachine.akm.v2;

import org.nocab.nocabmachine.akm.v2.fields.*;

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

            // Register, IndexReg, Address, [,I] factory //
                //Load/Store Instruction Type
            case(1):    //LDR r,x,addr[,I]
            case(2):    //STR r,x,addr[,I]
            case(3):    //LDA r,x,addr[,I]
                // Transfer Instruction type
            case(7):    //JNE r,x,addr[,I]
            case(14):   //SOB r,x,addr[,I]
            case(15):   //JGE r,x,addr[,I]
                // Arithmetic/Logical Instructions
            case(16):   //AMR r,x,addr[,I]
            case(17):   //SMR r,x,addr[,I]
                // Vector Operations
            case(35):   //FADD fr,x,addr[,I]
            case(36):   //FSUB fr,x,addr[,I]
            case(37):   //VADD fr,x,addr[,I]
            case(40):   //VSUB fr,x,addr[,I]
            case(41):   //CNVRT r,x,addr[,I]
            case(42):   //LDFR fr,x,addr[,I]
            case(43):   //STFR fr,x,addr[,I]
                fields[0] = new R(strToInt(tokens[1])); //R
                fields[1] = new IX(strToInt(tokens[2])); //IX
                fields[3] = new Addr(strToInt(tokens[3])); //addr
                if (tokens[4] != null) //if there is a token for [,I]
                    fields[2] = new I(1); //set I
                else
                    fields[2] = new I(0); //set no I
                break;

            // IndexReg, Address, [,I] factory //
                //Load/Store Instruction Type
            case(4):    //LDX x,addr[,I]
            case(5):    //STX x,addr[,I]
                // Transfer Instruction type
            case(6):    //JZ x,addr[,I]
            case(11):   //JMA x,addr[,I]
            case(12):   //JSR x,addr[,I]

                fields[1] = new IX(strToInt(tokens[2])); //IX
                fields[3] = new Addr(strToInt(tokens[3])); //addr
                if (tokens[4] != null) //if there is a token for [,I]
                    fields[2] = new I(1); //set I
                else
                    fields[2] = new I(0); //set no I
                break;

            // Transfer Instruction type
            case(44):   //SETCCE r
            case(10):   //JCC cc,x,addr[,I]
            case(13):   //RFS Immed

                fields[0] = new Field(strToInt(tokens[1]), 2); //R
                fields[1] = new Field(strToInt(tokens[2]), 2); //IX
                fields[3] = new Field(strToInt(tokens[3]), 5); //addr
                if (tokens[4] != null) //if there is a token for [,I]
                    fields[2] = new Field(1, 1); //set I
                else
                    fields[2] = new Field(0, 1); //set no I
                break;

                //Immediate Instructions
            case(20):   //AIR r,Immed
            case(21):   //SIR r,Immed
                fields[0] = new Field(strToInt(tokens[1]),2);   //R
                fields[1] = new Field(strToInt(tokens[2]), 2);  //immed
                break;
                // Reg to reg instructions
            case(22):   //MLT rx,ry
            case(23):   //DVD rx,ry
            case(24):   //TRR rx,ry
            case(25):   //AND rx,ry
            case(26):   //ORR rx,ry
                fields[0] = new Field(strToInt(tokens[1]),2);   //Rx
                fields[1] = new Field(strToInt(tokens[2]),2);   //Ry
                break;
            case(27):   //NOT rx
                fields[0] = new R(strToInt(tokens[1]));   //Rx
                break;

                // Logical shifts
            case(30):   //SRC r,count,L/R,A/L
            case(31):   //RRC r,count,L/R,A/L
                fields[0] = new R(strToInt(tokens[1]));//R
                fields[3] = new Addr(strToInt(tokens[2]));//count
                fields[2] = new Field(strToInt(tokens[3]),1);//L/R
                fields[1] = new Field(strToInt(tokens[4]),1);//A/L
                break;

            //I/O Operations
            case(32):   //IN r,devid
            case(33):   //OUT r,devid
            case(34):   //CHK r,devid
                fields[0] = new R(strToInt(tokens[1]));
                fields[1] = new Addr(strToInt(tokens[2]));

        }
        return fields;
    }

    private int computeEffectiveAddress(String[] tokens) {
        return 0; //not yet implemented
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
