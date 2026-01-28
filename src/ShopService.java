import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShopService  {
       public void purchaseProduct(User user,Product product,int quantity) {
           try {
               double totalPrice = product.getPrice() * quantity;
               if (!user.hasEnoughBalance(totalPrice)) {
                   throw new InsufficientBalanceException("Not enough balance!");
               }
               if (!product.isAvailable(quantity)) {
                   throw new IndexOutOfStockException("Not enough stock!");
               }

               user.deductBalance(totalPrice);
               product.reduceStock(quantity);
               Order order = new Order(product, quantity);
               user.addOrder(order);
               System.out.println("Purchase successful!");
               order.info();
           }catch (InsufficientBalanceException | IndexOutOfStockException e){
               System.out.println("Error :"+e.getMessage());
           }
       }

       public void viewAllProducts(List<Product> products){
           if(products.isEmpty()){
               System.out.println("Order is empty");
           }else {
               for(Product p:products){
                   p.info();
               }
           }
       }

    public List<Product> filterByPrice(List<Product> products,double minPrice,double maxPrice){
          return products.stream()
                   .filter(p->p.getPrice()>=minPrice && p.getPrice()<=maxPrice )
                   .collect(Collectors.toList());
    }
    public List<Product> filterByCategory(List<Product> products , Category category){
           return  products.stream()
                   .filter(p->p.getCategory()==category)
                   .collect(Collectors.toList());
    }
    public static void sortByPrice(List<Product> products ,boolean ascending){
           if(products==null || products.isEmpty()){
              return;
           }

           Comparator comparator=Comparator.comparingDouble(Product::getPrice);

           if(!ascending){
              comparator= comparator.reversed();
           }

            products.sort(comparator);
    }

    public void sortByName(List<Product> products){
           Comparator comparator=Comparator.comparing(Product::getName);

           products.sort(comparator);
    }
    public Optional<Product> searchByName(List<Product> products,String keyword){
          return products.stream()
                   .filter(p->p.getName().toLowerCase().equals(keyword.toLowerCase()))
                   .findFirst();
    }

    public void viewUserOrders(User user){
           if(user.getOrders().isEmpty()){
               System.out.println("Not orders");
           }else{
               for(Order o:user.getOrders()){
                  o.info();
               }
           }
    }
}

