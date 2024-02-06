package Workspace.Ari.v1;

public class Reg {
    public short data;  //instructions and data are 16b

    public Reg(int initData) {
        //init Register from int
        this.data = (short) initData;
    }
    public Reg(short initData) {
        //init Register from short
        this.data = initData;
    }

    public void set(int newData) {
        //set data in register
        this.data = (short) newData;
    }
    public short get() {
        //return data in register
        return this.data;
    }

    public String toString() {
        return String.format("%o",this.data); //return data in octal
    }
}

