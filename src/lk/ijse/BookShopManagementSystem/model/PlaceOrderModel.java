package lk.ijse.BookShopManagementSystem.model;

import lk.ijse.BookShopManagementSystem.db.DbConnection;
import lk.ijse.BookShopManagementSystem.tm.Order;

import java.sql.SQLException;

public class PlaceOrderModel {
    public static boolean placeOrder(Order ob) throws SQLException, ClassNotFoundException {
        DbConnection.getInstance().getConnection().setAutoCommit(false);
        try {
            boolean b = SalesModel.addSaleRecord(ob);
            if (b) {
                boolean b1 = SalesItemModel.addRecord(ob.getOb());
                if (b1) {
                    boolean b2 = ItemModel.updateQty(ob.getOb());
                    if (b2) {
                        DbConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DbConnection.getInstance().getConnection().rollback();
            return false;
        }catch (SQLException e){
            DbConnection.getInstance().getConnection().rollback();
            throw new SQLException();
        }catch (ClassNotFoundException e){
            DbConnection.getInstance().getConnection().rollback();
            throw new ClassNotFoundException();
        }finally {
            DbConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
