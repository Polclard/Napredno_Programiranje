//package VtorKolokviumVezbi.Zadaca6;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintStream;
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//class Employee
//{
//    private String ID ;
//    private String level;
//
//    public Employee(String ID, String level) {
//        this.ID = ID;
//        this.level = level;
//    }
//
//    public String getID() {
//        return ID;
//    }
//
//    public String getLevel() {
//        return level;
//    }
//}
//
//class HourlyEmployee extends Employee
//{
//    private Double hours;
//
//    public HourlyEmployee(String ID, String level, Double hours) {
//        super(ID, level);
//        this.hours = hours;
//    }
//
//    public Double getHours() {
//        return hours;
//    }
//
//    @Override
//    public String getID() {
//        return super.getID();
//    }
//
//    @Override
//    public String getLevel() {
//        return super.getLevel();
//    }
//}
//
//class FreelanceEmployee extends Employee
//{
//    ArrayList<Integer> points;
//
//    public FreelanceEmployee(String ID, String level, ArrayList<Integer> points) {
//        super(ID, level);
//        this.points = points;
//    }
//
//    @Override
//    public String getLevel() {
//        return super.getLevel();
//    }
//
//    @Override
//    public String getID() {
//        return super.getID();
//    }
//
//    public ArrayList<Integer> getPoints() {
//        return points;
//    }
//}
//
//class PayrollSystem
//{
//
//    ArrayList<HourlyEmployee> hourlyEmployees;
//    ArrayList<FreelanceEmployee> freelanceEmployees;
//
//    Map<String, Double> hourlyRateByLevel;
//    Map<String, Double> ticketRateByLevel;
//    public PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
//        this.hourlyEmployees = new ArrayList<>();
//        this.freelanceEmployees = new ArrayList<>();
//        this.hourlyRateByLevel = hourlyRateByLevel;
//        this.ticketRateByLevel = ticketRateByLevel;
//    }
//
//    public void readEmployees(InputStream in) {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//
//
//        bufferedReader.lines()
//                .filter(line
//                        ->
//                        Objects.equals(Arrays.stream(line.split(";")).collect(Collectors.toList()).get(0), "H"))
//                .map(line -> new HourlyEmployee(
//                        Arrays.stream(line.split(";")).collect(Collectors.toList()).get(1),
//                        Arrays.stream(line.split(";")).collect(Collectors.toList()).get(2),
//                        Double.parseDouble(Arrays.stream(line.split(";")).collect(Collectors.toList()).get(3))
//                )).forEach(hourlyEmployee -> this.hourlyEmployees.add(hourlyEmployee));
//
//        bufferedReader.lines()
//                .filter(line
//                        ->
//                        Objects.equals(Arrays.stream(line.split(";")).collect(Collectors.toList()).get(0), "F"))
//                .map(line -> new FreelanceEmployee(
//                        Arrays.stream(line.split(";")).collect(Collectors.toList()).get(1),
//                        Arrays.stream(line.split(";")).collect(Collectors.toList()).get(2),
//                        Double.parseDouble(Arrays.stream(line.split(";")).collect(Collectors.toList()).get(3))//todo
//                )).forEach(freelanceEmployee -> this.freelanceEmployees.add(freelanceEmployee));
//    }
//
//    public Map<String, Set<Employee>> printEmployeesByLevels(PrintStream out, Set<String> levels) {
//    }
//}
//public class PayrollSystemTest {
//
//    public static void main(String[] args) {
//
//        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
//        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
//        for (int i = 1; i <= 10; i++) {
//            hourlyRateByLevel.put("level" + i, 10 + i * 2.2);
//            ticketRateByLevel.put("level" + i, 5 + i * 2.5);
//        }
//
//        PayrollSystem payrollSystem = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);
//
//        System.out.println("READING OF THE EMPLOYEES DATA");
//        payrollSystem.readEmployees(System.in);
//
//        System.out.println("PRINTING EMPLOYEES BY LEVEL");
//        Set<String> levels = new LinkedHashSet<>();
//        for (int i=5;i<=10;i++) {
//            levels.add("level"+i);
//        }
//        Map<String, Set<Employee>> result = payrollSystem.printEmployeesByLevels(System.out, levels);
//        result.forEach((level, employees) -> {
//            System.out.println("LEVEL: "+ level);
//            System.out.println("Employees: ");
//            employees.forEach(System.out::println);
//        });
//
//
//    }
//}