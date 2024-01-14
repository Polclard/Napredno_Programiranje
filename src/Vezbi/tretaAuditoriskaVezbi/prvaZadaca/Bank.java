package Vezbi.tretaAuditoriskaVezbi.prvaZadaca;

import java.util.*;

public class Bank {
    private List<Account> listOfAccounts;
    private Account [] listOfAccountsWithNormalArray;
    private int totalAccounts;
    private int maxAccounts;

    public Bank(int maxAccounts) {
        this.maxAccounts = maxAccounts;
        this.listOfAccountsWithNormalArray = new Account[maxAccounts];
        this.listOfAccounts = new ArrayList<Account>(maxAccounts);
        this.totalAccounts = 0;
    }

    void addAccountInSecondList(Account newAccount)
    {
        if(!Arrays.stream(listOfAccountsWithNormalArray).anyMatch(p -> p.getAccountID() == newAccount.getAccountID()) &&
        this.totalAccounts < maxAccounts)
        {
            this.listOfAccountsWithNormalArray[totalAccounts++] = newAccount;
        }
        else
        {
            System.out.println("There is no space in the array!!!");
        }
    }

    void addAccount(Account newAccount) throws NoSuchElementException
    {
        if(!listOfAccounts.stream().anyMatch(p -> p.getAccountID() == newAccount.getAccountID()) &&
        this.totalAccounts < maxAccounts)
        {
            listOfAccounts.add(newAccount);
        }
        else {
            System.out.println("There is no space in the list!!!");
        }
    }

    public double totalAssets(){
        return listOfAccounts.stream().mapToDouble(Account::getBalance).sum();
    }
    public void addInterest() {
        for (Account account : listOfAccounts)
        {
            if(account instanceof InterestBearingAccount){
                InterestBearingAccount interestBearingAccount = (InterestBearingAccount) account;
                interestBearingAccount.addInterest();
            }
        }
    }
}
