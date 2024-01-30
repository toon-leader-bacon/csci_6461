package org.nocab.nocabmachine.akm.v2;

public class Field {
    public short value;
    public short size;
    public String name;

    public Field(int value_, int size_) {
        this.value = (short) value_;
        this.size = (short) size_;
    }
}
