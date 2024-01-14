package PrvKolokviumVezbi.TwenteethFIrstExercise;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class CustomTime implements Comparable<CustomTime>{
    private int minutes;
    private int seconds;
    private int miliseconds;

    public CustomTime(int minutes, int seconds, int miliseconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.miliseconds = miliseconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMiliseconds() {
        return miliseconds;
    }

    public int sumOfTimes()
    {
        return miliseconds + seconds* 1000 + minutes * 60 * 1000;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d:%03d", minutes, seconds, miliseconds);
    }

    @Override
    public int compareTo(CustomTime o) {
        return Integer.compare(this.sumOfTimes(), o.sumOfTimes());
    }
}

class F1 implements Comparable<F1>{
    private String driverName;
    private ArrayList<CustomTime> times;


    public F1(String driverName, ArrayList<CustomTime> times) {
        this.driverName = driverName;
        this.times = times;
    }
    public F1(String line)
    {
        times = new ArrayList<>();
        String [] splitedString = line.split("\\s+");
        ArrayList<String> arrayList = (ArrayList<String>) Arrays.stream(splitedString).collect(Collectors.toList());
        this.driverName = arrayList.remove(0);
        for(int i = 0; i < 3; i++)
        {
            this.times.add(new CustomTime(Integer.parseInt(arrayList.get(i).split(":")[0]),
                                          Integer.parseInt(arrayList.get(i).split(":")[1]),
                                          Integer.parseInt(arrayList.get(i).split(":")[2])));

        }
    }

    public ArrayList<CustomTime> getTimes() {
        return times;
    }

    public String getDriverName() {
        return driverName;
    }

    @Override
    public String toString() {
        this.times = (ArrayList<CustomTime>) this.times.stream().sorted(CustomTime::compareTo).collect(Collectors.toList());
        return String.format("%-10s %10s", driverName, times.get(0).toString());
    }

    @Override
    public int compareTo(F1 o) {
        this.times = (ArrayList<CustomTime>) this.times.stream().sorted(CustomTime::compareTo).collect(Collectors.toList());
        return Integer.compare(this.times.get(0).sumOfTimes(), o.times.get(0).sumOfTimes());
    }
}

class F1Race
{
    ArrayList<F1> f1Races;


    public F1Race()
    {
        f1Races = new ArrayList<>();
    }


    void readResults(InputStream inputStream)
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        bufferedReader.lines().map(F1::new).forEach(r-> this.f1Races.add(r));
    }

    void printSorted(OutputStream outputStream)
    {
        PrintWriter printWriter = new PrintWriter(outputStream);
        f1Races.sort(F1::compareTo);
        for(int i = 0; i < f1Races.size(); i++)
        {
            System.out.printf("%d. %s\n", i+1, f1Races.get(i).toString());
        }

        printWriter.flush();
        printWriter.close();
    }

}

public class F1Test {
    public static void main(String[] args) throws FileNotFoundException {
        F1Race f1Race = new F1Race();
        File file = new File("G:\\NaprednoProgramiranjeZadaci\\Napredno_Programiranje\\src\\PrvKolokviumVezbi\\TwenteethFIrstExercise\\file.txt");
//        f1Race.readResults(System.in);
        f1Race.readResults(new FileInputStream(file));
        f1Race.printSorted(System.out);
    }

}
