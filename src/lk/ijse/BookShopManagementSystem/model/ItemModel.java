package lk.ijse.BookShopManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BookShopManagementSystem.tm.BookTable;
import lk.ijse.BookShopManagementSystem.tm.Item;
import lk.ijse.BookShopManagementSystem.tm.OrderDetail;
import lk.ijse.BookShopManagementSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ItemModel {
    public static boolean addItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into item values(?,?,?,?,?,?,?,?)",
                item.getId(),item.getCategory(),item.getName(),item.getPrice(),
                item.getMarkPrice(),item.getQty(),item.getDescription(),item.getType());
    }
    public static boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update item set category = ? , name = ? ,price = ?," +
                        "markPrice = ? , qty=?,description = ?,type = ? where id = ?",
                item.getCategory(),item.getName(),item.getPrice(),item.getMarkPrice(),
                item.getQty(),item.getDescription(),item.getType(),item.getId());
    }
    public static boolean deleteItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from item where id = ?",item.getId());
    }
    public static Item searchItem(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs= CrudUtil.execute("SELECT * from item where id =?",id);
        if(rs.next()){
            Item item = new Item(rs.getString(1),rs.getString(2),rs.getString
                    (3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getString
                    (7), rs.getString(8));

            return item;
        }
        return null;
    }

    public static ObservableList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet rs= CrudUtil.execute("SELECT * from item ");
        ObservableList<Item> list = FXCollections.observableArrayList();
        while (rs.next()){
            Item item = new Item(rs.getString(1),rs.getString(2),rs.getString
                    (3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getString
                    (7), rs.getString(8));

            list.add(item);
        }
        return list;
    }
    public static ObservableList<Item> getAllItems(String by) throws SQLException, ClassNotFoundException {
        ResultSet rs= CrudUtil.execute("SELECT * from item where type = ?",by);
        ObservableList<Item> list = FXCollections.observableArrayList();
        while (rs.next()){
            Item item = new Item(rs.getString(1),rs.getString(2),rs.getString
                    (3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getString
                    (7), rs.getString(8));

            list.add(item);
        }
        return list;
    }

    public static boolean updateQty(ArrayList<OrderDetail> ob) throws SQLException, ClassNotFoundException {
        for (OrderDetail obj : ob){
            if(!updateQty(obj)){
                return false;
            }
        }
        return true;
    }

    public static boolean updateQty(OrderDetail ob) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update item set qty = qty-? where id = ?",ob.getQty(),ob.getCode());
    }

    public static ObservableList<BookTable> getThisMonthSeledAllItems(String type) throws SQLException, ClassNotFoundException {
        ResultSet rs= CrudUtil.execute("SELECT s.date,s.id,i.name,i.description,SUM(its.qty),i.price,Sum(its.qty)*i.price,Month(s.date)" +
                " as month,Year(s.date) as Year from item i inner join item_sales its on i.id = " +
                "its.item_id inner join sales s on its.sale_id = s.id where type = ? group by i.id,s.date having month = ? " +
                "and year = ?",type,LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        ObservableList<BookTable> list = FXCollections.observableArrayList();
        while (rs.next()){
            list.add(
            new BookTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)
            ,rs.getDouble(6),rs.getDouble(7))
            );

        }
        return list;
    }

    public static ObservableList<Item> getAllAvailableItems(String type) throws SQLException, ClassNotFoundException {
        ResultSet rs= CrudUtil.execute("SELECT * from item where type = '"+type+"' AND qty>0 ");
        ObservableList<Item> list = FXCollections.observableArrayList();
        while (rs.next()){
            Item item = new Item(rs.getString(1),rs.getString(2),rs.getString
                    (3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getString
                    (7), rs.getString(8));

            list.add(item);
        }
        return list;
    }
    public static ObservableList<Item> getAllAvailableItems() throws SQLException, ClassNotFoundException {
        ResultSet rs= CrudUtil.execute("SELECT * from item ");
        ObservableList<Item> list = FXCollections.observableArrayList();
        while (rs.next()){
            Item item = new Item(rs.getString(1),rs.getString(2),rs.getString
                    (3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getString
                    (7), rs.getString(8));

            list.add(item);
        }
        return list;
    }



}
