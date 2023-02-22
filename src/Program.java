import java.util.ArrayList;

// Erik Brandt
//erbr6568
public class Program {
    private Input input = new Input();
    private ArrayList<Dog> dogList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Auction> auctionList = new ArrayList<>();
    private DogSorter dogSorter = new DogSorter();
    private int auctionNumber;


    private String readCommand() {
        return input.readString("vilket kommando?> ");

    }


    private void handleCommand(String command) {
        switch (command) {
            case "rd":
            case "register new dog":
                registerNewDog();
                break;
            case "increase age":
                increaseAgecommand();
                break;
            case "list dogs":
                listDogs();
                break;
            case "remove dog":
                removeDog();
                break;
            case "exit":
                System.out.println("du gav kommandot: exit");
                break;
            case "ru":
            case "register new user":
                registerNewUser();
                break;
            case "list users":
                listUsers();
                break;
            case "give dog":
                giveDog();
                break;
            case "remove user":
                removeUser();
                break;
            case "a":
            case "start auction":
                startAuction();
                break;
            case "b":
            case "make bid":
                makeBid();
                break;
            case "la":
            case "list auctions":
                listAuctions();
                break;
            case "test":
               testHandler();
               break;
            case "list bids":
            case "lb":
                listBids();
                break;
            case "close auction":
            case "ca":
                closeAuction();
                break;


            default:
                System.out.println("error: inget sådant kommando");

        }
    }

    private void closeAuction() {
        System.out.println("Du gav kommandot: Close auction");

        String dogName = input.readString("Enter the name of the dog?> ");

        Dog dog = findDog(dogName);
        if (dog == null) {
            System.out.println("Error, no such dog");
            return;
        }
        Auction auction = findAuction(dog);

        if (auction == null) {
            System.out.println("Error, this dog is not up for auction");
            return;
        }

        if (auction.getBidList().equals("")) {
            System.out.println("The auction is closed. No bids where made for " + auction.getDog().getName());
            return;
        }

        System.out.println("The auction is closed. The winning bid was " + auction.getHighestBid().getBidAmount() + "kr and was made by " + auction.getHighestBid().getUser().getName());


        User user = auction.getHighestBid().getUser();
        user.addDog(dog);
        dog.setOwner(user);
        dog.setOwned(true);


        auctionList.remove(auction);

    }

    private void listBids() {
        System.out.println("Du gav kommandot: List bids");

        String dogName = input.readString("Enter the name of the dog?> ");

        Dog dog = findDog(dogName);
        if (dog == null) {
            System.out.println("Error, no such dog");
            return;
        }

        Auction auction = findAuction(dog);

        if (auction == null) {
            System.out.println("Error, this dog is not up for auction");
            return;
        }

        if (auction.getBidList().equals("")) {
            System.out.println("No bid registred yet for this auction");
            return;
        }
        System.out.println("Here are the bids for this auction:");
        System.out.println(auction.getBidList());


    }

    private void listAuctions() {

        if (auctionList.isEmpty()) {
            System.out.println("Error: no auctions in progress");
            return;
        }

        for (Auction auction : auctionList) {
            System.out.println(auction.toString());
        }


    }

    private void makeBid() {
        System.out.println("du gav kommandot: make bid");

        String ownerName = input.readString("Enter the name of the owner?> ");

        User user = findUser(ownerName);
        if (user == null) {
            System.out.println("Error, no such user");
            return;
        }

        String dogName = input.readString("Enter the name of the dog?> ");
        Dog dog = findDog(dogName);
        Auction auction = findAuction(dog);

        if (auction == null) {
            System.out.println("Error, dog is not in auction");
            return;
        }

        int bidAmount;
        if (auction.getHighestBid() == null) {
            bidAmount = input.readInt("Amount to bid (min1)?>");

        } else {
            bidAmount = getBidAmount(auction);
        }

        System.out.println("Bid made");

        Bid bid = new Bid(user, bidAmount);
        auction.addBid(bid);


    }

    private int getBidAmount(Auction auction) {
        int bidAmount;
        int highestbid = auction.getHighestBid().getBidAmount() + 1;
        bidAmount = input.readInt("Amount to bid (min" + highestbid + ") ?>");

        while (bidAmount <= auction.getHighestBid().getBidAmount()) {
            System.out.println("Error, bid higher!");
            bidAmount = input.readInt("Amount to bid (min" + highestbid + ") ?>");
        }
        return bidAmount;
    }


    private void startAuction() {
        System.out.println("du gav kommandot: Start auction");

        String dogName = input.readString("Enter the name of the dog?> ");

        Dog dog = findDog(dogName);
        if (dog == null) {
            System.out.println("Error, no such dog");
            return;
        }
        if (dog.getOwner() != null) {
            System.out.println("Error, dog has owner already");
            return;
        }

        Auction a = findAuction(dog);

        if (a != null) {
            System.out.println("Error, dog already in auction");
            return;
        }


        Auction auction = new Auction(dog, ++auctionNumber);
        auctionList.add(auction);
        System.out.println(dog.getName() + " has been put up for auction in auction:" + auctionNumber);


    }

    private void removeDog() {
        System.out.println("du gav kommandot: remove dog");
        String dogName = input.readString("Enter name of dog?>");
        Dog dog = findDog(dogName);
        if (dog == null) {
            System.out.println("Error, no such dog");
            return;
        }

        if (dog.getOwner() != null) {
            User user = dog.getOwner();
            user.removeDog(dog);
        }

        Auction auctionOfDog = null;
        for (Auction auction : auctionList) {
            if (auction.getDog().equals(dog)) {
                auctionOfDog = auction;
            }
        }
        auctionList.remove(auctionOfDog);


        dogList.remove(dog);
        System.out.println(dog.getName() + " is now removed");


    }

    private void removeUser() {
        System.out.println("du gav kommandot: remove user");
        String userName = input.readString("Enter name of user?>");
        User user = findUser(userName);
        if (user == null) {
            System.out.println("Error, no such user");
            return;
        }
        userList.remove(user);

        ArrayList<Dog> dogsRemove = new ArrayList<>();
        user.removeDogArray(dogsRemove);
        dogList.removeAll(dogsRemove);


        for (Auction auction : auctionList) {
            if (auction.containsUser(user)){
                auction.removeUser(user);
            }
        }
        System.out.println(user.getName() + " has been removed");

    }

    private void giveDog() {
        System.out.println("du gav kommandot: give dog");

        String dogName = input.readString("Enter the name of the dog?>");

        Dog dog = findDog(dogName);
        if (dog == null) {
            System.out.println("Error, no such dog");
            return;
        }
        if (dog.getOwned()) {
            System.out.println("Error, dog has owner already");
            return;
        }

        String ownerName = input.readString("Enter the name of the owner?>");

        User user = findUser(ownerName);
        if (user == null) {
            System.out.println("Error, no such user");
            return;
        }


        int i = dogList.indexOf(dog);

        user.addDog(dogList.get(i));
        dog.setOwned(true);
        dog.setOwner(user);

        System.out.println(user.getName() + " now owns " + dog.getName());


    }

    private void listUsers() {
        System.out.println("du gav kommandot: list users);");

        if (userList.size() != 0) {
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i).toString());
            }

        } else {
            System.out.println("error, no user in register");
        }
    }

    private void testHandler() {
        User user = new User("Erik");
        User user1 = new User("Hanna");
        User user2 = new User("Kalle");
        User user3 = new User("Felix");

        Dog dog = new Dog("Fido", "tax", 4, 6);
        Dog dog1 = new Dog("Molle", "Labrador", 24, 4);


        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        dogList.add(dog);
        dogList.add(dog1);

    }

    private void registerNewUser() {
        System.out.println("du gav kommandot: register new user");
        String name = input.readString("name?>");
        User user = new User(name);
        userList.add(user);
        System.out.println(user.getName() + " added to register");
    }

    private void increaseAgecommand() {
        System.out.println("du gav kommandot: increase age");

        String dogName = input.readString("Enter name of dog?>");
        Dog dog = findDog(dogName);
        if (dog == null) {
            System.out.println("Error, no such dog");
            return;
        }
        dog.increaseAge();
        System.out.println(dog.getName() + " is now one year older");
        dogSorter.sort(dogList);
    }

    private Dog findDog(String dogName) {
        for (int i = 0; i < dogList.size(); i++) {
            if (dogList.get(i).getName().equalsIgnoreCase(dogName)) {
                return dogList.get(i);
            }
        }
        return null;
    }

    private Auction findAuction(Dog dog) {
        for (int i = 0; i < auctionList.size(); i++) {
            if (auctionList.get(i).getDog() == dog) {
                return auctionList.get(i);
            }
        }
        return null;
    }

    private User findUser(String ownerName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equalsIgnoreCase(ownerName)) {
                return userList.get(i);
            }
        }
        return null;
    }

    private void listDogs() {
        if (dogList.size() != 0) {
            System.out.println("du gav kommandot: List dogs");
            double askTail = input.readDouble("Smallest tail length to display?>");
            for (int i = 0; i < dogList.size(); i++) {
                if (dogList.get(i).getTailLength() >= askTail) {
                    System.out.println(dogList.get(i));
                }
            }
        } else {
            System.out.println("Error, no dogs in register");
        }

    }

    private void registerNewDog() {
        System.out.println("du gav kommandot: register new dog");
        String name = input.readString("name?>");
        String breed = input.readString("breed?>");
        int age = input.readInt("age?>");
        int weight = input.readInt("weight?>");
        Dog dog = new Dog(name, breed, age, weight);
        dogList.add(dog);
        dogSorter.sort(dogList);
    }

    private void handleCommandLoop() {
        String command;
        do {

            command = readCommand();
            handleCommand(command);

        } while (!command.equals("exit"));
    }


    private void start() {
        initialize();
        handleCommandLoop();
        closeDown();


    }

    private void initialize() {
        System.out.println("hej och välkommen!");

    }

    private void closeDown() {
        System.out.println("hejdå!");
    }


    public static void main(String[] args) {

        Program program = new Program();
        program.start();
    }


}
