package org.nocab.nocabmachine.akm.v2;
import java.util.*;

public class ISA {
    Dictionary<String, Field> ITable = new Hashtable<>();

    public ISA()
    {
        ITable.put("LOC",new Field(0,6));   //Confirm wheter or not this is a Nop
        ITable.put("LDR",new Field(1,6));      //1
        ITable.put("SDR",new Field(2,6));      //2
        ITable.put("LDA",new Field(3,6));      //3
        ITable.put("LDX",new Field(4,6));      //4
        ITable.put("STX",new Field(5,6));      //5
        ITable.put("JZ" ,new Field(6,6));       //6
        ITable.put("JNE",new Field(7,6));      //7
        // ITable.put("",new Field(8,6));      //8
        // ITable.put("",new Field(9,6));      //9
        ITable.put("JCC",new Field(10,6));      //10
        ITable.put("JMA",new Field(11,6));      //11
        ITable.put("JSR",new Field(12,6));      //12
        ITable.put("RFS",new Field(13,6));      //13
        ITable.put("SOB",new Field(14,6));      //14
        ITable.put("JGE",new Field(15,6));      //15
        ITable.put("AMR",new Field(16,6));      //16
        ITable.put("SMR",new Field(17,6));      //17
        // ITable.put("",new Field(18,6));      //18
        // ITable.put("",new Field(19,6));      //19
        ITable.put("AIR",new Field(20,6));      //20
        ITable.put("SIR",new Field(21,6));      //21
        ITable.put("MLT",new Field(22,6));      //22
        ITable.put("DVD",new Field(23,6));      //23
        ITable.put("TRR",new Field(24,6));      //24
        ITable.put("AND",new Field(25,6));      //25
        ITable.put("ORR",new Field(26,6));      //26
        ITable.put("NOT",new Field(27,6));      //27
        // ITable.put("",new Field(28,6));      //28
        // ITable.put("",new Field(29,6));      //29
        ITable.put("SRC", new Field(30,6));      //30
        ITable.put("RRC", new Field(31,6));      //31
        ITable.put("IN",  new Field(32,6));       //32
        ITable.put("OUT", new Field(33,6));      //33
        ITable.put("CHK", new Field(34,6));      //34
        ITable.put("FADD",new Field(35,6));      //35
        ITable.put("FSUB",new Field(36,6));      //36
        ITable.put("VADD",new Field(37,6));      //37
        // ITable.put("",new Field(38,6));      //38
        // ITable.put("",new Field(39,6));      //39
        ITable.put("VSUB", new Field(40,6));      //40
        ITable.put("CNVRT",new Field(41,6));      //41
        ITable.put("LDFR", new Field(42,6));      //42
        ITable.put("STFR", new Field(43,6));      //43
        ITable.put("SETCCE",new Field(44,6));     //44
        ITable.put("TRAP code",new Field(45,6));  //45
        // ITable.put("",new Field(46,6));      //46
        // ITable.put("",new Field(47,6));      //47
        // ITable.put("",new Field(48,6));      //48
        // ITable.put("",new Field(49,6));      //49
        // ITable.put("",new Field(50,6));      //50
        // ITable.put("",new Field(51,6));      //51
        // ITable.put("",new Field(52,6));      //52
        // ITable.put("",new Field(53,6));      //53
        // ITable.put("",new Field(54,6));      //54
        // ITable.put("",new Field(55,6));      //55
        // ITable.put("",new Field(56,6));      //56
        // ITable.put("",new Field(57,6));      //57
        // ITable.put("",new Field(58,6));      //58
        // ITable.put("",new Field(59,6));      //59
        // ITable.put("",new Field(60,6));      //60
        // ITable.put("",new Field(61,6));      //61
        // ITable.put("",new Field(62,6));      //62
        // ITable.put("",new Field(63,6));      //63
    }
}
