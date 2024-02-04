package lk.ijse.BookShopManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BookShopManagementSystem.tm.IncomeTo;
import lk.ijse.BookShopManagementSystem.tm.Order;
import lk.ijse.BookShopManagementSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SalesModel {
    public static String getNewOrderId() throws SQLException, ClassNotFoundException {
        String lastOrderId = getLastOrderId();
        if(lastOrderId==null){
            return "S-00000001";
        }else {
            String[] split = lastOrderId.split("[S][-]");
            int lastValue = Integer.parseInt(split[1]);
            lastValue++;
            String newId = String.format("S-%08d", lastValue);
            return newId;
        }
    }

    private static String getLastOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select id from sales order by id desc limit" +
                " 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    public static boolean addSaleRecord(Order ob) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into sales values(?,?,?,?,?)", ob.getId(),
                ob.getTotal(), ob.getCustomerId(), ob.getDate(),ob.getIncome());

    }

    public static ObservableList<IncomeTo> getMonthlyIncome(int month,int year) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select sum(income) as income ,date ,Year(date) as year,Month(date) " +
                "as month from sales group by date having month=? and year = ?", month, year);
        ObservableList<IncomeTo> list = FXCollections.observableArrayList();
        while (rs.next()){
            list.add(new IncomeTo(rs.getString(2),rs.getDouble(1)));
        }
        return list;
    }

    public static HashMap getAnnualyIncome(int year) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT sum(income) as income ,date ,Year(date) as year,Month(date) as month from sales  group by month having  year = ? order by month", year);
        HashMap hm = new HashMap();
        while (rs.next()){
            hm.put(rs.getInt(4),rs.getDouble(1));
        }
        return hm;
    }
}
