package org.nocab.nocabmachine.akm.v2;

import java.util.*;
import org.nocab.nocabmachine.akm.v2.Assembler;

public class Main {
    static String[] MEM1 = new String[] {
        "LDR 3,0,15"
    };

    static String[] MEM2 = new String[] {
        "LOC 6",        //BEGIN AT LOCATION 6
        "Data 10",      //PUT 10 AT LOCATION 6
        "Data 3",       //PUT 3 AT LOCATION 7
        "Data End",     //PUT 1024 AT LOCATION 8
        "Data 0",       // ...
        "Data 12",
        "Data 9",
        "Data 18",
        "Data 12",
        "LDX 2,7",      //X2 GETS 3
        "LDR 3,0,10",   //R3 GETS 12
        "LDR 2,2,10",   //R2 GETS 12
        "LDR 1,2,10,1", //R1 GETS 18
        "LDA 0,0,0",    //R0 GETS 0
        "LDX 1,8",      //X1 GETS 1024
        "SETCCE 1",     //SET CONDITION CODE FOR EQUAL
        "JZ 1,0",       //JUMP TO End if CC is 1
        "LOC 1024",
        "End: HLT"      //STOP
    };

    public static void main(String[] args) {
        Assembler assembler = new Assembler();  //init new Assembler obj
        String[] MEM = MEM1;                    //load instr into Memory
        assembler.assemble(MEM);                //load memory into assembler, run
        return;
    }
}