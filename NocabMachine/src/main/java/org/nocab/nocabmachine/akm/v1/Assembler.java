package org.nocab.nocabmachine.akm.v1;

public class Assembler {
    ISA isa = new ISA();
    static int location; // location

    static short[] GPR = new short[4];
    static short[] IXR = new short[3];
    
//    static enum fields { //6 to ... to 16
//        HALT (new int[] {4,6}), // 4 --, 6 all 0s
//        TRAP (new int[] {6,4}), // 6 --, 4 trapcode
//        LDST (new int[] {2,2,1,5});    // 2 IX, 2 R, 1 I, 5 Addr
//
//
//
//        static int[] ix;
//        private fields(int[] indices) {
////            this.ix = indices;
//
//        }
//
//    }

    private int toInt(String item) {
        try {
            int value = Integer.valueOf(item);
            return value;
        }
        catch(NumberFormatException e) {
            System.out.println(item + " not found");
        }
        return 0;
    }

    public String[] tokenize(String instruction) {
        return instruction.split("[ ,]+");
    }

    private int getOpcode(String command) {
        return isa.ITable.get(command);
    }


    public void assemble(String[] instructions) {
        int PC = 0;

        for (String I : instructions) {
            //split instruction into tokens
            String[] tokens = this.tokenize(I);

            //get opcode from Instruction Table
            int opcode = this.getOpcode(tokens[0]); 

            //based on opcode, determine values in each field
            int[] fields;
            switch(opcode) {
                case 0:
                    fields = new int[] {6,10};
                case 45:
                    fields = new int[] {6,12};
                
            }

            // print the instruction to console ** (CHANGE FOR FINAL PRODUCTION)
            System.out.printf("%o",opcode);


            //set next location of PC
            PC = (tokens[0] == "LOC") ? this.toInt(tokens[1]) : PC + 1 ; 

        }
    }




    






}
