package org.nocab.nocabmachine.akm.v2;

public class Instruction {
    Field opcode;
    Field[] fields;

    Instruction(Field opcode_, Field[] fields_){
        this.opcode = opcode_;
        this.fields = fields_;
    }
}
