import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Map<String, User> users = new HashMap<>();
    static List<Product> products = new ArrayList<>();
    static ShopService shopService = new ShopService();

    public static void main(String[] args) {
        initializeProducts();

        while (true) {
            showMainMenu();
        }
    }

    public static void showMainMenu() {
        System.out.println("\n===== ONLINE SHOP =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("=======================");
        System.out.print("Enter number: ");
        int n = input.nextInt();
        input.nextLine();

        switch (n) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice!");
        }
    }

    public static void registerUser() {
        try {
            System.out.print("Enter your name: ");
            String name = input.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = input.nextDouble();
            input.nextLine();

            User user = new User(name,balance);
            users.put(name, user);

            System.out.println("Registration successful!");
            user.info();

        } catch (InvalidName | InvalidBalance e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void loginUser() {
        System.out.print("Enter your name: ");
        String name = input.nextLine();

        Optional<User> userOpt = Optional.ofNullable(users.get(name));

        if (userOpt.isPresent()) {
            System.out.println("Login successful! Welcome, " + name);
            showUserMenu(userOpt.get());
        } else {
            System.out.println("User not found!");
        }
    }

    public static void showUserMenu(User user) {
        while (true) {
            System.out.println("\n===== USER MENU =====");
            System.out.println("Balance: $" + user.getBalance());
            System.out.println("1. View all products");
            System.out.println("2. Filter by price");
            System.out.println("3. Filter by category");
            System.out.println("4. Search product");
            System.out.println("5. Buy product");
            System.out.println("6. View my orders");
            System.out.println("7. Add balance");
            System.out.println("0. Logout");
            System.out.println("=====================");
            System.out.print("Enter number: ");
            int n = input.nextInt();
            input.nextLine();

            switch (n) {
                case 1:
                    shopService.viewAllProducts(products);
                    break;

                case 2:
                    System.out.print("Enter min price: ");
                    double min = input.nextDouble();
                    System.out.print("Enter max price: ");
                    double max = input.nextDouble();
                    input.nextLine();

                    List<Product> filtered = shopService.filterByPrice(products, min, max);
                    shopService.viewAllProducts(filtered);
                    break;

                case 3:
                    System.out.print("Enter category (FOOD/TECH/WARE): ");
                    String c = input.nextLine();

                    try {
                        Category category = Category.valueOf(c.toUpperCase());
                        List<Product> filteredCat = shopService.filterByCategory(products, category);
                        shopService.viewAllProducts(filteredCat);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category! Use: FOOD, TECH, or WARE");
                    }
                    break;

                case 4:
                    System.out.print("Enter product name: ");
                    String keyword = input.nextLine();
                    Optional<Product> result = shopService.searchByName(products, keyword);

                    if (result.isPresent()) {
                        result.get().info();
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter product name: ");
                    String name = input.nextLine();
                    Optional<Product> r = shopService.searchByName(products, name);

                    if (r.isPresent()) {
                        System.out.print("Enter quantity: ");
                        int quantity = input.nextInt();
                        input.nextLine();

                        shopService.purchaseProduct(user, r.get(), quantity);
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 6:
                    shopService.viewUserOrders(user);
                    break;

                case 7:
                    try {
                        System.out.print("Enter amount: ");
                        double amount = input.nextDouble();
                        input.nextLine();

                        user.addBalance(amount);
                        System.out.println("Balance added successfully!");
                        System.out.println("New balance: $" + user.getBalance());
                    } catch (InvalidBalance e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public static void initializeProducts() {
        try {
            products.add(new Product("Apple", 1.5, 100, Category.FOOD));
            products.add(new Product("Banana", 0.8, 150, Category.FOOD));
            products.add(new Product("Bread", 2.0, 50, Category.FOOD));
            products.add(new Product("Milk", 3.5, 80, Category.FOOD));

            products.add(new Product("Laptop", 999.99, 10, Category.TECH));
            products.add(new Product("Phone", 699.99, 20, Category.TECH));
            products.add(new Product("Headphones", 49.99, 30, Category.TECH));
            products.add(new Product("Mouse", 15.0, 40, Category.TECH));

            products.add(new Product("Plate", 12.0, 25, Category.WARE));
            products.add(new Product("Cup", 5.0, 60, Category.WARE));
            products.add(new Product("Spoon", 2.5, 100, Category.WARE));

            System.out.println("Products initialized successfully!");
        } catch (Exception e) {
            System.out.println("Error initializing products: " + e.getMessage());
        }
    }
}