package lk.ijse.BookShopManagementSystem.controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BookShopManagementSystem.model.ItemModel;
import lk.ijse.BookShopManagementSystem.tm.BookTable;

import java.sql.SQLException;

public class AdminViewSellingItemsController {
    public TableView tblItems;
    public TableColumn clmDate;
    public TableColumn clmName;
    public TableColumn clmDescription;
    public TableColumn clmQty;
    public TableColumn clmPrice;
    public TableColumn clmSubTotal;

    public void initialize(){
        setTable();
    }

    public void setTable(){
        clmDate.setCellValueFactory(new PropertyValueFactory<BookTable,String>("date"));
        // clmOrderId.setCellValueFactory(new PropertyValueFactory<BookTable,String>("bookId"));
        clmName.setCellValueFactory(new PropertyValueFactory<BookTable,String>("name"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<BookTable,String>("description"));
        clmQty.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("qty"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<BookTable, Double>("price"));
        clmSubTotal.setCellValueFactory(new PropertyValueFactory<BookTable, Double>("total"));



        try {
            tblItems.setItems(ItemModel.getThisMonthSeledAllItems("Other"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
