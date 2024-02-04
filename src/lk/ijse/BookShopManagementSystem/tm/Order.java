package lk.ijse.BookShopManagementSystem.tm;

import java.util.ArrayList;

public class Order {
    private String id;
    private String date;
    private String customerId;
    private double total;
    private ArrayList<OrderDetail> ob;
    private double income;


    public Order(String id, String date, String customerId, double total, ArrayList<OrderDetail> ob,double income) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.total = total;
        this.ob = ob;
        this.income = income;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<OrderDetail> getOb() {
        return ob;
    }

    public void setOb(ArrayList<OrderDetail> ob) {
        this.ob = ob;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
