package project2.service;

import project2.util.FileUtilCurrentFile;
import project2.model.Customer;
import project2.model.Product;
import project2.model.Sale;
import project2.model.Shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopService {
    private static int nextIDProduct = 1;
    private static int nextIDCustumer = 1;
    private static int nextIDsale = 1;
    private Shop shop;

    public ShopService(Shop shop){
        this.shop = shop;
    }

    public void productRegister(Shop shop, Scanner sc){
        System.out.println(" ---- product Register ---- ");

        System.out.println("Product name: ");
        String name = sc.nextLine();

        System.out.println("Product price: ");
        double price = sc.nextDouble();

        System.out.println("quantity in stock: ");
        int qty = sc.nextInt();

        Product newProduct = new Product(nextIDProduct++, name, price, qty);
        shop.getProductList().add(newProduct);

        System.out.println("successful product register");
        System.out.println(newProduct);
    }
    public void listProducts(Shop shop){
        System.out.println(" ---- List Product ---- ");
        if (shop.getProductList().isEmpty()){
            System.out.println("No registered products");
        } else {
            shop.getProductList().forEach(System.out::println);
        }
    }

    public void customerRegister(Shop shop, Scanner sc){
        System.out.println(" ---- Customer Register ---- ");

        System.out.println("Name customer: ");
        String name = sc.nextLine();

        System.out.println("CPF customer: ");
        String cpf = sc.nextLine();

        Customer newCustomer = new Customer(nextIDCustumer++, name, cpf);
        shop.addCustomer(newCustomer);

        System.out.println("successful customer register");
        System.out.println(newCustomer);
    }
    public void listCustomers(Shop shop){
        System.out.println(" ---- List Customer ---- ");

        if (shop.getCustomerlist().isEmpty()){
            System.out.println("No registered customers");
        } else {
            shop.getCustomerlist().forEach(System.out::println);
        }
    }

    public void salesRegister(Shop shop, Scanner sc){
        System.out.println(" ---- Sales Register ---- ");
        if (shop.getCustomerlist().isEmpty() || shop.getProductList().isEmpty()){
            System.out.println("You must have at least one customer and one product registered");
            return;
        }

        System.out.println("Select customer by id: ");
        shop.getCustomerlist().forEach(c -> System.out.println(c.getId() + " - " + c.getName()));
        int idCustomer = sc.nextInt();
        sc.nextLine();

        Customer selectedCustomer = shop.getCustomerlist().stream().filter(c -> c.getId() == idCustomer).findFirst().orElse(null);

        if (selectedCustomer == null){
            System.out.println("Customer not found");
            return;
        }

        List<Product> selectedProducts = new ArrayList<>();
        double total = 0;

        String Continue;
        do {
            System.out.println("Select product by id: ");
            shop.getProductList().forEach(p -> System.out.println(p.getId() + " - " + p.getName() + " (Stock: " + p.getQty() + ")"));
            int idProduct = sc.nextInt();
            sc.nextLine();

            Product selectedProduct = shop.getProductList().stream().filter(p -> p.getId() == idProduct).findFirst().orElse(null);

            if (selectedProduct == null || selectedProduct.getQty() == 0){
                System.out.println("Product invalid or out of stock");
            } else {
                selectedProducts.add(selectedProduct);
                total += selectedProduct.getPrice();
                selectedProduct.setQuantityStock(selectedProduct.getQty() -1);
                System.out.println("Product added");
            }

            System.out.println("Add another product? (s/n): ");
            Continue = sc.nextLine();

        } while (Continue.equalsIgnoreCase("s"));
        if (selectedProducts.isEmpty()){
            System.out.println("No product added, sale canceled");
            return;
        }

        Sale newSale = new Sale(nextIDsale, selectedCustomer, selectedProducts, LocalDateTime.now(), total);
        shop.addSale(newSale);
        System.out.println("successful registered sales");
    }
    public void listSales(Shop shop){
        System.out.println(" ---- List Sales ---- ");
        if (shop.getSaleList().isEmpty()){
            System.out.println("No registered sales");
        } else {
            shop.getSaleList().forEach(System.out::println);
        }
    }

    public void saveProducts(){
        FileUtilCurrentFile.saveToFile(shop.getProductList(), "Products.dat");
    }
    public void loadProducts(){
        List<Product> loadedProducts = FileUtilCurrentFile.loadFromFile("Products.dat");
        shop.getProductList().clear();
        shop.getProductList().addAll(loadedProducts);
    }

    public void saveCustomers() {
        FileUtilCurrentFile.saveToFile(shop.getCustomerlist(), "Customers.dat");
    }
    public void loadCustomers() {
        List<Customer> loadedCustomers = FileUtilCurrentFile.loadFromFile("Customers.dat");
        shop.getCustomerlist().clear();
        shop.getCustomerlist().addAll(loadedCustomers);
    }

    public void saveSales() {
        FileUtilCurrentFile.saveToFile(shop.getSaleList(), "Sales.dat");
    }
    public void loadSales() {
        List<Sale> loadedSales = FileUtilCurrentFile.loadFromFile("Sales.dat");
        shop.getSaleList().clear();
        shop.getSaleList().addAll(loadedSales);
    }
}
