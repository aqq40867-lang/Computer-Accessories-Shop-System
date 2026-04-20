import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main application for the Computer Accessories Shop.
 * Features a hidden administrative interface accessed via a secret code.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Data Initialization
        List<Product> stock = new ArrayList<>();
        stock.add(new Mouse(112233, "Logitech", "black", ConnectivityType.WIRELESS, 25.50, ProductCategory.MOUSE, "gaming", 5));
        stock.add(new Keyboard(123456, "Corsair", "black", ConnectivityType.WIRED, 39.99, ProductCategory.KEYBOARD, "gaming", "UK"));
        stock.add(new Keyboard(123123, "Logitech", "black", ConnectivityType.WIRELESS, 32.00, ProductCategory.KEYBOARD, "flexible", "US"));
        stock.add(new Mouse(345345, "Advent", "grey", ConnectivityType.WIRED, 6.99, ProductCategory.MOUSE, "standard", 2));
        
        Address customerAddr1 = new Address("12", "LE11 3TU", "Loughborough");
        Customer customer1 = new Customer(101, "Jett", "Daniel", customerAddr1);
        
        Address adminAddr = new Address("15", "LE11 1QE", "Loughborough");
        Admin admin1 = new Admin(102, "admin1", "Jessica", adminAddr);
        
        // 2. Initial Login
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Welcome to the Computer Accessories Shop ---");
        System.out.print("Enter username to login: ");
        String inputUser = scanner.nextLine();
        
        User currentUser;
        if (inputUser.equalsIgnoreCase("admin1")) {
            currentUser = admin1;
            System.out.println("Welcome back, Jessica (Admin).");
        } else {
            currentUser = customer1;
            System.out.println("Welcome back, Daniel (Customer).");
        }
        
        // 3. Main Menu Loop
        boolean running = true;
        while (running) {
            System.out.println("\n========= Main Menu =========");
            System.out.println("1. View all products");
            System.out.println("2. Search for a product");
            System.out.println("3. Buy a product");
            System.out.println("4. Exit"); // Admin option is now hidden
            System.out.print("Select an option: ");
            
            String choice = scanner.nextLine();

            // Secret trigger for Admin Interface
            if (choice.equals("888")) {
                if (currentUser instanceof Admin) {
                    // Only loged-in Admins can use the secret code
                    addNewProduct(stock, scanner);
                } else {
                    // Logic: Even if a customer knows the code, they aren't an Admin
                    System.out.println("Unauthorized Access: You do not have administrator privileges.");
                }
                continue; // Return to the main menu after admin task
            }

            switch (choice) {
                case "1":
                    System.out.println("\n--- Current Stock List ---");
                    for (Product p : stock) System.out.println(p.toString());
                    break;

                case "2":
                    handleSearch(stock, scanner);
                    break;

                case "3":
                    handlePurchase(stock, scanner, currentUser);
                    break;
                    
                case "4":
                    System.out.println("Exiting system... Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please choose from the visible menu.");
            }
        }
        scanner.close();
    }

    /**
     * Hidden Administrative Method: Adding Stock
     */
    private static void addNewProduct(List<Product> stock, Scanner scanner) {
        try {
            System.out.println("\n[ADMIN SECURE INTERFACE]");
            System.out.print("Select type (1 for Keyboard, 2 for Mouse): ");
            String typeChoice = scanner.nextLine();

            System.out.print("Barcode: ");
            int barcode = Integer.parseInt(scanner.nextLine());
            System.out.print("Brand: ");
            String brand = scanner.nextLine();
            System.out.print("Color: ");
            String color = scanner.nextLine();
            System.out.print("Connectivity (WIRED/WIRELESS): ");
            ConnectivityType connectivity = ConnectivityType.valueOf(scanner.nextLine().toUpperCase());
            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            if (typeChoice.equals("1")) {
                System.out.print("Device Type: ");
                String devType = scanner.nextLine();
                System.out.print("Layout (UK/US): ");
                String layout = scanner.nextLine();
                stock.add(new Keyboard(barcode, brand, color, connectivity, price, ProductCategory.KEYBOARD, devType, layout));
            } else {
                System.out.print("Device Type: ");
                String devType = scanner.nextLine();
                System.out.print("Buttons: ");
                int buttons = Integer.parseInt(scanner.nextLine());
                stock.add(new Mouse(barcode, brand, color, connectivity, price, ProductCategory.MOUSE, devType, buttons));
            }
            System.out.println("SUCCESS: New product added to the warehouse.");
        } catch (Exception e) {
            System.out.println("FAILED: Incorrect data format. Operation aborted.");
        }
    }

    // --- Helper Methods for Clean Code ---
    private static void handleSearch(List<Product> stock, Scanner scanner) {
        System.out.print("Enter barcode to search: ");
        try {
            int barcode = Integer.parseInt(scanner.nextLine());
            for (Product p : stock) {
                if (p.getBarcode() == barcode) {
                    System.out.println("Found: " + p.toString());
                    return;
                }
            }
            System.out.println("Product not found.");
        } catch (Exception e) { System.out.println("Invalid barcode."); }
    }

    private static void handlePurchase(List<Product> stock, Scanner scanner, User user) {
        System.out.print("Enter barcode to buy: ");
        try {
            int barcode = Integer.parseInt(scanner.nextLine());
            Product selected = null;
            for (Product p : stock) if (p.getBarcode() == barcode) selected = p;

            if (selected == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.println("Selected: " + selected.getBrand() + " (£" + selected.getRetailPrice() + ")");
            System.out.println("Payment: [1] PayPal  [2] Credit Card");
            String payChoice = scanner.nextLine();
            
            PaymentMethod method = null;
            if (payChoice.equals("1")) {
                System.out.print("Email: ");
                method = new PayPal(scanner.nextLine());
            } else if (payChoice.equals("2")) {
                System.out.print("Card Number: ");
                String num = scanner.nextLine();
                System.out.print("CVV: ");
                method = new CreditCard(num, scanner.nextLine());
            }

            if (method != null) {
                System.out.println(method.processPayment(selected.getRetailPrice(), user.getAddress()).getMessage());
            }
        } catch (Exception e) { System.out.println("Transaction failed."); }
    }
}