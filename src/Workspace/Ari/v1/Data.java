package Workspace.Ari.v1;


public class Data {
    public enum DataType {
        Instruction,
        Integer,
        FPVector,
    }
    public Word data = new Word();

    public class Instruction {
        private DataType type;
        private short[] fields;

        public Instruction(short[] fields) {
            this.type = DataType.Instruction;
            this.fields = fields.clone();


        }

        public class LD {
            // load instruction
            
        }

        public class ST {
            // store instruction

        }


        // private void fillFields(short field, short value) {
        //     short base = 00;
        //     short opcode = (short) (base & value);

        // }
    }
}
