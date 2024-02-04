package lk.ijse.BookShopManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public TextField txtUserName;
    public PasswordField psdPassword;
    public JFXButton btnLogin;
    public AnchorPane mainContext;
    public Label lblLoginText;


    public void psdPasswordOnAction(ActionEvent actionEvent) {
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnLogin) {
            String username = txtUserName.getText();
            String password = psdPassword.getText();
            if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("")) {
                lblLoginText.setText("login success");
                Stage window = (Stage) mainContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Admin.fxml"))));

                return;
            }
            if (username.equalsIgnoreCase("user") && password.equalsIgnoreCase("")) {
                Stage stage = (Stage) mainContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/User.fxml"))));
                return;

            }
            if
            (txtUserName.getText().isEmpty() && psdPassword.getText().isEmpty()) {
                lblLoginText.setText("Please enter your data.");
            } else {
                lblLoginText.setText("Wrong username or password!");
            }
        }
        txtUserName.clear();
        psdPassword.clear();
    }
}



