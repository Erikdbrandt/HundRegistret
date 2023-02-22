
// Erik Brandt
//erbr6568
public class Dog {

    private static final double TAIL_LENGTH = 10;
    private static final double TAIL_LENGTH_TAX = 3.7;

    private String name;
    private String breed;
    private int age;
    private int weight;
    private User owner;
    private boolean owned;



    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;

    }


    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwned(boolean on) {
        owned = on;

    }


    public boolean getOwned() {
        return owned;
    }


    public void setOwner(User name) {
        this.owner = name;

    }




    public double getTailLength() {
        if (breed.equalsIgnoreCase("tax") || breed.equalsIgnoreCase("dachshund"))
            return TAIL_LENGTH_TAX;

        return age * (weight / TAIL_LENGTH);


    }

    public String toString() {

        if (!owned) {

            return "Name: " + name + " " + "breed: " + breed + " " + "age: " + age + " " + "Weight: " + weight + " " + "Tail length: " + getTailLength();
        }

        return "Name: " + name + " " + "breed: " + breed + " " + "age: " + age + " " + "Weight: " + weight + " " + "Tail length: " + getTailLength() + " owned by: " + owner.getName();
    }

    public void increaseAge() {
        age++;
    }


}
