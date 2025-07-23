package project2;

import project2.model.Product;
import project2.model.Shop;
import project2.service.ShopService;

import java.util.Scanner;

public class ShopSystem {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        Shop shop = new Shop();
        ShopService shopService = new ShopService(shop);
        int option;

        shopService.loadProducts();
        shopService.loadCustomers();
        shopService.loadSales();

        do{
            System.out.println(" ---- Shop System ---- ");
            System.out.println("1. Product Register");
            System.out.println("2. List Product");
            System.out.println("3. Customer Register");
            System.out.println("4. list Customer");
            System.out.println("5. Sales register");
            System.out.println("6. List Sales");
            System.out.println("0. leave");

            System.out.println("choose an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1 -> shopService.productRegister(shop, sc);
                case 2 -> shopService.listProducts(shop);
                case 3 -> shopService.customerRegister(shop, sc);
                case 4 -> shopService.listCustomers(shop);
                case 5 -> shopService.salesRegister(shop, sc);
                case 6 -> shopService.listSales(shop);
                case 0 -> {shopService.saveProducts();
                    shopService.saveCustomers();
                    shopService.saveSales();
                    System.out.println("Leaving the system... ");
                }
                default -> System.out.println("Invalid Option");
            }
        } while(option != 0);


        sc.close();
    }
}
