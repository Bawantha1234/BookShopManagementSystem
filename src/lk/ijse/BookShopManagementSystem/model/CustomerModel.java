package lk.ijse.BookShopManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BookShopManagementSystem.db.DbConnection;
import lk.ijse.BookShopManagementSystem.tm.Customer;
import lk.ijse.BookShopManagementSystem.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getId(), customer.getName(), customer.getAddress(),
                customer.getContactNumber(),customer.getEmail());
    }

    public static boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET name =?," +
                "address=? , contact_number = ? , email = ? WHERE id = ?");
        pstm.setString(5, customer.getId());
        pstm.setString(1,customer.getName());
        pstm.setString(2, customer.getAddress());
        pstm.setString(3, customer.getContactNumber());
        pstm.setString(4, customer.getEmail());
        


        return 0<pstm.executeUpdate();
    }

    public static Customer search(String text) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT  * FROM  Customer WHERE id = ?");
        pstm.setObject(1,text);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Customer(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5));
        }
        return null;
    }

    public static boolean delete(Customer search) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Customer where id = ?",search.getId());
    }

    public static ObservableList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from customer");
        ObservableList<Customer> list = FXCollections.observableArrayList();
        while (rs.next()){
            list.add(new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return list;
    }

    public static ObservableList<Customer> getAllCustomer(String searchBy,String value) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from customer where "+searchBy+" like '%"+value+"%'");
        ObservableList<Customer> list = FXCollections.observableArrayList();
        while (rs.next()){
            list.add(new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return list;
    }

}
