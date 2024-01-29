package org.nocab.nocabmachine.akm.v1;

public class Instruction extends Data {
    public enum InstructionType {
        //Instruction type, and fields
        LOADSTORE,   //opcode, R, IX, I, Addr  
        ARITHMETIC,  //opcode, Rx, Ry, --
        LOGIC,       //opcode, R, A/L, L/R, --, count
        IO,          //opcode, R, --, DevID
        FPVECTOR,    //opcode, fr, I, IX, Addr
        HALT,        //opcode, misc
        TRAP;        //opcode, misc
    }

    public DataType type = DataType.Instruction;
    public short value;

    private short opcode;




    // // Miscellaneous Code
    // // do not fit another category
    // private String[] MiscFields = new String[] {
    //     "Opcode",
    //     "Table Index" //applies only to Trap
    // };
    // public String[] MiscCodes  = new String[] {
    //     "HLT",
    //     "TRAP"
    // };

    // // Load/Store
    // // Move Data from/to memory and a register
    // // Access to memory may be indirect (by setting I bit)
    // private String[] LSFields = new String[] {
    //     "Opcode",
    //     "IX",
    //     "R",
    //     "I",
    //     "Addr"
    // };
    // public String[] LSCodes = new String[] {
    //     "LDR",      //01
    //     "STR",      //02
    //     "LDA",      //03
    //     "LDX",      //04
    //     "STX"       //05
    // };
    // public String[] TransferCodes = new String[] {
    //     "SETCCE",   //44    
    //     "JZ",       //06
    //     "JNE",      //07
    //     "JCC",      //10
    //     "JMA",      //11
    //     "JSR",      //12
    //     "RFS",      //13
    //     "SOB",      //14
    //     "JGE"       //15
    // };

    // //Arithmetic & Logical Instructions
    // //
    // public String[] ArithmeticFields = new String[] {
    //     "Opcode",
    //     "Rx",
    //     "Ry"
    // };
    // public String[] ArithmeticCodes = new String[] {
    //     "AMR",      //16    //add mem to reg
    //     "SMR",      //17    //sub mem from reg
    //     "AIR",      //20    //add immediate to reg
    //     "SIR",      //21    //sub immediate from reg
    //     "MLT",      //22    //mult reg to reg
    //     "DVD",      //23    //divd reg by reg
    //     "TRR",      //24    //TEQ reg and reg
    //     "AND",      //25    //AND reg and reg
    //     "ORR",      //26    //ORR reg and reg
    //     "NOT",      //27    //NOT reg to reg
    //     "SRC",      //30    //shift reg by count
    //     "RRC"       //31    //rotate reg by count
    // };

    // // I/O Instructions
    // public String[] IOFields = new String[] {
    //     "Opcode",
    //     "R",
    //     "DevID"
    // };
    // public String[] IOCodes = new String[] {
    //     "IN",       //32    //input char to reg from device
    //     "OUT",      //33    //output char to device from reg
    //     "CHK"       //34    //check device status reg
    // };

    // //Float Point/Vector Operation Instructins
    // public String[] FPVectorFields = new String[] {
    //     "S",        //Sign
    //     "Exp",      //Exponent
    //     "Frac"      //Fraction (Significand without implicit bit)
    // };
    // public String[] FPVectorCodes = new String[] {
    //     "FADD",     //35    //Float Add mem to reg
    //     "FSUB",     //36    //Float Sub mem from reg
    //     "VADD",     //37    //Vector Add
    //     "VSUB",     //40    //Vector Sub
    //     "CNVRT",    //41    //Convert to Fixed/Float
    //     "LDFR",     //42    //load Floating reg from mem
    //     "STFR"      //43    //store Floating reg to mem
    // };



    public Instruction (short value) {
        this.value = value;
    }

    public int opcode() {
        return this.opcode;
    }



}
