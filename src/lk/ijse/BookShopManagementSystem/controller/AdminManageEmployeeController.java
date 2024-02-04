package lk.ijse.BookShopManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BookShopManagementSystem.model.EmployeeModel;
import lk.ijse.BookShopManagementSystem.tm.Employee;

import java.sql.SQLException;
import java.util.Optional;

public class AdminManageEmployeeController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtSalary;
    public TextField txtContactNumber;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;
    public JFXButton btnBack;
    public AnchorPane ancEmployeeContext;
    public TextField txtSearch;
    public JFXButton btnSearch;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmNic;
    public TableColumn clmContact;
    public TableColumn clmSalary;
    public TableView tblEmployee;

    public void initialize(){
        setEmployeeTable();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();
        String contactNumber = txtContactNumber.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        Employee employee = new Employee(id, name, address, nic, contactNumber, salary);
        try {
            boolean isAdded = EmployeeModel.save(employee);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added Successfully !").show();
                clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Recheck & Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }

    public void clear(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNumber.clear();
        txtNic.clear();
        txtSalary.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();
        String contactNumber = txtContactNumber.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        Employee employee = new Employee(id, name, address, nic, contactNumber, salary);

        try {
            if(EmployeeModel.update(employee)){
                new Alert(Alert.AlertType.INFORMATION, "Update Successfully !").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Try Again !").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, "Driver Error !").show();
        }


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Employee search = EmployeeModel.search(txtSearch.getText());
            if(search==null){
                new Alert(Alert.AlertType.ERROR,"Employee Not Found").show();
                return;
            }
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.WARNING, "Do You Want To Delete \n" +
                    search.getId() + " : " + search.getName() + " From The System", ButtonType.YES, ButtonType.NO).showAndWait();

            if(buttonType.get().getText().equalsIgnoreCase("no")){
                new Alert(Alert.AlertType.INFORMATION,"Employee Not Deleted").show();
                return;
            }
            if(EmployeeModel.delete(search)){
                new Alert(Alert.AlertType.INFORMATION,"Employee Delete Success").show();
                clear();
            }else{
                new Alert(Alert.AlertType.ERROR,"Employee Delete Fail").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();
        try {
            Employee employee = EmployeeModel.search(id);
            if(employee != null) {
                fillData(employee);
            } else {
                new Alert(Alert.AlertType.WARNING, "Employee Not Found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public void SearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();
        try {
            Employee employee = EmployeeModel.search(id);
            if(employee != null) {
                fillData(employee);
            } else {
                new Alert(Alert.AlertType.WARNING, "Employee Not Found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Employee employee) {
        txtNic.setText(employee.getId());
        txtName.setText(employee.getName());
        txtAddress.setText(employee.getAddress());
        txtNic.setText(employee.getNic());
        txtContactNumber.setText(employee.getContactNumber());
        txtSalary.setText(String.valueOf(employee.getSalary()));

    }

    public void setEmployeeTable(){
        clmId.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<Employee,String>("address"));
        clmNic.setCellValueFactory(new PropertyValueFactory<Employee,String>("nic"));
        clmContact.setCellValueFactory(new PropertyValueFactory<Employee,String>("contactNumber"));
        clmSalary.setCellValueFactory(new PropertyValueFactory<Employee,Double>("salary"));

        try {
            tblEmployee.setItems(EmployeeModel.getAllEmployee());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
