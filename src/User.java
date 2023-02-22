// Erik Brandt
//erbr6568

import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private Dog[] ownedDogs = new Dog[0];
    private String name;


    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void addDog(Dog dog) {
        addListLength();
        ownedDogs[ownedDogs.length - 1] = dog;
    }

    public void addListLength() {
        this.ownedDogs = Arrays.copyOf(ownedDogs, ownedDogs.length + 1);
    }

    public void removeDogArray(ArrayList<Dog> listBucket) {

        for (int i = 0; i < ownedDogs.length; i++) {
            listBucket.add(ownedDogs[i]);
        }
    }

    public void removeDog(Dog dog) {


        Dog[] copyDogArray = new Dog[ownedDogs.length - 1];

        for (int i = 0; i < ownedDogs.length; i++) {
            for (int j = i; j < ownedDogs.length - 1; j++) {
                if (ownedDogs[j] == dog) {
                    for (int index = j; index < ownedDogs.length - 1; index++) {
                        ownedDogs[index] = ownedDogs[index + 1];
                        copyDogArray[j] = ownedDogs[i];
                    }
                }
                copyDogArray[j] = ownedDogs[i];
            }
        }
        ownedDogs = copyDogArray;



}

    public String toString() {
        String dogs = "";
        for (int i = 0; i < ownedDogs.length; i++) {
            dogs += ownedDogs[i].getName() + ", ";
        }
        if (dogs.equals("")) {
            return "name: " + name;
        }
        return "name: " + name + " owns " + dogs;
    }

}
