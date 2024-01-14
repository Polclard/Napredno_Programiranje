package Vezbi.tretaAuditoriskaVezbi.prvaZadaca;

public class NotEnoughMoneyException extends Exception {

    String message;

    public NotEnoughMoneyException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public NotEnoughMoneyException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public NotEnoughMoneyException(String message) {
        this.message = message;
    }

    public NotEnoughMoneyException() {
        super("Message");
    }

    public NotEnoughMoneyException(double balance, double amount) {
        super("You canoot wihrdaw " + amount + ". You only have " + balance);
    }
}