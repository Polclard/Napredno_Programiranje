//package PrvKolokviumVezbi.NinthExercise;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Scanner;
//
//class Triple<T extends Comparable>
//{
//    private Double a, b, c;
//    private ArrayList<Double> arrayList;
//
//    public Triple(Double a, Double b, Double c) {
//        this.a = a;
//        this.b = b;
//        this.c = c;
//        this.arrayList = new ArrayList<>();
//        arrayList.add(a);
//        arrayList.add(b);
//        arrayList.add(c);
//    }
//
//    public Double max()
//    {
//        if(a.compareTo(b) > 0 && a.compareTo(c) > 0)
//            return a;
//        if(b.compareTo(a) > 0 && b.compareTo(c) > 0)
//            return b;
//        return c;
//    }
//
//    public double avarage()
//    {
//        return ((double)a + (double) b + (double) c) /3;
//    }
//
//    void sort()
//    {
//       arrayList.sort(Comparator.naturalOrder());
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%.2f %.2f %.2f", (double)arrayList.get(0), (double)arrayList.get(1), (double)arrayList.get(2));
//    }
//}
//
//public class TripleTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int a = scanner.nextInt();
//        int b = scanner.nextInt();
//        int c = scanner.nextInt();
//        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
//        System.out.printf("%.2f\n", tInt.max());
//        System.out.printf("%.2f\n", tInt.avarage());
//        tInt.sort();
//        System.out.println(tInt);
//        float fa = scanner.nextFloat();
//        float fb = scanner.nextFloat();
//        float fc = scanner.nextFloat();
//        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
//        System.out.printf("%.2f\n", tFloat.max());
//        System.out.printf("%.2f\n", tFloat.avarage());
//        tFloat.sort();
//        System.out.println(tFloat);
//        double da = scanner.nextDouble();
//        double db = scanner.nextDouble();
//        double dc = scanner.nextDouble();
//        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
//        System.out.printf("%.2f\n", tDouble.max());
//        System.out.printf("%.2f\n", tDouble.avarage());
//        tDouble.sort();
//        System.out.println(tDouble);
//    }
//}
//
//
