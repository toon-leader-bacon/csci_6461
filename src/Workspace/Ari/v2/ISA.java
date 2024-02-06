package Workspace.Ari.v2;

import Workspace.Ari.v2.fields.OpCode;

import java.util.Dictionary;
import java.util.Hashtable;

public class ISA {
    Dictionary<String, Field> ITable = new Hashtable<>();

    public ISA() {
        ITable.put("LOC", new OpCode(0));   //Confirm wheter or not this is a Nop
        ITable.put("LDR", new OpCode(1));      //1
        ITable.put("SDR", new OpCode(2));      //2
        ITable.put("LDA", new OpCode(3));      //3
        ITable.put("LDX", new OpCode(4));      //4
        ITable.put("STX", new OpCode(5));      //5
        ITable.put("JZ", new OpCode(6));       //6
        ITable.put("JNE", new OpCode(7));      //7
        // ITable.put("",new OpCode(8));      //8
        // ITable.put("",new OpCode(9));      //9
        ITable.put("JCC", new OpCode(10));      //10
        ITable.put("JMA", new OpCode(11));      //11
        ITable.put("JSR", new OpCode(12));      //12
        ITable.put("RFS", new OpCode(13));      //13
        ITable.put("SOB", new OpCode(14));      //14
        ITable.put("JGE", new OpCode(15));      //15
        ITable.put("AMR", new OpCode(16));      //16
        ITable.put("SMR", new OpCode(17));      //17
        // ITable.put("",new OpCode(18));      //18
        // ITable.put("",new OpCode(19));      //19
        ITable.put("AIR", new OpCode(20));      //20
        ITable.put("SIR", new OpCode(21));      //21
        ITable.put("MLT", new OpCode(22));      //22
        ITable.put("DVD", new OpCode(23));      //23
        ITable.put("TRR", new OpCode(24));      //24
        ITable.put("AND", new OpCode(25));      //25
        ITable.put("ORR", new OpCode(26));      //26
        ITable.put("NOT", new OpCode(27));      //27
        // ITable.put("",new OpCode(28));      //28
        // ITable.put("",new OpCode(29));      //29
        ITable.put("SRC", new OpCode(30));      //30
        ITable.put("RRC", new OpCode(31));      //31
        ITable.put("IN", new OpCode(32));       //32
        ITable.put("OUT", new OpCode(33));      //33
        ITable.put("CHK", new OpCode(34));      //34
        ITable.put("FADD", new OpCode(35));      //35
        ITable.put("FSUB", new OpCode(36));      //36
        ITable.put("VADD", new OpCode(37));      //37
        // ITable.put("",new OpCode(38));      //38
        // ITable.put("",new OpCode(39));      //39
        ITable.put("VSUB", new OpCode(40));      //40
        ITable.put("CNVRT", new OpCode(41));      //41
        ITable.put("LDFR", new OpCode(42));      //42
        ITable.put("STFR", new OpCode(43));      //43
        ITable.put("SETCCE", new OpCode(44));     //44
        ITable.put("TRAP code", new OpCode(45));  //45
        // ITable.put("",new OpCode(46));      //46
        // ITable.put("",new OpCode(47));      //47
        // ITable.put("",new OpCode(48));      //48
        // ITable.put("",new OpCode(49));      //49
        // ITable.put("",new OpCode(50));      //50
        // ITable.put("",new OpCode(51));      //51
        // ITable.put("",new OpCode(52));      //52
        // ITable.put("",new OpCode(53));      //53
        // ITable.put("",new OpCode(54));      //54
        // ITable.put("",new OpCode(55));      //55
        // ITable.put("",new OpCode(56));      //56
        // ITable.put("",new OpCode(57));      //57
        // ITable.put("",new OpCode(58));      //58
        // ITable.put("",new OpCode(59));      //59
        // ITable.put("",new OpCode(60));      //60
        // ITable.put("",new OpCode(61));      //61
        // ITable.put("",new OpCode(62));      //62
        // ITable.put("",new OpCode(63));      //63
    }
}
//
//    public ISA() {
//
//        ITable.put("", (short) 0);   //Confirm wheter or not this is a Nop
//        ITable.put("LDR", (short) 1);      //1
//        ITable.put("SDR", (short) 2);      //2
//        ITable.put("LDA", (short) 3);      //3
//        ITable.put("LDX", (short) 4);      //4
//        ITable.put("STX", (short) 5);      //5
//        ITable.put("JZ", (short) 6);       //6
//        ITable.put("JNE", (short) 7);      //7
//        // ITable.put("",(short) 8);      //8
//        // ITable.put("",(short) 9);      //9
//        ITable.put("JCC", (short) 10);     //10
//        ITable.put("JMA", (short) 11);     //11
//        ITable.put("JSR", (short) 12);     //12
//        ITable.put("RFS", (short) 13);     //13
//        ITable.put("SOB", (short) 14);     //14
//        ITable.put("JGE", (short) 15);     //15
//        ITable.put("AMR", (short) 16);     //16
//        ITable.put("SMR", (short) 17);     //17
//        // ITable.put("",(short) 18);      //18
//        // ITable.put("",(short) 19);      //19
//        ITable.put("AIR", (short) 20);     //20
//        ITable.put("SIR", (short) 21);     //21
//        ITable.put("MLT", (short) 22);     //22
//        ITable.put("DVD", (short) 23);     //23
//        ITable.put("TRR", (short) 24);     //24
//        ITable.put("AND", (short) 25);     //25
//        ITable.put("ORR", (short) 26);     //26
//        ITable.put("NOT", (short) 27);     //27
//        // ITable.put("",(short) 28);      //28
//        // ITable.put("",(short) 29);      //29
//        ITable.put("SRC", (short) 30);     //30
//        ITable.put("RRC", (short) 31);     //31
//        ITable.put("IN", (short) 32);      //32
//        ITable.put("OUT", (short) 33);     //33
//        ITable.put("CHK", (short) 34);     //34
//        ITable.put("FADD", (short) 35);    //35
//        ITable.put("FSUB", (short) 36);    //36
//        ITable.put("VADD", (short) 37);    //37
//        // ITable.put("",(short) 38);      //38
//        // ITable.put("",(short) 39);      //39
//        ITable.put("VSUB", (short) 40);    //40
//        ITable.put("CNVRT", (short) 41);   //41
//        ITable.put("LDFR", (short) 42);    //42
//        ITable.put("STFR", (short) 43);    //43
//
//        ITable.put("SETCCE", (short) 44);     //44
//        ITable.put("TRAP code", (short) 45);  //45
//        // ITable.put("",(short) 46);      //46
//        // ITable.put("",(short) 47);      //47
//        // ITable.put("",(short) 48);      //48
//        // ITable.put("",(short) 49);      //49
//        // ITable.put("",(short) 50);      //50
//        // ITable.put("",(short) 51);      //51
//        // ITable.put("",(short) 52);      //52
//        // ITable.put("",(short) 53);      //53
//        // ITable.put("",(short) 54);      //54
//        // ITable.put("",(short) 55);      //55
//        // ITable.put("",(short) 56);      //56
//        // ITable.put("",(short) 57);      //57
//        // ITable.put("",(short) 58);      //58
//        // ITable.put("",(short) 59);      //59
//        // ITable.put("",(short) 60);      //60
//        // ITable.put("",(short) 61);      //61
//        // ITable.put("",(short) 62);      //62
//        // ITable.put("",(short) 63);      //63
//        // ITable.put("",(short) 64);      //64
//    }
//}
