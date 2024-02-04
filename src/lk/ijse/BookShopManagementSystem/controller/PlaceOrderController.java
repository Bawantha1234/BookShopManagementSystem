package lk.ijse.BookShopManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lk.ijse.BookShopManagementSystem.db.DbConnection;
import lk.ijse.BookShopManagementSystem.model.CustomerModel;
import lk.ijse.BookShopManagementSystem.model.ItemModel;
import lk.ijse.BookShopManagementSystem.model.PlaceOrderModel;
import lk.ijse.BookShopManagementSystem.model.SalesModel;
import lk.ijse.BookShopManagementSystem.tm.Customer;
import lk.ijse.BookShopManagementSystem.tm.Item;
import lk.ijse.BookShopManagementSystem.tm.Order;
import lk.ijse.BookShopManagementSystem.tm.OrderDetail;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class PlaceOrderController {
    public TextField txtDescription;
    public JFXComboBox cmbCode;
    public TextField tctUnitprice;
    public TextField txtQtyOnHand;
    public TextField txtQty;
    public TextField tXtUnitprice;
    public TableColumn clmCode;
    public TableColumn clmDescription;
    public TableColumn clmQty;
    public TableColumn clmUnitPrice;
    public TableColumn clmTotal;
    public TableView<OrderDetail> tblPlaceOrder;
    public Label lblOrderdate;
    public Label lblOrderId;
    public Label lblTotal;
    public JFXComboBox cmbCutomerId;
    public TextField txtCusName;
    public TextField txtAddress;
    public TextField txtContactNumber;
    public TextField txtEmail;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colMobileNo;
    public TableView tblCustomer;
    private Item item;

    public void initialize(){
        clmCode.setCellValueFactory(new PropertyValueFactory<OrderDetail,String>("code"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<OrderDetail,String>("description"));
        clmQty.setCellValueFactory(new PropertyValueFactory<OrderDetail,Integer>("qty"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<OrderDetail,Double>("unitPrice"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<OrderDetail,Double>("subTotal"));
        setCustomerTable();
        setItemComboBox();
        setNewId();
        setCustomerComboBox();
        lblOrderdate.setText(String.valueOf(LocalDate.now()));

    }
    public void setCustomerTable(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<Customer, String>("contactNumber"));

        try {
            tblCustomer.setItems(CustomerModel.getAllCustomer());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCustomerComboBox(){
        cmbCutomerId.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                return ((Customer)object).getName();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            cmbCutomerId.setItems(CustomerModel.getAllCustomer());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void setItemComboBox(){
        cmbCode.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                return ((Item)object).getId();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
        try {
            cmbCode.setItems(ItemModel.getAllItems());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setNewId(){
        try {
            lblOrderId.setText(SalesModel.getNewOrderId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void txtContactNumberOnAction(ActionEvent actionEvent) {
        searchBy("contact_number",txtContactNumber.getText());
        ObservableList<Customer> items = cmbCutomerId.getItems();
        int count=0;
        for (Customer ob :items ){
            if(ob.getContactNumber().equals(txtContactNumber.getText())){
                cmbCutomerId.getSelectionModel().select(count);
                cmbCutomerIdOnAction(null);
            }
            count++;
        }

    }

    public void searchBy(String s,String value){
        try {
            tblCustomer.setItems(CustomerModel.getAllCustomer(s,value));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            OrderDetail orderDetail = new OrderDetail(lblOrderId.getText(),item.getId(), item.getDescription(), Integer.parseInt(txtQty.getText()), item.getMarkPrice(), item.getPrice());
            if (orderDetail.getQty() < 0) return;
            int i = item.getQty() - orderDetail.getQty();
            if (i < 0) {
                new Alert(Alert.AlertType.ERROR, "Out Of Stock").show();
                return;
            }
            item.setQty(item.getQty() - orderDetail.getQty());
            try {
                double total = Double.parseDouble(lblTotal.getText());
                lblTotal.setText(String.valueOf(total+ orderDetail.getSubTotal()));
            }catch(NumberFormatException ex){
                lblTotal.setText(String.valueOf(orderDetail.getSubTotal()));
            }

            ObservableList<OrderDetail> items = tblPlaceOrder.getItems();
            for (OrderDetail ob : items) {
                if (ob.getCode().equalsIgnoreCase(orderDetail.getCode())) {
                    ob.setQty(ob.getQty() + orderDetail.getQty());
                    tblPlaceOrder.refresh();
                    return;
                }
            }
            tblPlaceOrder.getItems().add(orderDetail);
            txtQty.clear();
            txtDescription.clear();
            tXtUnitprice.clear();
            txtQtyOnHand.clear();
            cmbCode.getSelectionModel().select(null);
            //cmbCodeOnAction(null);
        }catch(NumberFormatException ex){
            //System.out.println("dd");
        }
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        OrderDetail selectedItem = tblPlaceOrder.getSelectionModel().getSelectedItem();
        if(selectedItem==null){
            new Alert(Alert.AlertType.ERROR,"Select Item From Table to Remove").show();
            return;
        }
        ObservableList<Item> items = cmbCode.getItems();
        for(Item ob : items){
            if(ob.getId().equals(selectedItem.getCode())) {
                lblTotal.setText(String.valueOf(Integer.parseInt(lblTotal.getText())-selectedItem.getSubTotal()));
                ob.setQty(ob.getQty() + selectedItem.getQty());
            }
        }
        tblPlaceOrder.getItems().remove(tblPlaceOrder.getSelectionModel().getSelectedIndex());
    }

    public void cmbCodeOnAction(ActionEvent actionEvent) {
         item = (Item) cmbCode.getSelectionModel().getSelectedItem();
        if(item==null)return;
        txtDescription.setText(item.getDescription());
        tXtUnitprice.setText(String.valueOf(item.getMarkPrice()));
        txtQtyOnHand.setText(String.valueOf(item.getQty()));
    }

    public void btnPlaceOrderForm(ActionEvent actionEvent) {
        Customer c =(Customer) cmbCutomerId.getSelectionModel().getSelectedItem();
        if(c==null) {
            new Alert(Alert.AlertType.WARNING,"Select Customer ").show();
            return;
        }
        ObservableList<OrderDetail> items = tblPlaceOrder.getItems();
        ArrayList<OrderDetail> obj = new ArrayList<>();
        double income=0;
        for(OrderDetail ob : items){
            obj.add(ob);
            income = income+(ob.getSubTotal()-(ob.getQty()*ob.getPrice()));
        }
        Order order = new Order(lblOrderId.getText(),LocalDate.now().toString(),
                c.getId(),Double.parseDouble(lblTotal.getText()),
                obj,income);

        try {
            boolean b = PlaceOrderModel.placeOrder(order);
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Payment Done").show();
                clear();
                print();
                setNewId();
                tblPlaceOrder.getItems().clear();
                lblTotal.setText("");
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong .^.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong .^.").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong .^.").show();
        }
    }
    public void print(){
        String billPath = "H:\\Bawantha\\New Compressed (zipped) Folder\\lk.ijse.BookShopManagementSystemUpdated_1\\lk.ijse.BookShopManagementSystem\\src\\lk\\ijse\\BookShopManagementSystem\\report\\BookshopManagement.jrxml";
        String sql = "select its.sale_id,i.name as name ,its.qty as qty ,i.markPrice as price ,its.Qty*i.markPrice as subTotal from item_sales its inner join item i on its.item_id = i.id where its.sale_id = '"+lblOrderId.getText()+"'";
        JasperDesign jasdi = null;
        String savePath = "H:\\Bawantha\\New Compressed (zipped) Folder\\lk.ijse.BookShopManagementSystemUpdated_1\\lk.ijse.BookShopManagementSystem\\src\\lk\\ijse\\BookShopManagementSystem\\bills" + LocalDate.now().getYear() + LocalDate.now().getMonth().toString() + LocalDate.now().getDayOfMonth() + LocalTime.now().getHour() + LocalTime.now().getMinute() + LocalTime.now().getSecond() + ".pdf";
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("total",lblTotal.getText());
        try {
            jasdi = JRXmlLoader.load(billPath);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasdi.setQuery(newQuery);
            JasperReport js = JasperCompileManager.compileReport(jasdi);
            JasperPrint jp = JasperFillManager.fillReport(js, hm, DbConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jp, savePath);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.show();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void clear(){
        cmbCutomerId.getSelectionModel().select(null);
        cmbCode.getSelectionModel().select(null);
        txtAddress.clear();
        txtEmail.clear();
        txtCusName.clear();
        txtContactNumber.clear();
        txtDescription.clear();
        tXtUnitprice.clear();
        txtQtyOnHand.clear();
        txtQty.clear();

    }


    public void cmbCutomerIdOnAction(ActionEvent actionEvent) {
        Customer c =(Customer) cmbCutomerId.getSelectionModel().getSelectedItem();
        if(c==null)return;
        txtAddress.setText(c.getAddress());
        txtCusName.setText(c.getName());
        txtContactNumber.setText(c.getContactNumber());
        txtEmail.setText(c.getEmail());
        tblPlaceOrder.getItems().clear();
        tblPlaceOrder.refresh();

    }
}
