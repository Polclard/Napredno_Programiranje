package PrvKolokviumVezbi.NineteenthExercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class SubtitleTime
{
    private String from;
    private String to;
    private long fromMinutes;
    private long fromSeconds;
    private double fromMiliseconds;

    private long toMinutes;
    private long toSeconds;
    private double toMiliseconds;

    private double toFullMiliseconds;
    private double fromFullMiliseconds;

    public SubtitleTime(String from, String to) {
        this.from = from;
        this.to = to;
        String [] splittedFrom = from.split(":");
        fromMinutes = Long.parseLong(splittedFrom[0]);
        fromSeconds = Long.parseLong(splittedFrom[1]);
        fromMiliseconds= Double.parseDouble(splittedFrom[2].replace(",", "."));

        String [] splittedTo = to.split(":");
        toMinutes = Long.parseLong(splittedTo[0]);
        toSeconds = Long.parseLong(splittedTo[1]);
        toMiliseconds= Double.parseDouble(splittedTo[2].replace(",", "."));

        fromFullMiliseconds = fromMiliseconds + fromSeconds*1000 + fromMinutes*60*1000;
        toFullMiliseconds = toMiliseconds + toSeconds*1000 + toMinutes*60*1000;
    }

    public void shift(int ms)
    {
        fromFullMiliseconds += ms;
        toFullMiliseconds += ms;

        toMinutes = (long)toFullMiliseconds/1000/60;
        toFullMiliseconds/=1000;
        toFullMiliseconds/=60;
        toSeconds = (long)toFullMiliseconds/1000;
        toFullMiliseconds/=1000;
        toMiliseconds = toFullMiliseconds;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("%s --> %s", from, to);
    }
}
class SubtitleRow
{
    private int orderNumber;
    private SubtitleTime subtitleTime;
    private String text;

    public SubtitleRow(int orderNumber, SubtitleTime subtitleTime, String text) {
        this.orderNumber = orderNumber;
        this.subtitleTime = subtitleTime;
        this.text = text;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public SubtitleTime getSubtitleTime() {
        return subtitleTime;
    }

    public String getText() {
        return text;
    }
}

class Subtitles
{
    ArrayList<SubtitleRow> subtitleRows = new ArrayList<>();
    int loadSubtitles(InputStream inputStream)
    {
        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNextLine())
        {
            int orderNumber = scanner.nextInt();
            scanner.nextLine();
            String fromToTime = scanner.nextLine();
            StringBuilder text = new StringBuilder();
            String str = "";
            while(!(str = scanner.hasNext("") ? "" : scanner.nextLine()).isEmpty())
            {
                text.append(str);
            }

            SubtitleRow newSubtitleRow = new SubtitleRow(orderNumber, new SubtitleTime(fromToTime.split(" --> ")[0], fromToTime.split(" --> ")[1]), text.toString());
            this.subtitleRows.add(newSubtitleRow);
        }
        return subtitleRows.size();
    }

    void print()
    {
        for(SubtitleRow row : subtitleRows)
        {
            System.out.printf("%d\n%s\n%s\n", row.getOrderNumber(), row.getSubtitleTime().toString(), row.getText());
        }
    }
    void shift(int ms){
        for(int i = 0; i < subtitleRows.size(); i++)
        {
            subtitleRows.get(i).getSubtitleTime().shift(ms);
        }
    }
}


public class SubtitlesTest {
    public static void main(String[] args) throws FileNotFoundException {
        Subtitles subtitles = new Subtitles();
        File file = new File("G:\\NaprednoProgramiranjeZadaci\\Napredno_Programiranje\\src\\PrvKolokviumVezbi\\NineteenthExercise\\file.txt");
       // int n = subtitles.loadSubtitles(System.in);
        int n = subtitles.loadSubtitles(new FileInputStream(file));
        System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
        subtitles.print();
        int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
        System.out.println(String.format("SHIFT FOR %d ms", shift));
        subtitles.shift(shift);
        System.out.println("+++++ SHIFTED SUBTITLES +++++");
        subtitles.print();
    }
}

