package org.nocab.nocabmachine.nocab;

public class Field {

    public Field(short value_, short size_) {
        this.value = value_;
        this.size = size_;
    }

    public short value;
    public short size;

    String toBinString() {
        StringBuilder sb = new StringBuilder(this.size);
        String shortBin = Integer.toBinaryString(this.value);
        while(shortBin.length() < this.size) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

}
