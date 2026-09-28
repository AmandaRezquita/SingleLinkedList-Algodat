public class MagicLinkedList {
    private MagicNode head;

    public MagicLinkedList() {
        this.head = null;
    }

    public void addMagicItem(String code, String name, String category, double price, int stock) {
        MagicNode newNode = new MagicNode(code, name, category, price, stock);
        if (head == null) {
            head = newNode;
        } else {
            MagicNode pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = newNode;
        }
        System.out.println(">> Magical Item " + name + " (" + code + ") successfully added to shop inventory!");
    }

    public void displayInventory() {
        if (head == null) {
            System.out.println("\n[!] Ollivanders inventory database is empty.");
            return;
        }

        System.out.println("\n=========================================================================================");
        System.out.printf("| %-8s | %-25s | %-15s | %-15s | %-8s |\n", "CODE", "ITEM NAME", "CATEGORY", "PRICE (Galleon)", "STOCK");
        System.out.println("=========================================================================================");
        
        MagicNode pointer = head;
        while (pointer != null) {
            System.out.printf("| %-8s | %-25s | %-15s | %-15.2f | %-8d |\n", 
                              pointer.itemCode, pointer.itemName, pointer.category, pointer.priceGalleon, pointer.stockQuantity);
            pointer = pointer.next;
        }
        System.out.println("=========================================================================================");
    }

    public MagicNode searchMagicItem(String query) {
        MagicNode pointer = head;
        while (pointer != null) {
            if (pointer.itemCode.equalsIgnoreCase(query) || pointer.itemName.equalsIgnoreCase(query)) {
                return pointer;
            }
            pointer = pointer.next;
        }
        return null;
    }

    public boolean updatePriceAndStock(String code, double newPrice, int newStock) {
        MagicNode item = searchMagicItem(code);
        if (item != null) {
            item.priceGalleon = newPrice;
            item.stockQuantity = newStock;
            return true;
        }
        return false;
    }

    public boolean deleteMagicItem(String code) {
        if (head == null) return false;

        if (head.itemCode.equalsIgnoreCase(code)) {
            head = head.next;
            return true;
        }

        MagicNode pointer = head;
        while (pointer.next != null && !pointer.next.itemCode.equalsIgnoreCase(code)) {
            pointer = pointer.next;
        }

        if (pointer.next != null) {
            pointer.next = pointer.next.next;
            return true;
        }
        return false;
    }
}
