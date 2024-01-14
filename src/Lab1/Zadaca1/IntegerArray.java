package Lab1.Zadaca1;

import java.util.Arrays;
import java.util.stream.Stream;

public final class IntegerArray {
    private final int[] array;

    public IntegerArray(int[] a) {
        this.array = Arrays.copyOf(a, a.length);
    }

    public int length() {
        return array.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerArray that = (IntegerArray) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    public int getElementAt(int i) {
        if (i < 0 || i >= array.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[i];
    }

    public int sum() {
        return Arrays.stream(array).sum();
    }

    public double average() {
        return (double) Arrays.stream(array).sum() / (double) array.length;
    }

    public IntegerArray getSorted() {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        return new IntegerArray(sortedArray);
    }

    public IntegerArray concat(IntegerArray ia) {
        int[] newArray = new int[this.array.length + ia.array.length];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        System.arraycopy(ia.array, 0, newArray, this.array.length, ia.array.length);
        return new IntegerArray(newArray);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < this.array.length; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(this.array[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}