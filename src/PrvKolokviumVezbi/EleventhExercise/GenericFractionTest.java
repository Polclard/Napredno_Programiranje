package PrvKolokviumVezbi.EleventhExercise;

import java.util.Scanner;

class GenericFraction<T extends Number, U extends Number>
{
    private T numerator;
    private U denominator;

    public GenericFraction(T numerator, U denominator) {
        this.numerator = numerator;
        if(denominator.equals(0))
        {
            throw new ZeroDenominatorException("Denominator cannot be zero");
        }
        this.denominator = denominator;
    }

    GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf)
    {
        int tempDenominator = this.denominator.intValue() * gf.denominator.intValue();
        int tempNumerator = this.numerator.intValue() * (tempDenominator / this.denominator.intValue()) +
                gf.numerator.intValue() * (tempDenominator / gf.denominator.intValue());
        int gcd = findGCD(tempNumerator, tempDenominator);
        int simplifiedNumerator = tempNumerator / gcd;
        int simplifiedDenominator = tempDenominator / gcd;
        return new GenericFraction<>((double)simplifiedNumerator, (double)simplifiedDenominator);
    }

    public static int findGCD(int a, int b) {
        int largest = 1;
       for(int i = 2; i < 1000; i++)
       {
           if (a % i == 0 && b % i == 0)
           {
               largest = i;
           }
       }
       return largest;
    }

    double toDouble()
    {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public String toString() {
        int gcd = findGCD(this.numerator.intValue(), this.denominator.intValue());
        int simplifiedNumerator = numerator.intValue() / gcd;
        int simplifiedDenominator = denominator.intValue() / gcd;

        return String.format("%.2f / %.2f", (double)simplifiedNumerator, (double)simplifiedDenominator);
    }
}

class ZeroDenominatorException extends RuntimeException
{
    public ZeroDenominatorException(String message) {
        super(message);
    }
}

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}

// вашиот код овде
