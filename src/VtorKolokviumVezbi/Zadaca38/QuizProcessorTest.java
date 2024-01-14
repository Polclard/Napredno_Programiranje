package VtorKolokviumVezbi.Zadaca38;


import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class RightAndStudentAnswersNotEqual extends RuntimeException
{
    public RightAndStudentAnswersNotEqual(String message) {
        super(message);
    }
}

class Quiz
{
    private String studentIndex;
    private ArrayList<String> rightAnswers;
    private ArrayList<String> studentAnswers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(studentIndex, quiz.studentIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentIndex);
    }

    public Quiz(String studentIndex, String rightAnswers, String studentAnswers) {
        this.studentIndex = studentIndex;
        this.rightAnswers = new ArrayList<>();
        this.studentAnswers = new ArrayList<>();
        if(Arrays.stream(rightAnswers.split(", ")).collect(Collectors.toList()).size()
            !=
            Arrays.stream(studentAnswers.split(", ")).collect(Collectors.toList()).size()
        )
        {
            throw new RightAndStudentAnswersNotEqual("A quiz must have same number of correct and selected answers");
        }
        this.rightAnswers.addAll(Arrays.asList(rightAnswers.split(", ")));
        this.studentAnswers.addAll(Arrays.asList(studentAnswers.split(", ")));
    }

    public Double calculatePoints()
    {
        double points = 0.0;
        for(int i = 0; i < rightAnswers.size(); i++)
        {
            if(rightAnswers.get(i).equals(studentAnswers.get(i)))
                points += 1;
            else
                points -= 0.25;
        }
        return points;
    }

    public String getStudentIndex() {
        return studentIndex;
    }
}

class QuizProcessor
{

    public static Map<String, Double> processAnswers(InputStream is)
    {   ArrayList<Quiz> quizzes = new ArrayList<>();
        Map<String, Double> map = new TreeMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));


            bufferedReader
                    .lines()
                    .map(
                            line -> {
                                try
                                {
                                    return new Quiz(
                                            Arrays.stream(line.split(";")).collect(Collectors.toList()).get(0),
                                            Arrays.stream(line.split(";")).collect(Collectors.toList()).get(1),
                                            Arrays.stream(line.split(";")).collect(Collectors.toList()).get(2)
                                    );
                                }catch (RightAndStudentAnswersNotEqual e)
                                {
                                    System.out.println(e.getMessage());
                                    return  null;
                                }
                            }
                    )
                    .filter(Objects::nonNull)
                    .forEach(quizzes::add);
        quizzes.forEach(quiz -> map.put(quiz.getStudentIndex(), quiz.calculatePoints()));
        return map;
    }
}
public class QuizProcessorTest {
    public static void main(String[] args) {
        QuizProcessor.processAnswers(System.in).forEach((k, v) -> System.out.printf("%s -> %.2f%n", k, v));
    }
}