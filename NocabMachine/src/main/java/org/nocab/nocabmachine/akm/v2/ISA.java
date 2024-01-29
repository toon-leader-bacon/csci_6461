package org.nocab.nocabmachine.akm.v2;
import java.util.*;

public class ISA {
    Dictionary<String, Short> ITable = new Hashtable<>();


    public ISA()
    {

        ITable.put("",(short) 0);   //Confirm wheter or not this is a Nop
        ITable.put("LDR",(short) 1);      //1
        ITable.put("SDR",(short) 2);      //2
        ITable.put("LDA",(short) 3);      //3
        ITable.put("LDX",(short) 4);      //4
        ITable.put("STX",(short) 5);      //5
        ITable.put("JZ" ,(short) 6);       //6
        ITable.put("JNE",(short) 7);      //7
        // ITable.put("",(short) 8);      //8
        // ITable.put("",(short) 9);      //9
        ITable.put("JCC",(short) 10);      //10
        ITable.put("JMA",(short) 11);      //11
        ITable.put("JSR",(short) 12);      //12
        ITable.put("RFS",(short) 13);      //13
        ITable.put("SOB",(short) 14);      //14
        ITable.put("JGE",(short) 15);      //15
        ITable.put("AMR",(short) 16);      //16
        ITable.put("SMR",(short) 17);      //17
        // ITable.put("",(short) 18);      //18
        // ITable.put("",(short) 19);      //19
        ITable.put("AIR",(short) 20);      //20
        ITable.put("SIR",(short) 21);      //21
        ITable.put("MLT",(short) 22);      //22
        ITable.put("DVD",(short) 23);      //23
        ITable.put("TRR",(short) 24);      //24
        ITable.put("AND",(short) 25);      //25
        ITable.put("ORR",(short) 26);      //26
        ITable.put("NOT",(short) 27);      //27
        // ITable.put("",(short) 28);      //28
        // ITable.put("",(short) 29);      //29
        ITable.put("SRC", (short) 30);      //30
        ITable.put("RRC", (short) 31);      //31
        ITable.put("IN",  (short) 32);       //32
        ITable.put("OUT", (short) 33);      //33
        ITable.put("CHK", (short) 34);      //34
        ITable.put("FADD",(short) 35);      //35
        ITable.put("FSUB",(short) 36);      //36
        ITable.put("VADD",(short) 37);      //37
        // ITable.put("",(short) 38);      //38
        // ITable.put("",(short) 39);      //39
        ITable.put("VSUB", (short) 40);      //40
        ITable.put("CNVRT",(short) 41);      //41
        ITable.put("LDFR", (short) 42);      //42
        ITable.put("STFR", (short) 43);      //43

        ITable.put("SETCCE",(short) 44);     //44
        ITable.put("TRAP code",(short) 45);  //45
        // ITable.put("",(short) 46);      //46
        // ITable.put("",(short) 47);      //47
        // ITable.put("",(short) 48);      //48
        // ITable.put("",(short) 49);      //49
        // ITable.put("",(short) 50);      //50
        // ITable.put("",(short) 51);      //51
        // ITable.put("",(short) 52);      //52
        // ITable.put("",(short) 53);      //53
        // ITable.put("",(short) 54);      //54
        // ITable.put("",(short) 55);      //55
        // ITable.put("",(short) 56);      //56
        // ITable.put("",(short) 57);      //57
        // ITable.put("",(short) 58);      //58
        // ITable.put("",(short) 59);      //59
        // ITable.put("",(short) 60);      //60
        // ITable.put("",(short) 61);      //61
        // ITable.put("",(short) 62);      //62
        // ITable.put("",(short) 63);      //63
        // ITable.put("",(short) 64);      //64
    }
}
