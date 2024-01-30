package org.nocab.nocabmachine.nocab.FieldProcessors;

import org.nocab.nocabmachine.nocab.Field;

import java.util.ArrayList;
import java.util.List;

public class HltFieldProcessor extends FieldProcessor {
    @Override
    public ArrayList<Field> process(String fields) {
        // Stops the machine.
        // Should have 0 fields.
        List<String> fieldTokens = super.splitFields(fields);
        if (!fieldTokens.isEmpty()) {
            throw new IllegalArgumentException("HLT operation has unexpected fields: " + fields);
        }
        return new ArrayList<>();
    }
}
