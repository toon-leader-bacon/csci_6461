package Workspace.Ari.v2;
public class Instruction_old extends Word {
    short WORD_SIZE = 16;
    short OPCODE_SIZE = 6;
    short shiftAmt  = (short)(WORD_SIZE - OPCODE_SIZE);


    public Instruction_old(short opcode,short[] data) {
        super(0);
//        this.data &= (short) (opcode << shiftAmt); //mask and set opcode bits on Instruction data
        short[] fields = getFields(opcode);        //based on opcode, determine the next fields
        populateFields(fields,data);

        this.print();

    }

    public void insert(int fieldIndex, short value){
        this.data &= value << fieldIndex;
    }


    public void populateFields(short[] fields, short[] values) {

        for(short i = 0; i < fields.length; i++) {

        }
        return;
    }

    public short[] getFields(short opcode) {
        short[] LDSTR = new short[] {1,2,3,4,5,44,6,7,10,11,12,13,14,15};
        short[] ARITH = new short[] {16,17,20,21,22,23,24,25,26,27};
        short[] SHIFT = new short[] {30,31};
        short[] IO = new short[]{32,33,34};
        short[] FPMATH = new short[] {35,36,37,40,41,42,43};

        short[] fieldSizes;
        if(contained(opcode,LDSTR))
            fieldSizes = new short[] {2,2,1,5};   //load/stores, transfer,
        else if(contained(opcode,ARITH))
            fieldSizes = new short[] {6,2,2};     //arithmetic, logical
        else if(contained(opcode,SHIFT))
            fieldSizes = new short[] {4,2,1,1,2}; //shift, rotate
        else if(contained(opcode,IO))
            fieldSizes = new short[] {5,3,2};     //input/output
        else if(contained(opcode,FPMATH))
            fieldSizes = new short[] {5,2,1,2};   //vector math
        else
            fieldSizes = new short[] {};

        return fieldSizes;
    }

    public boolean contained(short num, short[] arr) {
        for(short item:arr){
            if(num == item)
                return true;
        }
        return false;

    }

    public void print() {
        System.out.printf("%o",this.data);
    }
}
