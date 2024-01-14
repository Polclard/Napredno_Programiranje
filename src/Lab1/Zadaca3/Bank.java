//package Lab1.Zadaca3;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class Bank {
//    private String name;
//    private List<Account> accounts;
//    private Long amount;
//    private Long totalProvision;
//
//    Bank(String name, Account accounts[])
//    {
//        this.name = name;
//        this.accounts = Arrays.stream(accounts).toList();
//    }
//    public boolean makeTransaction(Transaction t) {
//        Optional<Account> fromAccount = accounts.stream().filter(r -> r.getID() == t.getFromId()).findFirst();
//        Optional<Account> toAccount = accounts.stream().filter(r -> r.getID() == t.getToId()).findFirst();
//        if(fromAccount.isPresent() && toAccount.isPresent())
//        {
//            double fromAccountAmountInDouble = Double.parseDouble(fromAccount.get().getBalance().split("$")[0]);
//            double transactionAmount = Double.parseDouble(t.getAmount());
//
//            if(t instanceof FlatAmountProvisionTransaction)
//            {
//
//            }
//            else if(t instanceof FlatPercentProvisionTransaction)
//            {
//
//            }
//
//            if(fromAccountAmountInDouble >= transactionAmount)
//            {
//
//                return true;
//            }
//        }
//        else
//        {
//            return false;
//        }
//    }
//
//    public Long getTotalProvision() {
//        return totalProvision;
//    }
//}
