import java.util.*;

public class Product{
    private UUID id;
    private String name;
    private double price;
    private int stock;
    private Category category;

    //Constructor
    public Product(String name,double price,int stock,Category category){
        setCategory(category);
        setName(name);
        setPrice(price);
        setStock(stock);
        this.id=UUID.randomUUID();
    }

    //Setters
    public void setName(String name ) throws InvalidName{
        if(name==null || name.isEmpty()){
            throw new InvalidName("Product name cannot empty!");
        }
        this.name=name;
    }
    public void setPrice(double price) throws InvalidPrice{
        if(price<0){
            throw  new InvalidPrice("Price must be greater than 0!");
        }
        this.price=price;
    }
    public void setStock(int stock) throws InvalidStock{
        if(stock<0){
            throw new InvalidStock("Not enough stock!");
        }
        this.stock=stock;
    }
    public void setCategory(Category category)throws InvalidCategory{
        if(category==null){
            throw new InvalidCategory("choose category for your product!");
        }
        this.category=category;
    }

    //Getters
    public Category getCategory() {
        return category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    //Methods

    public boolean isAvailable(int quantity){
           return  stock>=quantity;
    }
    public void reduceStock(int quantity)throws IndexOutOfStockException{
        if(quantity>stock){
            throw new IndexOutOfStockException("Not enough stocks!");
        }
        stock-=quantity;
    }
    public void addStock(int quantity) throws InvalidStock{
        if(quantity<=0){
            throw new InvalidStock("Quantity must be greater than 0!");
        }
        stock+=quantity;
    }
        public void info() {
            System.out.println("================================");
            System.out.println("          PRODUCT INFO           ");
            System.out.println("================================");
            System.out.println("ID       : " + id);
            System.out.println("Name     : " + name);
            System.out.printf("Price    : %.2f%n", price);
            System.out.println("Stock    : " + stock);
            System.out.println("Category : " + category);
            System.out.println("================================");
        }
}