import java.util.*;

public class Order{

    private UUID id;
    private Product product;
    private int quantity;
    private double totalPrice;

    //Constructor
    public Order(Product product,int quantity){
        this.product=product;
        this.quantity=quantity;
        this.totalPrice=product.getPrice()*quantity;
        this.id=UUID.randomUUID();
    }

    //Getters
    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void info() {
        System.out.println("================================");
        System.out.println("            ORDER INFO           ");
        System.out.println("================================");
        System.out.println("Order ID     : " + id);
        System.out.println("Product Name : " + product.getName());
        System.out.printf("Unit Price   : %.2f%n", product.getPrice());
        System.out.println("Quantity     : " + quantity);
        System.out.printf("Total Price  : %.2f%n", totalPrice);
        System.out.println("================================");
    }


}