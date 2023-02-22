// Erik Brandt
//erbr6568
public class Bid {
    private int bidAmount;
    private User user;

    public Bid(User user, int bidAmount) {
        this.user = user;
        this.bidAmount = bidAmount;

    }

    public int getBidAmount() {
        return bidAmount;
    }

    public User getUser() {
        return user;
    }


    @Override
    public String toString() {
        return user.getName() + " " + getBidAmount() + " Kr";
    }
}
