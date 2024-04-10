/*
POS System for Hardware store - Java console application
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    // method to initialize the program
    public static void main(String[] args){
        Controller.preloadTheStock();
        welcomeMessage();
        menu();
    }

    // welcomeMessage method to welcome the customer
    public static void welcomeMessage(){
        // generate current date
        Date currentDate = new Date();
        // format date
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
        String strDate = formatter.format(currentDate);

        // format welcome message
        System.out.println("");
        System.out.println("|--------------------------------------------------|");
        System.out.println("|       WELCOME to Downtown Hardware Store         |");
        System.out.println("|--------------------------------------------------|\n");
        System.out.println("Today is: " + strDate + "\n");
        System.out.println("Please select one of the options below to continue: \n");
    }

    // method to check if user wants to continue or exit the program
    public static boolean continueProgram(){
        System.out.println("");
        System.out.println("Do you want to continue? (yes/no)");
        System.out.println("");
        String response = input.nextLine();

        return response.equalsIgnoreCase("yes");
    }

    // method to handle/generate the menu selection
    public static void menu(){
        int choice;
        boolean continueProgram;

        do {
            System.out.println("1. Display the available stock");
            System.out.println("2. Purchase an item");
            System.out.println("3. Exit & Payment");
            System.out.println("\n");

            while(!input.hasNext()){
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Product list: \n");
                    Controller.displayStock();
                    continueProgram = continueProgram();
                    break;
                case 2:
                    Controller.itemPurchase();
                    continueProgram = true;
                    break;
                case 3:
                    Controller.payment();
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    continueProgram = true;
            }
        }while(continueProgram);
    }
}
