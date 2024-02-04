package lk.ijse.BookShopManagementSystem.controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BookShopManagementSystem.model.ItemModel;
import lk.ijse.BookShopManagementSystem.tm.Item;

import java.sql.SQLException;

public class BookStockController {
    public TableView tblBookStock;
    public TableColumn clmBookId;
    public TableColumn clmCategory;
    public TableColumn clmName;
    public TableColumn clmPrice;
    public TableColumn clmPrice1;
    public TableColumn clmQuantity;
    public TableColumn clmDescription;

    public void initialize(){
        setTable();
    }

    public void setTable(){
        clmBookId.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Item,String>("category"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
        clmPrice1.setCellValueFactory(new PropertyValueFactory<Item,Double>("markPrice"));
        clmQuantity.setCellValueFactory(new PropertyValueFactory<Item,Integer>("qty"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<Item,String >("description"));

        try {
            tblBookStock.setItems(ItemModel.getAllAvailableItems("Book"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //clm.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
    }
}
