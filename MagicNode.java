public class MagicNode {
    String itemCode;
    String itemName;
    String category;
    double priceGalleon;
    int stockQuantity;
    MagicNode next;

    public MagicNode(String itemCode, String itemName, String category, double priceGalleon, int stockQuantity){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.priceGalleon = priceGalleon;
        this.stockQuantity = stockQuantity;
        this.next = null;
    }
}
