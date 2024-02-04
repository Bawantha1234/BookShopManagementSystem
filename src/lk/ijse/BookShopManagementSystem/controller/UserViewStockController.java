package lk.ijse.BookShopManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserViewStockController {
    public JFXButton btnBooks;
    public JFXButton BtnItems;
    public AnchorPane ancStock;

    public void btnBooksOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/userBookStock.fxml"));
        ancStock.getChildren().clear();
        ancStock.getChildren().add(parent);
    }

    public void BtnItemsOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/userItemStock.fxml"));
        ancStock.getChildren().clear();
        ancStock.getChildren().add(parent);
    }
}
