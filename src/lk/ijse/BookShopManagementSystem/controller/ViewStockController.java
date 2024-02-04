package lk.ijse.BookShopManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ViewStockController {
    public JFXButton btnBooks;
    public JFXButton BtnItems;
    public AnchorPane ancStock;

    public void btnBooksOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/BookStock.fxml"));
        ancStock.getChildren().clear();
        ancStock.getChildren().add(parent);
    }

    public void BtnItemsOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemStock.fxml"));
        ancStock.getChildren().clear();
        ancStock.getChildren().add(parent);
    }
}
