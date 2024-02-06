package Workspace.Ari.v3;

public class Bits {
    public short size;
    public Boolean[] bits;

    public Bits(int size_) {
        this.size = (short) size_;
        this.bits = new Boolean[this.size]; //defaults to all 0s
    }

    private void setBit(short bit) {this.bits[bit] = true;}
    private void clearBit(short bit) {this.bits[bit] = false;}

    public void copyField(int index, Bits data){
        for(int i = 0; i < data.size; i++) {
            this.bits[i + index] = data.bits[i];
        }
    }

    public String value(){
        StringBuilder rstring = new StringBuilder();
        char c;
        for(boolean bit:this.bits){
            c = (bit == true) ? '1' : '0';
            rstring.append(c);
        }
        return rstring.toString();

    }


}
