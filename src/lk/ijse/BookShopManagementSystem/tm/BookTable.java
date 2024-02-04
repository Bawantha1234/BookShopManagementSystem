package lk.ijse.BookShopManagementSystem.tm;

public class BookTable {
    private String date;
    private String bookId;
    private String name;
    private String description;
    private int qty;
    private double price;
    private double total;



    public BookTable(String date, String bookId, String name, String description, int qty, double price, double total) {
        this.date = date;
        this.bookId = bookId;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
