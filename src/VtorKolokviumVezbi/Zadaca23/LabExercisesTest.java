package VtorKolokviumVezbi.Zadaca23;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Student implements Comparable<Student>
{
    String index;
    List<Integer> labPoints;
    private static double NUMBER_OF_LABS = 10.0;

    public Student(String index, List<Integer> labPoints) {
        this.index = index;
        this.labPoints = labPoints;
    }

    public String getIndex() {
        return index;
    }

    public List<Integer> getLabPoints() {
        return labPoints;
    }

    public Integer getNumberOfAttendedLabExercises()
    {
        return this.labPoints.size();
    }

    public Double getSummaryPoints()
    {
        return this.labPoints.stream().mapToDouble(r -> r).sum()/NUMBER_OF_LABS;
    }

    private String getSignature()
    {
        return getNumberOfAttendedLabExercises() < 8 ? "NO" : "YES";
    }

    public boolean getSignatureBoolean()
    {
        return getNumberOfAttendedLabExercises() >= 8;
    }
    @Override
    public int compareTo(Student that) {
        Comparator<Student> comparator =
                Comparator.comparingDouble(Student::getSummaryPoints)
                        .thenComparing(Student::getIndex);

        return comparator.compare(this, that);
    }

    @Override
    public String toString() {

        return String.format("%s %s %.2f", index, getSignature(), getSummaryPoints());
    }
}


class LabExercises
{
    ArrayList<Student> students;

    public LabExercises() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void printByAveragePoints (boolean ascending, int n)
    {
        if(ascending)
        {
            this.students
                    .stream()
                    .sorted(Student::compareTo)
                    .sorted(Comparator.naturalOrder())
                    .limit(n)
                    .forEach(System.out::println);
        }
        else
        {
            this.students
                .stream()
                .sorted(Student::compareTo)
                .sorted(Comparator.reverseOrder())
                .limit(n)
                .forEach(System.out::println);
        }
    }

    public List<Student> failedStudents()
    {
        Comparator<Student> customComparator =
                Comparator
                        .comparing(Student::getIndex)
                        .thenComparing(Student::getSummaryPoints);

        return this.students.stream()
                .filter(student -> !student.getSignatureBoolean())
                .sorted(customComparator)
                .collect(Collectors.toList());
    }

    public Map<Integer,Double> getStatisticsByYear()
    {
        return this.students.stream()
                .filter(Student::getSignatureBoolean)
                .collect(Collectors.groupingBy(
                            student -> 20-Integer.parseInt(student.getIndex().substring(0,2)),
                            Collectors.averagingDouble(Student::getSummaryPoints)
                        ));
    }

}
public class LabExercisesTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}