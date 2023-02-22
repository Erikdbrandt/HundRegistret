// Erik Brandt
//erbr6568

import java.util.ArrayList;

public class Auction {
    private int auctionNumber;
    private Dog dog;
    private ArrayList<Bid> bidList = new ArrayList<>();


    public Auction(Dog dog, int auctionNumber) {
        this.dog = dog;
        this.auctionNumber = auctionNumber;
    }

    public Dog getDog() {
        return dog;
    }


    public void addBid(Bid bid) {
        Bid oldBid = null;

        for (Bid bids : bidList) {
            if (bids.getUser().equals(bid.getUser())) {
                oldBid = bids;
            }
        }
        bidList.remove(oldBid);


        bidList.add(0, bid);
    }

    public Bid getHighestBid() {
        if (bidList.isEmpty()) {
            return null;
        } else {
            return bidList.get(0);
        }
    }

    public String bidListTopThree() {
        String bidString = "";
        for (int i = 0; i <= 2 && i != bidList.size(); i++) {
            bidString += bidList.get(i).toString() + ", ";

        }
        if (bidString.equals("")) {
            return bidString;
        } else {
            return removeLastString(bidString);
        }

    }

    private String removeLastString(String str) {

        return str.substring(0, str.length() - 2);
    }

    public void removeUser(User user) {
        Bid removeBid = null;

        for (Bid bid : bidList) {
            if (bid.getUser().equals(user)) {
                removeBid = bid;
            }
        }
        bidList.remove(removeBid);

    }

    public boolean containsUser(User user) {
        boolean contains = false;
        for (Bid bid : bidList) {
            if (bid.getUser().equals(user)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    public String getBidList() {
        String stringOfBidlist = "";
        for (Bid bid : bidList) {
            stringOfBidlist += bid.toString() + "\n";
        }
        return stringOfBidlist;
    }


    @Override
    public String toString() {
        return "Auction #" + auctionNumber + ": " + dog.getName() + ". Top bids: " + "[" + bidListTopThree() + "]";
    }
}
