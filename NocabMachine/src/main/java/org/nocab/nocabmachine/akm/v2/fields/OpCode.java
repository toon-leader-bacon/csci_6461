package org.nocab.nocabmachine.akm.v2.fields;
import org.nocab.nocabmachine.akm.v2.Field;

public class OpCode extends Field{
    public OpCode(int code_){
        super(code_,6, fieldType.OpCode);
    }
}

