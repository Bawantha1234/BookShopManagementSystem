package lk.ijse.BookShopManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BookShopManagementSystem.model.CustomerModel;
import lk.ijse.BookShopManagementSystem.tm.Customer;

import java.sql.SQLException;
import java.util.Optional;

public class AddCustomerController {
    public AnchorPane ancCustomer;
    public TextField txtId;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtContactNumber;
    public TextField txtAddress;
    public TextField txtSearch;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;
    public TableView tblBookDetails;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmContactNumber;
    public TableColumn clmEmail;
    public TableView tblCustomerDetails;

    public void initialize(){
        setCustomerTable();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            Customer search = CustomerModel.search(txtSearch.getText());
            if(search==null){
                new Alert(Alert.AlertType.ERROR,"Customer Not Found").show();
                return;
            }
            txtId.setText(search.getId());
            txtName.setText(search.getName());
            txtAddress.setText(search.getAddress());
            txtContactNumber.setText(search.getContactNumber());
            txtEmail.setText(search.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void AddOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(txtId.getText(),txtName.getText(),txtAddress.getText()
                ,txtContactNumber.getText(),txtEmail.getText());
        try {
            boolean save = CustomerModel.save(customer);
            if(save){
                new Alert(Alert.AlertType.INFORMATION,"Customer Added Success").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(txtId.getText(),txtName.getText(),txtAddress.getText()
                ,txtContactNumber.getText(),txtEmail.getText());

        try {
            boolean update = CustomerModel.update(customer);
            if(update){
                new Alert(Alert.AlertType.INFORMATION,"Customer Update Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer Update Fail").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(txtId.getText(),txtName.getText(),txtAddress.getText()
                ,txtContactNumber.getText(),txtEmail.getText());

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.WARNING, "Do You Want To Delete This" +
                " Customer From System",ButtonType.YES,ButtonType.NO).showAndWait();
        if(buttonType.get().getText().equalsIgnoreCase("no")){
            new Alert(Alert.AlertType.INFORMATION,"Customer Not Deleted").show();
            return;
        }
        try {
            boolean delete = CustomerModel.delete(customer);
            if(delete){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNumber.clear();
        txtEmail.clear();
    }

    public void setCustomerTable(){
        clmId.setCellValueFactory(new PropertyValueFactory<Customer,String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));
        clmContactNumber.setCellValueFactory(new PropertyValueFactory<Customer,String>("contactNumber"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<Customer,String>("email"));

        try {
            tblCustomerDetails.setItems(CustomerModel.getAllCustomer());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
