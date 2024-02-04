package lk.ijse.BookShopManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BookShopManagementSystem.model.ItemModel;
import lk.ijse.BookShopManagementSystem.tm.Item;

import java.sql.SQLException;
import java.util.Optional;

public class AdminManageSchoolItemsController {
    public AnchorPane ancItem;
    public TextField txtId;
    public TextField txtName;
    public TextField txtPrice;
    public TextField txtQuantity;
    public TextField txtDescription;
    public TextField txtSearch;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;
    public JFXComboBox<String> cmbCategory;
    public TableColumn clmId;
    public TableColumn clmCategory;
    public TableColumn clmName;
    public TableColumn clmPrice;
    public TableColumn clmQuantity;
    public TableColumn clmDescription;
    public TextField txtMarkPrice;
    public TableView tblItems;




    public void btnAddOnAction(ActionEvent actionEvent) {
        Item item = getItemDetail();
        try {
            if(ItemModel.addItem(item)){
                new Alert(Alert.AlertType.INFORMATION,"Item Added").show();
                setItemTable();
                clear();
            }else{

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Add Item Error - Driver Error").show();
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        Item item = getItemDetail();
        if(!isItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Update Failed\nTry Update Option Of Manage Book").show();
            return;
        }
        item=getItemDetail();
        try {
            if(ItemModel.updateItem(item)){
                new Alert(Alert.AlertType.INFORMATION,"Item Update Success").show();
                setItemTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        try {
            Item item1 = ItemModel.searchItem(txtId.getText());
            if(!isItem(item1)){
                new Alert(Alert.AlertType.INFORMATION,"Delete Failed\nTry Delete Option Of Manage Book").show();
                return;
            }
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.WARNING, "Do You Want To Delete \n" +
                    item1.getId() + " : " + item1.getName() + "\n" + "From The System", ButtonType.YES,
                    ButtonType.NO).showAndWait();
            if(buttonType.get().getText().equalsIgnoreCase("no")){
                new Alert(Alert.AlertType.INFORMATION,"Item Not Deleted\nCanceled By User").show();
                return;
            }

            if(ItemModel.deleteItem(item1)){
                clear();
                setItemTable();
                new Alert(Alert.AlertType.INFORMATION,"Delete Success").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Item Delete Failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) {
    }

    public void initialize(){
        setCategory();
        setItemTable();
    }
    public void setCategory(){
        cmbCategory.getItems().add("Business Stationary");
        cmbCategory.getItems().add("Desktop Instrument");
        cmbCategory.getItems().add("Drawing Stationary");
        cmbCategory.getItems().add("Business Stationary");
        cmbCategory.getItems().add("Ink and Toner");
        cmbCategory.getItems().add("Business Stationary");
        cmbCategory.getItems().add("Paper");
    }

    public Item getItemDetail(){
        String id = txtId.getText();
        String category = cmbCategory.getValue().toString();
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        double markPrice= Double.parseDouble(txtMarkPrice.getText());
        int qty = Integer.parseInt(txtQuantity.getText());
        String description = txtDescription.getText();

        return new Item(id,category,name,price,markPrice,qty,description,"Other");
    }

    public void clear(){
        txtSearch.clear();
        txtId.clear();
        cmbCategory.getSelectionModel().select(null);
        txtName.clear();
        txtPrice.clear();
        txtQuantity.clear();
        txtDescription.clear();
        txtMarkPrice.clear();
    }

    public boolean isItem(Item item){
        return item.getType().equalsIgnoreCase("other");
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            Item item = ItemModel.searchItem(txtSearch.getText());
            if(item!=null){
                if(!isItem(item)){
                    new Alert(Alert.AlertType.ERROR,"Try With Manage Book Search Option" +
                            "").show();
                    return;
                }
                setText(item);
            }else{
                new Alert(Alert.AlertType.ERROR,"Item Not Found").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setText(Item item){
        txtId.setText(item.getId());
        cmbCategory.getSelectionModel().select(item.getCategory());
        txtName.setText(item.getName());
        txtPrice.setText(String.valueOf(item.getPrice()));
        txtQuantity.setText(String.valueOf(item.getQty()));
        txtDescription.setText(item.getDescription());
        txtMarkPrice.setText(String.valueOf(item.getMarkPrice()));
    }
    public void setItemTable()  {
        clmId.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Item,String>("category"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
        clmQuantity.setCellValueFactory(new PropertyValueFactory<Item,Integer>("qty"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<Item,String >("description"));

        try {
            tblItems.setItems(ItemModel.getAllItems("Other"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
