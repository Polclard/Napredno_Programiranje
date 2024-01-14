//package Lab1.Zadaca3;
//
//import java.util.Objects;
//
//public abstract class Transaction {
//    protected Long fromId;
//    protected Long toId;
//    protected String description;
//    protected String amount;
//
//    public Transaction(Long fromId, Long toId, String description, String amount) {
//        this.fromId = fromId;
//        this.toId = toId;
//        this.description = description;
//        this.amount = amount;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Transaction that = (Transaction) o;
//        return Objects.equals(fromId, that.fromId) && Objects.equals(toId, that.toId) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount);
//    }
//
//
//    public String getDescription() {
//        return description;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(fromId, toId, description, amount);
//    }
//
//    public String getAmount() {
//        return amount;
//    }
//
//    public Long getFromId() {
//        return fromId;
//    }
//
//    public Long getToId() {
//        return toId;
//    }
//}
