package org.nocab.nocabmachine.nocab;

public class Field {

    public Field(short value_, short size_) {
        this.value = value_;
        this.size = size_;
    }


    public Field(int value_, short size_) {
        this((short) value_, size_);
    }

    public Field(short value_, int size_) {
        this(value_, (short) size_);
    }

    public Field(int value_, int size_) {
        this((short) value_, (short) size_);
    }

    public Field(String value_, int size_) {
        Field other = Field.fromDecimalString(value_, size_);
        this.value = other.value;
        this.size = other.size;
    }


    public static Field fromDecimalString(String value, int size) {
        if (!Utility.isNumeric((value))) {
            throw new IllegalArgumentException("Can not create field from value string (decimal): " + value);
        }
        return new Field(Integer.parseInt(value), size);
    }

    public short value;
    public short size;

    /**
     * Convert this Field into a binary string of the appropriate length
     *
     * @return A string representation of this binary number
     */
    public String toBinString() {
        StringBuilder result = new StringBuilder(this.size);

        // Initialize the result with the minimal binary representation
        result.append(Integer.toBinaryString(this.value));

        // Add 0s to the front of the result to reach the expected size
        while (result.length() < this.size) {
            result.insert(0, '0');
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return "Field{" +
                "value=" + value +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Field other = (Field) obj;
        return this.value == other.value &&
                this.size == other.size;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.value;
        hash = 53 * hash + this.size;
        return hash;
    }
}
