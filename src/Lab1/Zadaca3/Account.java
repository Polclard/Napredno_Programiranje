//package Lab1.Zadaca3;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class Account {
//    private String name;
//    private long ID;
//    private String balance;
//    private static List<Long> usedIds = new ArrayList<Long>();
//
//
//    public Account(String name, String balance) {
//        this.name = name;
//        this.balance = balance;
//        this.ID = initializeId();
//    }
//
//    public String getBalance() {
//        return balance;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public long getID() {
//        return ID;
//    }
//    public long getId() {
//        return ID;
//    }
//
//    public void setBalance(String balance) {
//        this.balance = balance;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Name:%s\nBalance:%s\n", name, balance);
//    }
//
//    private Long initializeId()
//    {
//        Random seed = new Random();
//        Long newID = seed.nextLong(100000 + seed.nextLong(900000));
//        for (int i = 0; i < usedIds.size(); i++) {
//            if(usedIds.get(i).equals(newID))
//            {
//                newID = seed.nextLong(100000 + seed.nextLong(900000));
//                i = 0;
//            }
//        }
//        usedIds.add(newID);
//        return newID;
//    }
//
//}
