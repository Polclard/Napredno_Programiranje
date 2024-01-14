package PrvKolokviumVezbi.EigthExercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

enum ArchiveType{LockedArchive, SpecialArchive}


class Archive
{
    int id;
    LocalDate dateArchived;
    ArchiveType archiveType;


    public Archive()
    {

    }

    public ArchiveType getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(ArchiveType archiveType) {
        this.archiveType = archiveType;
    }

    public void setDateArchived(LocalDate dateArchived) {
        this.dateArchived = dateArchived;
    }
}

class LockedArchive extends Archive
{
    LocalDate dateToOpen;

    public LockedArchive(int id, LocalDate dateToOpen)
    {
        this.id = id;
        this.dateToOpen = dateToOpen;
        this.archiveType = ArchiveType.LockedArchive;
    }

    public LocalDate getDateToOpen() {
        return dateToOpen;
    }
}

class SpecialArchive extends Archive
{
    int maxOpen;
    int timesOpened;

    public SpecialArchive(int id, int maxOpen)
    {
        this.id = id;
        this.maxOpen = maxOpen;
        this.archiveType = ArchiveType.SpecialArchive;
        this.timesOpened = 0;
    }

    public int getMaxOpen() {
        return maxOpen;
    }

    public int getTimesOpened() {
        return timesOpened;
    }

    public void setTimesOpened(int timesOpened) {
        this.timesOpened = timesOpened;
    }
}

class NonExistingItemException extends RuntimeException
{
    public NonExistingItemException(int id) {
        super("Item with id " + id + " doesn't exist");
    }
}

class ArchiveStore
{
    ArrayList<Archive> archives;
    ArrayList<String> logs;
    ArchiveStore()
    {
        this.archives = new ArrayList<>();
        logs = new ArrayList<>();
    }

    void archiveItem(Archive item, LocalDate date)
    {
        item.setDateArchived(date);
        this.archives.add(item);
        logs.add("Item " + item.id + " archived at " + date.toString() + "\n");
    }
    void openItem(int id, LocalDate date)
    {
        Optional<Archive> optionalArchive = this.archives.stream().filter(r -> r.id == id).findFirst();
        if(optionalArchive.isEmpty())
        {
            throw new NonExistingItemException(id);
        }
        else
        {
            if(optionalArchive.get().getArchiveType().equals(ArchiveType.LockedArchive))
            {
                LockedArchive object = (LockedArchive)optionalArchive.get();
                if(date.isBefore(object.getDateToOpen()))
                {
                    logs.add(String.format("Item %d cannot be opened before %s\n", id, object.getDateToOpen().toString()));
                }
                else
                {
                    logs.add("Item " + id + " opened at " + date.toString() + "\n");
                }
            }
            else if(optionalArchive.get().getArchiveType().equals(ArchiveType.SpecialArchive))
            {
                SpecialArchive object = (SpecialArchive) optionalArchive.get();
                object.setTimesOpened(object.getTimesOpened()+1);
                if(object.getTimesOpened() > object.getMaxOpen())
                {
                    logs.add(String.format("Item %d cannot be opened more than %d times\n", id, object.getMaxOpen()));
                }
                else
                {
                    logs.add("Item " + id + " opened at " + date.toString() + "\n");
                }
            }
        }

    }
    String getLog()
    {
        StringBuilder stringBuilder = new StringBuilder();
        logs.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        LocalDate date = LocalDate.of(2013, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();

            LocalDate dateToOpen = date.atStartOfDay().plusSeconds(days * 24 * 60 * 60).toLocalDate();
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}
