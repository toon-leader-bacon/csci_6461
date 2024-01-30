package org.nocab.nocabmachine.nocab.DataStructures;

import org.nocab.nocabmachine.nocab.DataStructures.Instruction;

import java.util.ArrayList;

public class Program {

    public Program() {
        code = new ArrayList<>();
    }

    public Program(ArrayList<Instruction> code_) {
        this.code = code_;
    }

    ArrayList<Instruction> code;

    public void addInstruction(Instruction instruction) {
        this.code.add(instruction);
    }
}
