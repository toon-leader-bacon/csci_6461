package Workspace.Ari.v2;

public class Field {
    public short value;
    public short size;
    public fieldType type;

    public enum fieldType{
        OpCode,R,IX,I,Addr,Blank,Other
    };

    public Field(int value_, int size_, fieldType ft_) {
        this.value = (short) value_;
        this.size = (short) size_;
        this.type = ft_;
    }


    public short getValue() {
        return this.value;
    }
    public short getSize(){
        return this.size;
    }
    public fieldType getType(fieldType ft){
        return this.type;
    }



}
