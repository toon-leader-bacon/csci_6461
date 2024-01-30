package org.nocab.nocabmachine.nocab;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SourceProgramReaderTest {

    @org.junit.jupiter.api.Test
    void tokenizeLineOnWhitespaceTest() {
        SourceProgramReader underTest = new SourceProgramReader();

        String inputStr = "6\tData\t10\t\t\t;PUT 10 AT LOCATION 6\n";
        ArrayList<String> expected = new ArrayList<>() {
            {
                add("6");
                add("Data");
                add("10");
                add("");
                add("");
                add(";PUT");
                add("10");
                add("AT");
                add("LOCATION");
                add("6");
                add("");
            }
        };
        assertEquals(expected, underTest.tokenizeLineOnWhitespace(inputStr));
    }

    /*
    @org.junit.jupiter.api.Test
    void tokenizeLineTest() {
        SourceProgramReader underTest = new SourceProgramReader();

        String inputStr = "6\tData\t10\t\t\t;PUT 10 AT LOCATION 6\n";
        ArrayList<String> expected = new ArrayList<>() {
            {
                add("6");
                add("Data");
                add("10");
                add(";PUT 10 AT LOCATION 6");
            }
        };
        assertEquals(expected, underTest.tokenizeLine(inputStr));
    }
    */

    @org.junit.jupiter.api.Test
    void blab() {
        SourceProgramReader underTest = new SourceProgramReader();
        underTest.blab("/Users/nocab/Projects/School/CompArch/csci_6461/NocabMachine/src/test/java/org/nocab/nocabmachine/nocab/SourceProgram.txt");
    }


    @Test
    void processFields_Data() {
        SourceProgramReader underTest = new SourceProgramReader();

        ArrayList<Field> expected = new ArrayList<>() {
            {
                add(new Field(10, 10));
            }
        };
        assertEquals(expected, underTest.processFields("data", "10"));

        expected = new ArrayList<>() {
            {
                add(new Field(1024, 10));
            }
        };
        assertEquals(expected, underTest.processFields("DATA", "END"));
    }

    @Test
    void processFields_LDR() {
        SourceProgramReader underTest = new SourceProgramReader();

        ArrayList<Field> expected = new ArrayList<>() {
            {
                add(new Field(3, 2));
                add(new Field(0, 2));
                add(new Field(0, 1));
                add(new Field(10, 5));
            }
        };
        assertEquals(expected, underTest.processFields("LDR", "3,0,10"));

        expected = new ArrayList<>() {
            {
                add(new Field(3, 2));
                add(new Field(0, 2));
                add(new Field(1, 1));
                add(new Field(10, 5));
            }
        };
        assertEquals(expected, underTest.processFields("LDR", "3,0,10,1"));
        /*
        expected = new ArrayList<>() {
            {
                add(new Field(1024, 10));
            }
        };
        assertEquals(expected, underTest.processFields("DATA", "END"));
        */

    }
}
