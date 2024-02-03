package nocab;

import java.util.ArrayList;

/**
 * A Word is a BinaryNumber that has exactly 16 bits
 */
public class Word extends BinaryNumber {
    public static final int SIZE = 16; // Each word contains 16 bits

    public static final Word ZERO = new Word(Word.buildArrayList(Word.SIZE, false));
    public static final Word ALL_ONES = new Word(Word.buildArrayList(Word.SIZE, true));

    public Word() {
        // Default to 0 value word
        this.setValue(ZERO.getValue());
    }

    public Word(ArrayList<Boolean> value) {
        this.setValue(value);
    }

    public Word(String value) {
        this.setValue(buildArrayList(value));
    }


    protected static ArrayList<Boolean> buildArrayList(int size, boolean value) {
        ArrayList<Boolean> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i, value);
        }
        return result;
    }

    public void setValue(ArrayList<Boolean> newValue) {
        // Else the size isn't right...
        if (newValue.size() < Word.SIZE) {
            // Add 0s to the front until it's the right size
            while (newValue.size() < Word.SIZE) {
                newValue.addFirst(false);
            }
        } else if (newValue.size() > Word.SIZE) {
            // Truncate the bits that are too large (keep the lowest/ rightmost 16 bits)
            int newStart = newValue.size() - 16;
            newValue = new ArrayList<>(newValue.subList(newStart, newValue.size()));
        }
        // Else the newValue.size is exactly = Word.SIZE

        super.setValue(newValue);
    }

}
