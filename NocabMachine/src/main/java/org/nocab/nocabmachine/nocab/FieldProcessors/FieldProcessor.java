package org.nocab.nocabmachine.nocab.FieldProcessors;

import org.nocab.nocabmachine.nocab.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FieldProcessor {

    public abstract ArrayList<Field> process(String fields);

    List<String> splitFields(String fields) {
        if (fields.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(fields.split(","));
    }
}
