package lk.ijse.BookShopManagementSystem.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminManageIncomeController {
    public AnchorPane ancIncomeContext;
    public JFXButton btnMonthly;
    public JFXButton btnAnnually;
    public AnchorPane ancSubIncome;

    public void btnMonthlyOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/IncomeMonthly.fxml"));
        ancSubIncome.getChildren().clear();
        ancSubIncome.getChildren().add(parent);
    }

    public void btnAnnuallyOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/IncomeAnually.fxml"));
        ancSubIncome.getChildren().clear();
        ancSubIncome.getChildren().add(parent);
    }
}
