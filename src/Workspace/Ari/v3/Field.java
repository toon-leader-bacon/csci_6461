package Workspace.Ari.v3;

public class Field extends Bits{
    public fieldType type;

    public enum fieldType{
        OpCode,R,IX,I,Addr,Blank,Other
    };

    public Field(int value_, int size_, fieldType ft_) {
        super(size_);
        this.setField((short) value_);
        this.type = ft_;
    }


    public void setField(short value_){
        for(int i=0; i < this.size; i++){
            this.bits[i] = ((value_ << i) == 1);
        }
        return;
    }

    public String getValue() {
        return this.value();
    }
    public short getSize(){
        return this.size;
    }
    public fieldType getType(fieldType ft){
        return this.type;
    }



}
