package nocab.DataStructures;

import nocab.BinaryNumber;

import java.util.ArrayList;

public class Instruction {

    public Instruction(OpCode opCode, ArrayList<Field> fields_) {
        this.operationCode = opCode;
        this.fields = fields_;
    }

    public OpCode operationCode;
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
