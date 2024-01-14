package PrvKolokviumVezbi.FourtheenthExercise;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
@SuppressWarnings("deprecation")
class Weather implements Comparable<Weather>
{
    private float temperature;
    private float humidity;
    private float wind;
    private float visibility;
    private Date date;
    @SuppressWarnings("deprecation")
    public Weather(float temperature, float humidity, float wind, float visibility, Date date) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.visibility = visibility;
        this.date = date;
    }

    public float getHumidity() {
        return humidity;
    }

    public Date getDate() {
        return date;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getVisibility() {
        return visibility;
    }

    public float getWind() {
        return wind;
    }

    @SuppressWarnings("deprecation")
    @Override
    public String toString() {
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s", temperature, wind, humidity, visibility, date.toGMTString());
    }

    @SuppressWarnings("deprecation")
    @Override
    public int compareTo(Weather weather) {
        return this.date.compareTo(weather.date);
    }
}

class WeatherStation
{
    ArrayList<Weather> weathers;
    int numberOfDays;
    public WeatherStation(int days) {
        this.numberOfDays = days;
        weathers = new ArrayList<>();
    }
    @SuppressWarnings("deprecation")
    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date)
    {

        Optional<Weather> optionalWeather =  weathers
                .stream()
                .filter(r -> (r.getDate().getDay() == date.getDay()) && (r.getDate().getHours() == date.getHours()) && (date.getMinutes()*60+date.getSeconds() - r.getDate().getMinutes()*60+r.getDate().getSeconds() <= 150))
                .findFirst();
        if(optionalWeather.isEmpty())
        {
            weathers = (ArrayList<Weather>) weathers.stream().filter(r -> date.getDay() - r.getDate().getDay() < numberOfDays).collect(Collectors.toList());
            weathers.add(new Weather(temperature, humidity, wind, visibility, date));
        }
    }

    public ArrayList<Weather> getWeathers() {
        return weathers;
    }

    public int total()
    {
        return weathers.size();
    }
    @SuppressWarnings("deprecation")
    public void status(Date from, Date to)
    {
        ArrayList<Weather> weathersToPrint = (ArrayList<Weather>) weathers
                .stream()
                .filter(r -> (r.getDate().compareTo(to) == 0) ||
                        (r.getDate().compareTo(from) > 0 &&
                        r.getDate().compareTo(to) < 0))
                .sorted(Weather::compareTo)
                .collect(Collectors.toList());
        if(weathersToPrint.isEmpty())
        {
            throw new RuntimeException();
        }
        else
        {
            weathersToPrint.forEach(System.out::println);
            System.out.printf("Average temperature: %.2f", weathersToPrint.stream().mapToDouble(Weather::getTemperature).sum() / weathersToPrint.size());
        }
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
}
@SuppressWarnings("deprecation")
public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurment(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

// vashiot kod ovde