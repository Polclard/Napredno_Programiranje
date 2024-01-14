package PrvKolokviumVezbi.SeventeenthExercise;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

class Category implements Comparable<Category>
{
    String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Category o) {
        return name.compareTo(o.name);//TODO
    }

    @Override
    public boolean equals(Object o) {
        return this.name.equals(((Category)o).name);
    }
}

abstract class NewsItem
{
    protected String title;
    protected Date publish;
    protected Category category;

    public NewsItem(String title, Date publish, Category category) {
        this.title = title;
        this.publish = publish;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Date getPublish() {
        return publish;
    }

    public String getTitle() {
        return title;
    }

    protected abstract String getTeaser();
}

class TextNewsItem extends NewsItem
{
    private String newsText;

    public TextNewsItem(String title, Date publish, Category category, String newsText) {
        super(title, publish, category);
        this.newsText = newsText;
    }

    public String getNewsText() {
        return newsText;
    }

    @Override
    protected String getTeaser() {
        return String.format("%s\n%d\n%s\n", title, (Date.from(Instant.now()).getTime() - publish.getTime()) / 60000,  newsText.length()<=80 ? newsText : newsText.substring(0,80));
    }
}

class MediaNewsItem extends NewsItem
{
   private String url;
   private int viewNumber;


    public MediaNewsItem(String title, Date publish, Category category, String url, int viewNumber) {
        super(title, publish, category);
        this.url = url;
        this.viewNumber = viewNumber;
    }

    public int getViewNumber() {
    return viewNumber;
    }

    public String getUrl() {
        return url;
    }

    @Override
    protected String getTeaser() {
        return String.format("%s\n%d\n%s\n%d\n", title, (Date.from(Instant.now()).getTime() - publish.getTime()) / 60000 , url, viewNumber);
    }
}

class FrontPage
{
    List<NewsItem> newsItemList = new ArrayList<>();
    Category[] categories;

    FrontPage(Category[] categories)
    {
        this.categories = categories;
    }
    void addNewsItem(NewsItem newsItem)
    {
        this.newsItemList.add(newsItem);
    }

    List<NewsItem> listByCategory(Category category)
    {
        return newsItemList.stream().filter(r -> r.getCategory().equals(category)).collect(Collectors.toList());
    }

    List<NewsItem> listByCategoryName(String category)
    {
        ArrayList<Category> arrayOfCategories = (ArrayList<Category>) Arrays.stream(categories).collect(Collectors.toList());

        if (arrayOfCategories.stream().filter(r -> r.getName().equals(category)).collect(Collectors.toList()).isEmpty())
        {
            throw new CategoryNotFoundException(String.format("Category %s was not found", category));
        }
        else
        {
             return newsItemList.stream().filter(r -> r.getCategory().name.equals(category)).collect(Collectors.toList());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(NewsItem item : this.newsItemList)
        {
            stringBuilder.append(item.getTeaser());
        }
        return stringBuilder.toString();
    }
}

class CategoryNotFoundException extends RuntimeException
{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}

public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


// Vasiot kod ovde