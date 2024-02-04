package lk.ijse.BookShopManagementSystem.controller;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BookShopManagementSystem.model.ItemModel;
import lk.ijse.BookShopManagementSystem.tm.Item;

import java.sql.SQLException;

public class ItemStockController {
    public TableColumn clmBookId;
    public TableColumn clmCategory;
    public TableColumn clmName;
    public TableColumn clmPrice;
    public TableColumn clmMarkPrice;
    public TableColumn clmQuantity;
    public TableColumn clmDescription;
    public TableView tblBooks;

    public void initialize(){
        setTable();
    }

    public void setTable(){
        clmBookId.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Item,String>("category"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
        clmMarkPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("markPrice"));
        clmQuantity.setCellValueFactory(new PropertyValueFactory<Item,Integer>("qty"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<Item,String >("description"));

        try {
            tblBooks.setItems(ItemModel.getAllAvailableItems("Other"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //clm.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
    }
}
