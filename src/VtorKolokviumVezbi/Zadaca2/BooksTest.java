package VtorKolokviumVezbi.Zadaca2;

import java.util.*;
import java.util.stream.Collectors;

class Book
{
    private String naslov;
    private String kategorija;
    private float cena;

    public Book(String naslov, String kategorija, float cena) {
        this.naslov = naslov;
        this.kategorija = kategorija;
        this.cena = cena;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getKategorija() {
        return kategorija;
    }

    public float getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) %.2f", naslov, kategorija, cena);
    }
}


class BookCollection
{
    Set<Book> bookSet;

    public BookCollection() {
        this.bookSet = new HashSet<>();
    }

    public void printByCategory(String category) {
        SortedSet<Book> sortedSet = new TreeSet<>(Comparator
                .comparing(Book::getNaslov)
                .thenComparingDouble(Book::getCena));
        sortedSet.addAll(this.bookSet.stream().filter(r -> r.getKategorija().equals(category)).collect(Collectors.toList()));
        sortedSet.forEach(System.out::println);
    }

    public List<Book> getCheapestN(int n) {
        return this.bookSet.stream().sorted(Comparator.comparing(Book::getCena)).limit(n).collect(Collectors.toList());
    }

    public void addBook(Book book) {
        this.bookSet.add(book);
    }
}

public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}

// Вашиот код овде