package org.nocab.nocabmachine.nocab.DataStructures;

import org.nocab.nocabmachine.nocab.BinaryNumber;
import org.nocab.nocabmachine.nocab.Field;

import java.util.ArrayList;

public class Instruction {

    public Instruction(Field opCode, ArrayList<Field> fields_) {
        this.operationCode = opCode;
        this.fields = fields_;
    }

    public Field operationCode;
    public ArrayList<Field> fields;

    public String assemble() {
        BinaryNumber instructionInBinary = new BinaryNumber(this.mergeFieldsToBinary());
        return instructionInBinary.toString_Octal();
    }

    protected String mergeFieldsToBinary() {
        StringBuilder sb = new StringBuilder();
        sb.append(operationCode.toBinString());
        for (Field f : this.fields) {
            sb.append(f.toBinString());
        }
        return sb.toString();
    }

}
