package lk.ijse.BookShopManagementSystem.controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BookShopManagementSystem.model.ItemModel;
import lk.ijse.BookShopManagementSystem.tm.BookTable;

import java.sql.SQLException;

public class AdminViewSellingBooksController {
    public TableView tblSellingBooks;
    public TableColumn clmDate;
   // public TableColumn clmOrderId;
    public TableColumn clmDescription;
    public TableColumn clmQty;
    public TableColumn clmPrice;
    public TableColumn clmTotal;
    public TableColumn clmName;

    public void initialize(){
        setTable();
    }

    public void setTable(){
        clmDate.setCellValueFactory(new PropertyValueFactory<BookTable,String>("date"));
        //clmOrderId.setCellValueFactory(new PropertyValueFactory<BookTable,String>("bookId"));
        clmName.setCellValueFactory(new PropertyValueFactory<BookTable,String>("name"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<BookTable,String>("description"));
        clmQty.setCellValueFactory(new PropertyValueFactory<BookTable,Integer>("qty"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<BookTable, Double>("price"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<BookTable, Double>("total"));
        try {
            tblSellingBooks.setItems(ItemModel.getThisMonthSeledAllItems("Book"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
