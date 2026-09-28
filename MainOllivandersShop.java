import java.util.Scanner;

public class MainOllivandersShop {
    public static void main(String[] args) {
        MagicLinkedList shopInventory = new MagicLinkedList();
        Scanner input = new Scanner(System.in);
        int choice = 0;

        shopInventory.addMagicItem("W01", "Elder Wand (Phoenix Feather)", "Wand", 35.0, 3);
        shopInventory.addMagicItem("P01", "Felix Felicis (Liquid Luck)", "Potion", 12.5, 5);
        shopInventory.addMagicItem("B01", "Nimbus 2000 Broomstick", "Broom", 50.0, 2);

        do{
            System.out.println("\n===========================================");
            System.out.println("  OLLIVANDERS DIAGON ALLEY CASHIER SYSTEM ");
            System.out.println("===========================================");
            System.out.println("1. Register New Magic Item (Create)");
            System.out.println("2. View Shop Inventory (Read)");
            System.out.println("3. Search Magic Item (Search)");
            System.out.println("4. Update Price & Stock (Update)");
            System.out.println("5. Remove Magic Item (Delete)");
            System.out.println("6. Process Customer Purchase");
            System.out.println("7. Exit System");
            System.out.print("Select Option (1-7): ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("[!] Numeric input required.");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Item Code (ex : W02)  : ");
                    String code = input.nextLine();
                    System.out.print("Enter Magic Item Name       : ");
                    String name = input.nextLine();
                    System.out.print("Enter Category (Wand/Potion): ");
                    String category = input.nextLine();
                    System.out.print("Enter Price (Galleons)      : ");
                    double price = input.nextDouble();
                    System.out.print("Enter Initial Stock Quantity: ");
                    int stock = input.nextInt();
                    shopInventory.addMagicItem(code, name, category, price, stock);
                    break;
                case 2:
                    shopInventory.displayInventory();
                    break;
                case 3:
                    System.out.print("Enter Item Code or Name to Search: ");
                    String searchQuery = input.nextLine(); 
                    MagicNode searchResult = shopInventory.searchMagicItem(searchQuery);
                    if (searchResult != null) {
                        System.out.println("\n[+] Magic Item Found:");
                        System.out.println("Item Code      : " + searchResult.itemCode);
                        System.out.println("Item Name      : " + searchResult.itemName);
                        System.out.println("Category       : " + searchResult.category);
                        System.out.println("Price          : " + searchResult.priceGalleon + " Galleons");
                        System.out.println("Available Stock: " + searchResult.stockQuantity);
                    } else {
                        System.out.println("[-] Magic item not found in inventory.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Item Code to Modify: ");
                    String updateCode = input.nextLine();
                    if (shopInventory.searchMagicItem(updateCode) != null) {
                        System.out.print("Enter Updated Price (Galleons): ");
                        double newPrice = input.nextDouble();
                        System.out.print("Enter Updated Stock Quantity  : ");
                        int newStock = input.nextInt();
                        if (shopInventory.updatePriceAndStock(updateCode, newPrice, newStock)) {
                            System.out.println(">> Item details updated successfully!");
                        }
                    } else {
                        System.out.println("[-] Specified Item Code does not exist.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Item Code to Remove: ");
                    String removeCode = input.nextLine();
                    if (shopInventory.deleteMagicItem(removeCode)) {
                        System.out.println(">> Item removed from Ollivanders inventory.");
                    } else {
                        System.out.println("[-] Specified Item Code does not exist.");
                    }
                    break;
                case 6:
                    System.out.print("Enter Item Code to Purchase: ");
                    String purchaseCode = input.nextLine();
                    MagicNode selectedItem = shopInventory.searchMagicItem(purchaseCode);
                    if (selectedItem != null) {
                        System.out.print("Enter Quantity to Buy: ");
                        int quantity = input.nextInt();
                        if (quantity <= selectedItem.stockQuantity) {
                            double totalCost = quantity * selectedItem.priceGalleon;
                            selectedItem.stockQuantity -= quantity;

                            System.out.println("\n-------------------------------------------");
                            System.out.println("      OLLIVANDERS SHOP RECEIPT OF SALE     ");
                            System.out.println("-------------------------------------------");
                            System.out.println("Magical Item : " + selectedItem.itemName);
                            System.out.println("Category     : " + selectedItem.category);
                            System.out.println("Quantity     : " + quantity);
                            System.out.println("Total Amount : " + totalCost + " Galleons");
                            System.out.println("Stock Remain : " + selectedItem.stockQuantity);
                            System.out.println("-------------------------------------------");
                        } else {
                            System.out.println("[-] Insufficient stock available for purchase.");
                        }
                    } else {
                        System.out.println("[-] Specified Item Code does not exist.");
                    }
                    break;

                case 7:
                    System.out.println("Mischief Managed! Closing Ollivanders Cashier System.");
                    break;

                default:
                    System.out.println("Invalid menu selection!");
            }

        } while (choice != 7);

        input.close();

    }
}
