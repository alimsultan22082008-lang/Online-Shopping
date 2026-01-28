import java.util.*;
public class User{

    private UUID id;
    private String name;
    private double balance;
    private List<Order> orders;

    //Constructor
    public User(String name, double balance)throws InvalidBalance ,InvalidName{
        this.id=UUID.randomUUID();
        this.orders=new ArrayList<>();
        setName(name);
        setBalance(balance);
    }

    //Setters
    public void setName(String name) throws InvalidName{
        if(name==null || name.isEmpty()){
            throw new InvalidName("Please write your name!");
        }
        this.name=name;
    }
    public void setBalance(double balance) throws InvalidBalance{
        if(balance <0 ){
            throw new InvalidBalance("Balance cannot be smaller than 0!");
        }
        this.balance=balance;
    }

    //Getters
    public double getBalance() {
        return balance;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }


    //Methods

    public boolean hasEnoughBalance(double amount){
        return balance>=amount;
    }

    public void deductBalance(double amount) throws InsufficientBalanceException{
        if(amount>balance){
            throw new InsufficientBalanceException("Balance not enough!");
        }
        balance-=amount;
    }

    public void addBalance(double amount) throws InvalidBalance{
        if(amount<=0){
            throw new InvalidBalance("amount must be greater than 0!");
        }
        balance+=amount;
    }
    public void addOrder(Order order){
        orders.add(order);
    }

    public void info(){
        System.out.println("================================");
        System.out.println("        USER INFORMATION         ");
        System.out.println("================================");
        System.out.println("ID       : " + id);
        System.out.println("Name     : " + name);
        System.out.printf("Balance  : %.2f ₸%n", balance);
        System.out.println("Orders   : " + orders.size());
        System.out.println("================================");
    }

}


