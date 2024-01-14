package Vezbi.tretaAuditoriskaVezbi.prvaZadaca;

public class InterestCheckingAccount extends Account implements InterestBearingAccount {

    public static final double INTEREST_RATE = 0.03;

    public InterestCheckingAccount(String nameOfOwner, int accountID, float balance) {
        super(nameOfOwner, accountID, balance);
    }

    @Override
    public void addInterest() {
        addToBalance(INTEREST_RATE * this.getBalance());
    }
}
