package lk.ijse.BookShopManagementSystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BookShopManagementSystem.tm.Employee;
import lk.ijse.BookShopManagementSystem.db.DbConnection;
import lk.ijse.BookShopManagementSystem.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeModel {
    public static boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Employee VALUES(?, ?, ?, ?, ?, ?)");
        pstm.setString(1, employee.getId());
        pstm.setString(2, employee.getName());
        pstm.setString(3, employee.getAddress());
        pstm.setString(4, employee.getNic());
        pstm.setString(5, employee.getContactNumber());
        pstm.setDouble(6, employee.getSalary());


        return pstm.executeUpdate() > 0;

    }

    public static Employee search(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT  * FROM  Employee WHERE id = ?");
        pstm.setObject(1, id);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
        }
        return null;
    }

    public static boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Employee SET name =?," +
                "address=? ,nic = ? , contact_number = ? , Salary = ? WHERE id = ?");
        pstm.setString(6, employee.getId());
        pstm.setString(1, employee.getName());
        pstm.setString(2, employee.getAddress());
        pstm.setString(3, employee.getNic());
        pstm.setString(4, employee.getContactNumber());
        pstm.setDouble(5, employee.getSalary());


        return 0<pstm.executeUpdate();
        /*if (pstm.executeUpdate() > 0) {
            new Alert(Alert.AlertType.INFORMATION, "Update Successfully !").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Try Again !");
        }
        */
    }

    public static boolean delete(Employee emp) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from Employee where id = ?",emp.getId());
    }

    public static ObservableList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from employee");
        ObservableList<Employee> list = FXCollections.observableArrayList();
        while ((rs.next())){
            list.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5), rs.getDouble(6)));
        }
        return list;
    }

}

