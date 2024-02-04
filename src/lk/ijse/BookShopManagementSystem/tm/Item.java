package lk.ijse.BookShopManagementSystem.tm;

public class Item {
    private String id;
    private String category;
    private String name;
    private double price;
    private int qty;
    private String description;
    private String type;
    private double markPrice;

    public Item(String id, String category, String name, double price,double markPrice, int qty, String description,String type) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.description = description;
        this.type =type;
        this.markPrice=markPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(double markPrice) {
        this.markPrice = markPrice;
    }
}
