package org.nocab.nocabmachine.akm.v2.fields;

import org.nocab.nocabmachine.akm.v2.Field;

public class Addr extends Field {
    public Addr(int value_) {
        super(value_,5);
        this.type = fieldType.Addr;
    }
}

