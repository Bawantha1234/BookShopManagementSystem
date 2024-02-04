package lk.ijse.BookShopManagementSystem.model;

import lk.ijse.BookShopManagementSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginModel {
    public static ArrayList<String> loginDetails() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM login WHERE username ='?' AND password = '?'";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
            codeList.add(result.getString(2));
        }
        return codeList;
    }
}
