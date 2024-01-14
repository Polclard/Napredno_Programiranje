package Vezbi.tretaAuditoriskaVezbi.prvaZadaca;

public class PlatinumCheckingAccount extends InterestCheckingAccount implements InterestBearingAccount{
    public PlatinumCheckingAccount(String nameOfOwner, int accountID, float balance) {
        super(nameOfOwner, accountID, balance);
    }

    @Override
    public void addInterest() {
        this.addToBalance(getBalance() * INTEREST_RATE * 2);
    }
}
