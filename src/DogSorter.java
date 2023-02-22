// Erik Brandt
//erbr6568

import java.util.ArrayList;

public class DogSorter {


    public void sort(ArrayList<Dog> dogs) {

        for (int i = 0; i < dogs.size() - 1; i++) {
            Dog currentMin = dogs.get(i);
            int currentMinIndex = i;
            for (int j = i + 1; j < dogs.size(); j++) {
                if (currentMin.getTailLength() > dogs.get(j).getTailLength()) {
                    currentMin = dogs.get(j);
                    currentMinIndex = j;
                } else if (currentMin.getTailLength() == dogs.get(j).getTailLength()) {
                    if (currentMin.getName().compareTo(dogs.get(j).getName()) > 0) {
                        currentMin = dogs.get(j);
                        currentMinIndex = j;
                    }
                }
            }
            if (currentMinIndex != i) {
                dogs.set(currentMinIndex, dogs.get(i));
                dogs.set(i, currentMin);
            }
        }

    }
}
