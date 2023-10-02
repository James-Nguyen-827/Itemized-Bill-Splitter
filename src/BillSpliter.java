import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BillSpliter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Double bill = 0.0;
        boolean valid = false;

        // Prompts user to enter in total price of of bill with no tip, stores input as a double
        while(!valid) {
            try {
                System.out.print("Total price of bill without tip and tax: " );
                bill = input.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Numbers only");
                input.next();
            }
        }

        // Prompts uses to enter tip amount
        System.out.print("Tip amount: ");
        Double tip = input.nextDouble();

        // Asks to if user wants to split the bill evenly 
        System.out.print("Split bill evenly? (y/n): ");
        String choice = input.next();

        // Ask how many people will split the bill 
        System.out.print("Enter in how many people to split bill: ");
        int numPeople = input.nextInt();

        // Will either split tip evenly or calculate how much each person will pay for their meal with tax
        if (choice.equals("y")){
            System.out.println("Everyone will each pay including tip " + (bill + tip) / numPeople);

        } else {
            // Ask user for tax rate of their area 
            System.out.print("What is the tax: ");
            Double tax = input.nextDouble() / bill;

            // Makes an arrayList that holds Diner class objects named myDiners
            ArrayList<Diner> myDiners = new ArrayList<>();

            // Loops for the amount of diners, and makes new Diner objects. calls set Diner method, and calcTotal method.
            for(int i = 0; i < numPeople; i++){
                myDiners.add(new Diner());
                myDiners.get(i).setDiner();
                myDiners.get(i).calcTotal(tax);
            }
            
            double total = 0;
            // Iterates through the array of diners, and prints out what they owe
            for (int i = 0; i < numPeople; i++){
                double roundOff = Math.round((myDiners.get(i).getTotal() + tip/numPeople) * 100.0) / 100.0;
                System.out.println("-------------------------------------------");
                System.out.println(myDiners.get(i).getName() + " owes: $" + roundOff);
                total += roundOff;
            }
            System.out.println("\nTotal bill: $" + Math.round((total) * 100.0) / 100.0);
        }
    }
}
// Diner class
class Diner {
    Scanner input = new Scanner(System.in);

    String name;
    Double total;
    
    // Default Constructor
    Diner(){
        name = "";
        total = 0.0;
    }

    // Parameterized Constructor
    Diner(String newName, Double newTotal){
        name = newName;
        total = newTotal;
    }

    // Set Diner method 
    void setDiner(){
        System.out.print("Name of person: ");
        name = input.next();
        System.out.println("Enter " + name + " items without tax (0 to finish): ");

        while(true) {
            double price = input.nextDouble();
            if (price == 0) {
                break;
            }
            total += price;
        }
    }

    // calcTotal method calculates how much they will pay + tax
    void calcTotal(Double tax){
        total = (total + (total * tax));
    }

    // Returns total
    Double getTotal(){
        return total;
    }

    // Returns name
    String getName(){
        return name;
    }
}
