package org.nocab.nocabmachine.akm.v2;


public class Instruction {
    static int OPCODE_SHIFT = 10;

    Field opcode;
    Field[] fields;

    public Instruction(Field opcode_, Field[] fields_){
        this.opcode = opcode_;  //6b
        this.fields = fields_;  //10b
    }

    public short literalRepresentation() {
        // return the literal data representation of the Instruction based on the fields
        short value =  0;
        value = (short) (value | (this.opcode.getValue() << OPCODE_SHIFT));
        Field[] localFields = this.fields;
        int numberOfFields = localFields.length;
        for (int field = 0; field < numberOfFields; ++field) {
            short fieldValue = localFields[field].getValue();   //value of field (to be inserted)
            int SHIFT_AMT = 0;  //amount to shift field value to align on short

            for(int remainingfields = field+1; remainingfields < numberOfFields; remainingfields++) {
                SHIFT_AMT += localFields[remainingfields].getSize();
            }

            value = (short) (value | (fieldValue << SHIFT_AMT));
        }

//        System.out.printf("Oct: %o\n",value);
//        System.out.printf("Bin: %b\n",value);
//        System.out.println();
        return value;
    }

    public String toBinaryString(){
        StringBuilder str = new StringBuilder();
        str.append(Integer.toBinaryString(this.opcode.getValue()));
        for(Field field:this.fields){
            str.append(Integer.toBinaryString(field.getValue()));
        }
        return str.toString();
    }

    public String toOctalString() {
        return Integer.toOctalString(literalRepresentation());
    }
}
