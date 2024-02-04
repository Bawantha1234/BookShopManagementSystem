package lk.ijse.BookShopManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {


    public AnchorPane ancAdminPane;
    public JFXButton btnBooks;
    public JFXButton btnEmployee;
    public JFXButton btnIncomeReports;
    public JFXButton btnLogOut;
    public JFXButton btnLogout;
    public JFXButton btnManagaeEmployee;
    public JFXButton btnManageBooks;
    public JFXButton btnManageSchoolItems;
    public JFXButton btnManageSellingBooks;
    public JFXButton btnManageSellingItems;
    public JFXButton btnManageStock;
    public JFXButton btnManageincomeReports;
    public JFXButton btnOrder;
    public JFXButton btnPlaceOrder;
    public JFXButton btnSchoolItem;
    public JFXButton btnSellingItems;

    public JFXButton btnStock;
    public JFXButton btnSellingBooks;
    public AnchorPane ancAdminContext;
    public JFXButton btnManageEmployee;
    public AnchorPane ancAdminSubContext;
    public JFXButton btnManageCustomer;


    public void btnLogout1OnAction(ActionEvent event) throws IOException {
        Stage window = (Stage) ancAdminSubContext.getScene().getWindow();
        window.close();
        Parent load = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Main Window");
        stage.show();

    }




    public void btnManageBooks1OnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminManageBooks.fxml"));
        ancAdminSubContext.getChildren().clear();
        ancAdminSubContext.getChildren().add(parent);
    }



    public void btnManageEmployee1OnAction(ActionEvent event) throws IOException {
       Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminManageEmployee.fxml"));
       ancAdminSubContext.getChildren().clear();
       ancAdminSubContext.getChildren().add(parent);

    }





    public void btnManageIncomeReport1OnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminManageIncome.fxml"));
        ancAdminSubContext.getChildren().clear();
        ancAdminSubContext.getChildren().add(parent);
    }





    public void btnManageSchoolItem1OnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminManageSchoolItems.fxml"));
        ancAdminSubContext.getChildren().clear();
        ancAdminSubContext.getChildren().add(parent);
    }





    public void btnManageSellingItems1OnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminViewSellingItems.fxml"));
        ancAdminSubContext.getChildren().clear();
        ancAdminSubContext.getChildren().add(parent);

    }




    public void btnManageStock1OnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ViewStock.fxml"));
        ancAdminSubContext.getChildren().clear();
        ancAdminSubContext.getChildren().add(parent);
    }




    public void btnPlaceOrder1OnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/PlaceOrder.fxml"));
        ancAdminSubContext.getChildren().clear();
        ancAdminSubContext.getChildren().add(parent);
    }




    public void btnManageSellingIBooksOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminViewSellingBooks.fxml"));
        ancAdminSubContext.getChildren().clear();
        ancAdminSubContext.getChildren().add(parent);
    }

    public void btnManageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddCustomer.fxml"));
        ancAdminSubContext.getChildren().clear();
        ancAdminSubContext.getChildren().add(parent);
    }
}
