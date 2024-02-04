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

public class AdminManageBooksController {
    public TableView tblBookDetails;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn description;
    public JFXComboBox<String> cmbCategory;
    public Button btnClear;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnAdd;
    public TextField txtDescription;
    public TextField txtQuantity;
    public TextField txtPrice;
    public TextField txtName;
    public TextField txtId;
    public AnchorPane ancBooks;
    public TextField txtSearch;
    public TextField txtMarkPrice;
    public TableColumn clmCategory;
    public TableColumn clmPrice;
    public TableColumn clmQty;
    public TableColumn clmDescription;

    public void UpdateOnAction(ActionEvent actionEvent) {

        try {
            Item item = ItemModel.searchItem(txtId.getText());
            if(!isBook(item)){

                new Alert(Alert.AlertType.ERROR,"Try With Manage School Item Update Option" +
                        "").show();
                return;
            }
            item=getBookDetail();
            if(ItemModel.updateItem(item)){
                setBookTable();
                new Alert(Alert.AlertType.INFORMATION,"Book Update Success").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setBookTable()  {
        clmId.setCellValueFactory(new PropertyValueFactory<Item,String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Item,String>("category"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
        clmQty.setCellValueFactory(new PropertyValueFactory<Item,Integer>("qty"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<Item,String >("description"));

        try {
            tblBookDetails.setItems(ItemModel.getAllItems("Book"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

        try {
            Item item1 = ItemModel.searchItem(txtSearch.getText());
            if(!isBook(item1)){
                new Alert(Alert.AlertType.ERROR,"Try With Manage School Item Delete Option" +
                        "").show();
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
                setBookTable();
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
        clear();
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

    public void AddOnAction(ActionEvent actionEvent) {
        Item item = getBookDetail();
        try {
            if(ItemModel.addItem(item)){
                new Alert(Alert.AlertType.INFORMATION,"Book Added").show();
                setBookTable();
                clear();
            }else{

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Add Book Error - Driver Error").show();
        }
    }

    public Item getBookDetail(){
        String id = txtId.getText();
        String category = cmbCategory.getValue().toString();
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        double markPrice = Double.parseDouble(txtMarkPrice.getText());
        int qty = Integer.parseInt(txtQuantity.getText());
        String description = txtDescription.getText();

        return new Item(id,category,name,price,markPrice,qty,description,"Book");
    }

    public void initialize(){
        setData();
        setBookTable();
    }

    public void setData(){
        cmbCategory.getItems().add("Short Stories");
        cmbCategory.getItems().add("Novels");
        cmbCategory.getItems().add("Stories");
        cmbCategory.getItems().add("Sinhala Literature");
        cmbCategory.getItems().add("English Literature");
        cmbCategory.getItems().add("History");
        cmbCategory.getItems().add("Dictionary");
        
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            Item item = ItemModel.searchItem(txtSearch.getText());
            if(item!=null){
                if(!isBook(item)){
                    new Alert(Alert.AlertType.ERROR,"Try With Manage School Item Search Option" +
                            "").show();
                    return;
                }
                setText(item);
            }else{
                new Alert(Alert.AlertType.ERROR,"Book Not Found");
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

    public boolean isBook(Item item){
        return item.getType().equalsIgnoreCase("book");
    }
}
