package Vezbi.PrvaZadaca;

import java.util.*;


class Participant
{
    public String city;
    public String code;
    public String name ;
    public int age;

    public Participant(String city, String code, String name, int age) {
        this.city = city;
        this.code = code;
        this.name = name;
        this.age = age;
    }
    public Participant(String code, String name, int age) {
        this.city = "";
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Audition
{
    private Map<String, Set<String>> cityParticipants;
    private Map<String, List<Participant>> candidatesByCity;

    public Audition() {
        cityParticipants = new HashMap<>();
        candidatesByCity = new HashMap<>();
    }

    public void addParticpant(String city, String code, String name, int age)
    {
        if(cityParticipants.containsKey(city) == false)
        {
            cityParticipants.put(city, new HashSet<>());
            candidatesByCity.put(city, new ArrayList<>());
        }

        if(cityParticipants.get(city).contains(code) == false)
        {
            cityParticipants.get(city).add(code);
            Participant newParticipant = new Participant(code,name,age);
            candidatesByCity.get(city).add(newParticipant);
        }
    }

    public void listByCity(String city)
    {
        List<Participant> candidates = candidatesByCity.get(city);
        candidates.sort(Comparator.comparing(Participant::getName).thenComparing(Participant::getAge));
        for (Participant candidate : candidates) {
            System.out.println(candidate.code + " " + candidate.name + " " + candidate.age);
        }
    }
}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}