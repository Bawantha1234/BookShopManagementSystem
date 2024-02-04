package lk.ijse.BookShopManagementSystem.model;

import lk.ijse.BookShopManagementSystem.tm.OrderDetail;
import lk.ijse.BookShopManagementSystem.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalesItemModel {
    public static boolean addRecord(ArrayList<OrderDetail> ob) throws SQLException, ClassNotFoundException {
        for (OrderDetail obj:ob){
            if(!addRecord(obj)){
                return false;
            }
        }
        return true;
    }

    private static boolean addRecord(OrderDetail ob) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into item_sales values(?,?,?)",ob.getOrderId(),
                ob.getCode(),ob.getQty());
    }
}
