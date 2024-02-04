package lk.ijse.BookShopManagementSystem.tm;

public class IncomeTo {
    private String date;
    private double income;

    public IncomeTo(String date, double income) {
        this.date = date;
        this.income = income;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
