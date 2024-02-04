package lk.ijse.BookShopManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {
    public JFXButton btnPlaceOrder;
    public JFXButton btnManageSellingBooks;
    public JFXButton btnManageSellingItems;
    public JFXButton btnManageStock;
    public JFXButton btnLogout;
    public AnchorPane ancUserContext;
    public JFXButton btnAddCustomer;

    public void btnPlaceOrder1OnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/PlaceOrder.fxml"));
        ancUserContext.getChildren().clear();
        ancUserContext.getChildren().add(parent);
    }

    public void btnManageSellingIBooksOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/UserManageSellingBooks.fxml"));
        ancUserContext.getChildren().clear();
        ancUserContext.getChildren().add(parent);
    }

    public void btnManageSellingItems1OnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/UserManageSellingItems.fxml"));
        ancUserContext.getChildren().clear();
        ancUserContext.getChildren().add(parent);
    }

    public void btnManageStock1OnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/UserViewStock.fxml"));
        ancUserContext.getChildren().clear();
        ancUserContext.getChildren().add(parent);
    }

    public void btnLogout1OnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) ancUserContext.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Main Window");
        stage.show();
    }

    public void AddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddCustomer.fxml"));
        ancUserContext.getChildren().clear();
        ancUserContext.getChildren().add(parent);
    }
}
