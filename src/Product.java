public class Product {
    private int productID;
    private String productName;
    private double productPrice;

    Product(int productID, String productName, double productPrice){
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    // get productPrice
    public double getProductPrice(){
        return this.productPrice;
    }

    // get productName
    public String getProductName(){
        return this.productName;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(productID).append(" | Name: ").append(productName).append(" | Price: €").append(productPrice);
        sb.append("\n--------------------------------------------");
        return sb.toString();
    }
}
