package org.nocab.nocabmachine.nocab.DataStructures;

import org.nocab.nocabmachine.nocab.BinaryNumber;

public class SourceProgramLine {

    public SourceProgramLine() {
        this.memoryLocation = null;
        this.instruction = null;
        this.comments = "";
    }

    public SourceProgramLine(BinaryNumber memoryLocation_, Instruction instruction_, String comments_) {
        this.memoryLocation = memoryLocation_;
        this.instruction = instruction_;
        this.comments = comments_;
    }

    public BinaryNumber memoryLocation;
    public Instruction instruction;
    public String comments;

    public String assemble() {
        return this.assemble(false);
    }

    public String assemble(boolean includeComments) {
        StringBuilder result = new StringBuilder();
        result.append(this.memoryLocation.toString_Octal(6)); // 6 character strings
        result.append('\t');
        result.append(this.instruction.assemble());
        if (includeComments) {
            result.append('\t');
            result.append(this.comments);
        }
        result.append('\n');
        return result.toString();

    }
}
