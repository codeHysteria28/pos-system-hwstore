import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

// controller class that reacts to choices from console menu
public class Controller {
    static ArrayList<Product> products = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    // method to preload the products
    public static void preloadTheStock(){
        // read and load the items from "stock.txt" text file
        try(BufferedReader br = new BufferedReader(new FileReader("src/stock.txt"))){
            String line;

            // loop over file
            while((line = br.readLine()) != null){
                // split the line in two parts by comma
                String[] parts = line.split(",");

                if(parts.length >= 3){
                    // extract name and price from file
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    // add single product to Product object
                    products.add(new Product(id, name, price));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("There was a problem with reading a data from a file !");
        }
    }

    // display stock method: the main purpose is to display a products from txt file
    public static void displayStock(){
        // loop over products and display them
        for(Product product : products){
            System.out.println(product.toString());
        }
    }

    // method to check if customer would like to select multiple products
    public static boolean continuePurchase(){
        System.out.println("");
        System.out.println("Do you want to select another item ? (yes/no)");
        System.out.println("");
        String response = sc.nextLine();

        return response.equalsIgnoreCase("yes");
    }

    // method to allow user purchase an item/s from the stock list
    public static void itemPurchase(){
        // define variables
        int choice;
        boolean continueProgram;

        // allow to select multiple products
        do{
            // prompt user to select the product from menu
            System.out.println("Select an item from product list: \n");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline left-over

            // add the selected product to the transaction
            if(choice > 0 && choice <= products.size()){
                Product selectedProduct = products.get(choice - 1);
                // add selectedProduct to the transaction
                addProductToTransaction(selectedProduct);

                // display selected item
                System.out.println(selectedProduct);
            }else {
                System.out.println("Invalid choice. Please select a valid product.");
            }

            continueProgram = continuePurchase();
        }while(continueProgram);
    }

    // add single product to transaction
    public static void addProductToTransaction(Product selectedProduct){
        Transaction transaction;

        // check if there is an existing transaction
        if(transactions.isEmpty()){
            // Create a new transaction
            transaction = new Transaction(UUID.randomUUID(), new Date(), new ArrayList<>(), 0.0);
            transactions.add(transaction);
        }else {
            transaction = transactions.get(transactions.size() - 1);
        }

        // Add the product name to the products list in the transaction
        transaction.getProducts().add(selectedProduct.getProductName());

        // Update the total price of the transaction
        transaction.setPrice(transaction.getPrice() + selectedProduct.getProductPrice());
    }

    // method to finalize the payment
    public static void payment(){
        // check if there are any transactions
        if(!transactions.isEmpty()){
            // Get last transaction
            Transaction transaction = transactions.get(transactions.size() - 1);

            // write the transaction to txt file and append each new transaction in transaction txt file
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/transactions.txt", true))){
                bw.write(transaction.toString());
                bw.newLine(); // This is to ensure that each transaction is written on a new line
            }catch(Exception ex){
                ex.printStackTrace();
                System.out.println("There was a problem with writing a data to a file !");
            }

            // print the transaction
            System.out.println(transaction.toString());
        }else{
            System.out.println("No transactions to process");
        }
    }
}
