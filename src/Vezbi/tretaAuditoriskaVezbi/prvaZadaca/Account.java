package Vezbi.tretaAuditoriskaVezbi.prvaZadaca;

public abstract class Account {
    private String nameOfOwner;
    private int accountID;
    private static int idSeed = 0;
    private double balance;

    public Account(String nameOfOwner, int accountID, float balance) {
        this.nameOfOwner = nameOfOwner;
        this.accountID = accountID;
        this.balance = balance;
        this.accountID = idSeed++;
    }

    void addToBalance(double amount)
    {
        this.balance += amount;
    }

    void takeAwayFromBalance(double amount) throws NotEnoughMoneyException {
        if(this.balance < amount)
            throw new NotEnoughMoneyException(balance, amount);
        this.balance -= amount;
    }

    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNameOfOwner() {
        return nameOfOwner;
    }

    public int getAccountID() {
        return accountID;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return nameOfOwner + " with id " + accountID + " has $" + balance;
    }
}
