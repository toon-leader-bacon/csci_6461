package org.nocab.nocabmachine.akm.v2;
public class Instruction {
    short data = (short) 0; //initialize instruction with all 0s
    int WORD_SIZE = 16;
    int OPCODE_SIZE = 6;
    int shiftAmt  = WORD_SIZE - OPCODE_SIZE;



    public Instruction(String type,int[] data) {
        ISA isa = new ISA();

        short opcode = isa.ITable.get(type);    //based on Instruction, get opcode value
        this.data &= (short) (opcode << shiftAmt); //mask and set opcode bits on Instruction data
        int[] fields = getFields(opcode);        //based on opcode, determine the next fields
        populateFields(fields,data);

        


        this.print();

    }

    public void insert(int fieldIndex, short value){
        this.data &= value << fieldIndex;
    }


    public void populateFields(int[] fields, int[] values) {

        for(int i = 0; i < fields.length; i++) {

        }
        return;
    }

    public int[] getFields(short opcode) {
        int[] LDSTR = new int[] {1,2,3,4,5,44,6,7,10,11,12,13,14,15};
        int[] ARITH = new int[] {16,17,20,21,22,23,24,25,26,27};
        int[] SHIFT = new int[] {30,31};
        int[] IO = new int[]{32,33,34};
        int[] FPMATH = new int[] {35,36,37,40,41,42,43};

        int[] fieldSizes;
        if(contained(opcode,LDSTR))
            fieldSizes = new int[] {2,2,1,5};   //load/stores, transfer, 
        else if(contained(opcode,ARITH))
            fieldSizes = new int[] {6,2,2};     //arithmetic, logical
        else if(contained(opcode,SHIFT))
            fieldSizes = new int[] {4,2,1,1,2}; //shift, rotate
        else if(contained(opcode,IO))
            fieldSizes = new int[] {5,3,2};     //input/output
        else if(contained(opcode,FPMATH))
            fieldSizes = new int[] {5,2,1,2};   //vector math
        else
            fieldSizes = new int[] {};

        return fieldSizes;
    }

    public boolean contained(short n, int[] arr) {
        int num = (int) n;
        for(int item:arr){
            if(num == item)
                return true;
        }
        return false;

    }

    public void print() {
        System.out.printf("%o",this.data);
    }
}
