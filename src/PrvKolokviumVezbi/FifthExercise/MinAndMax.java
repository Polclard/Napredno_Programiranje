package PrvKolokviumVezbi.FifthExercise;

import java.util.Scanner;


class MinMax<T>
{
    private T minimum;
    private T maximum;

    public MinMax()
    {

    }
    public MinMax(T minimum, T maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public T max()
    {
        return maximum;
    }
    public T min()
    {
        return minimum;
    }
    void update(T element)
    {
        this.maximum = element;
        this.minimum = element;
    }

    @Override
    public String toString() {
        return "MinMax{" +
                "minimum=" + minimum +
                ", maximum=" + maximum +
                '}';
    }
}
public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}