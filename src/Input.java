// Erik Brandt
//erbr6568

import java.util.Scanner;

public class Input {
    private Scanner input = new Scanner(System.in);



    public String readString(String prompt) {
        System.out.print(prompt);
        String string = input.nextLine().toLowerCase().trim();
        while (string.isEmpty()) {
            System.out.println("Error, cant be empty");
            System.out.print(prompt);
            string = input.nextLine().toLowerCase().trim();
        }
        return string;
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        int number = input.nextInt();
        input.nextLine();
        return number;

    }

    public double readDouble(String prompt) {
        System.out.print(prompt);
        double number = input.nextDouble();
        input.nextLine();
        return number;
    }
}