//package PrvKolokviumVezbi.SeventhExercise;
//
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//abstract class Time implements Comparable
//{
//    int hours;
//    int minutes;
//
//    public int getHours() {
//        return hours;
//    }
//
//    public int getMinutes() {
//        return minutes;
//    }
//
//    public void setHours(int hours) {
//        this.hours = hours;
//    }
//
//    public void setMinutes(int minutes) {
//        this.minutes = minutes;
//    }
//}
//
//class Time24Format extends Time
//{
//
//    public Time24Format(String line) {
//        this.hours = Integer.parseInt(line.split("\\\\.|:")[0]);
//        this.minutes = Integer.parseInt(line.split("\\\\.|:")[1]);
//    }
//
//    @Override
//    public String toString() {
//        return hours+":"+minutes;
//    }
//    @Override
//    public int compareTo(Object o) {
//        return Integer.compare(this.getHours()+this.getMinutes(), ((Time)o).getHours() + ((Time)o).getMinutes());
//    }
//}
//
//class TimeAMPMFormat extends  Time{
//
//    String ampm = new String();
//    public TimeAMPMFormat(String line) {
//        int hour24 = Integer.parseInt(line.split("\\\\.|:")[0]);
//        int minutes24 = Integer.parseInt(line.split("\\\\.|:")[1]);
//        if(hour24 == 0 && (minutes24 >= 0 && minutes24 <=59))
//        {
//            this.hours = 12;
//            this.minutes = minutes24;
//            ampm = "AM";
//        }
//        else if((hour24 >= 1 && hour24 <= 11))
//        {
//            ampm = "AM";
//            this.hours = hour24;
//            this.minutes = minutes24;
//        }
//        else if((hour24 == 12) && (minutes24 >= 0 && minutes24 <=59))
//        {
//            ampm = "PM";
//            this.hours = hour24;
//            this.minutes = minutes24;
//        }
//        else if((hour24 >= 13 && hour24 <= 23))
//        {
//            ampm = "PM";
//            this.hours = hour24-12;
//            this.minutes = minutes24;
//        }
//    }
//
//    @Override
//    public String toString() {
//        return this.hours + ":" + this.minutes + " " + ampm;
//}
//
//    @Override
//    public int compareTo(Object o) {
//        return 0;
//    }
//
//    enum TimeFormat {
//    FORMAT_24, FORMAT_AMPM
//}
//class TimeTable
//{
//    ArrayList<Time24Format> timeList24Format;
//    ArrayList<TimeAMPMFormat> timeListAMPMFormat;
//
//    TimeTable()
//    {
//        timeList24Format = new ArrayList<>();
//        timeListAMPMFormat = new ArrayList<>();
//    }
//    void readTimes(InputStream inputStream)
//    {
//        Scanner scanner = new Scanner(inputStream);
//        while(scanner.hasNextLine())
//        {
//            String line = scanner.nextLine();
//            timeListAMPMFormat.add(new TimeAMPMFormat(line));
//            timeList24Format.add(new Time24Format(line));
//        }
//    }
//
//    void writeTimes(OutputStream outputStream, TimeFormat format)
//    {
//
//    }
//}
//public class TimesTest {
//
//    public static void main(String[] args) {
//        TimeTable timeTable = new TimeTable();
//        try {
//            timeTable.readTimes(System.in);
//        } catch (UnsupportedFormatException e) {
//            System.out.println("UnsupportedFormatException: " + e.getMessage());
//        } catch (InvalidTimeException e) {
//            System.out.println("InvalidTimeException: " + e.getMessage());
//        }
//        System.out.println("24 HOUR FORMAT");
//        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
//        System.out.println("AM/PM FORMAT");
//        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
//    }
//
//}
//
//
