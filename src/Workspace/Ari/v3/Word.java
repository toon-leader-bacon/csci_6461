package Workspace.Ari.v3;

public class Word extends Bits{
    public Word() {
        super(16); //each word is 16 bits
    }


    public void setWord(short n) {
        for(int i=0; i < this.size; i++){
            this.bits[i] = ((n << i) == 1);
        }
        return;
    }


    public short getWord() {
        short num = 0;
        for(int i = 0; i < this.size; i++){
            if(this.bits[i])
                num |= 1 << i;
        }
        return num;
    }

}
