import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

// Transaction class with id, transactionDate, price and products to be used in the POS system
public class Transaction {
    // transaction attributes to define Transaction object
    UUID uuid;
    Date transactionDate;
    double price;
    ArrayList<String> products;

    // Transaction constructor
    public Transaction(UUID uuid, Date transactionDate, ArrayList<String> products, double price) {
        this.uuid = uuid;
        this.transactionDate = transactionDate;
        this.products = products;
        this.price = price;
    }

    // get all products
    public ArrayList<String> getProducts(){
        return this.products;
    }

    // get price of transaction
    public double getPrice(){
        return this.price;
    }

    // set price of transaction and round it to two decimal numbers
    public void setPrice(double price){
        BigDecimal bd = new BigDecimal(Double.toString(price));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.price = bd.doubleValue();
    }

    // toString method to print Transaction object
    @Override
    public String toString() {
        // format purchase time stamp
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
        String formattedDate = formatter.format(transactionDate);

        // format receipt for each transaction
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt from POS System\n");
        receipt.append("-----------------------\n");
        receipt.append("Transaction ID: ").append(uuid).append("\n");
        receipt.append("Transaction Date & Time: ").append(formattedDate).append("\n");
        receipt.append("Products:\n");

        // loop over products in ArrayList
        for (String product : products){
            receipt.append("- ").append(product).append("\n");
        }

        receipt.append("Total Price: €").append(price).append("\n");
        receipt.append("-----------------------\n");
        receipt.append("30 days warranty applied\n");
        receipt.append("Thank you for your purchase!\n");

        // return formated receipt
        return receipt.toString();
    }
}
