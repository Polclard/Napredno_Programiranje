package VtorKolokviumVezbi.Zadaca4;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Partial exam II 2016/2017
 */

class File implements Comparable<File>
{
    char folder;
    String name;
    int size;
    LocalDateTime createdAt;

    public File(char folder, String name, int size, LocalDateTime createdAt) {
        this.folder = folder;
        this.name = name;
        this.size = size;
        this.createdAt = createdAt;
    }

    public char getFolder() {
        return folder;
    }

    public String getName() {
        return name;
    }

    public String getMonthAndDay()
    {
        return String.format("%s-%d",
                this.createdAt.getMonth().toString(),
                this.createdAt.getDayOfMonth());
    }

    public int getSize() {
        return size;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public int compareTo(File o) {
        Comparator<File> comparator = Comparator
                .comparing(File::getCreatedAt)
                .thenComparing(File::getName)
                .thenComparingInt(File::getSize);

        return comparator.compare(this, o);
    }

    @Override
    public String toString() {
        return String.format("%-10s %5dB %s",
                name,size,createdAt.toString());
    }
}

class FileSystem
{
    List<File> files;

    FileSystem()
    {
        this.files = new ArrayList<>();
    }

    public void addFile(char folder, String name, int size, LocalDateTime createdAt)
    {
         File newFile = new File(
            folder,
            name,
            size,
            createdAt
        );
        this.files.add(newFile);
    }
    public List<File> findAllHiddenFilesWithSizeLessThen(int size){
        return this.files.stream()
                .filter(file -> file.getName().startsWith("."))
                .filter(file -> file.getSize() < size)
                .sorted()
                .collect(Collectors.toList());
    }
    public int totalSizeOfFilesFromFolders(List<Character> folders){
        return this.files.stream()
                .filter(file -> folders.contains(file.folder))
                .mapToInt(File::getSize)
                .sum();
    }
    public Map<Integer, Set<File>> byYear(){
        return this
                .files
                .stream()
                .collect(Collectors.groupingBy(
                        file -> file.createdAt.getYear(),
                        Collectors.toCollection(TreeSet::new)
                ));
    }
    public Map<String, Long> sizeByMonthAndDay(){
        return this
                .files
                .stream()
                .collect(Collectors.groupingBy(
                        File::getMonthAndDay,
                        Collectors.summingLong(File::getSize)
                ));
    }
}
public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
}

// Your code here
