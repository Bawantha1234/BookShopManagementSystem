package lk.ijse.BookShopManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BookShopManagementSystem.db.DbConnection;
import lk.ijse.BookShopManagementSystem.model.SalesModel;
import lk.ijse.BookShopManagementSystem.tm.IncomeTo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class IncomeMonthlyController {
    public TableColumn clmDate;
    public TableColumn clmIncome;
    public TableView tblIncome;

    public void initialize(){
        setTable();
    }

    public void setTable(){
        clmDate.setCellValueFactory(new PropertyValueFactory<IncomeTo,String>("date"));
        clmIncome.setCellValueFactory(new PropertyValueFactory<IncomeTo,Double>("income"));
        try {
            tblIncome.setItems(SalesModel.getMonthlyIncome(LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        print();
    }

    public void print(){
        String billPath = "G:\\IJSE\\GDSE 63\\DBP\\FinalProject\\Kalana\\lk.ijse.BookShopManagementSystemUpdated_1\\lk.ijse.BookShopManagementSystem\\src\\lk\\ijse\\BookShopManagementSystem\\report\\BookShopIncomeDaily.jrxml";
        String sql = "select sum(income) as income,count(DISTINCT id) as order_count ,date ,Year(date) as year,Month(date) as month from sales group by date having month= "+LocalDate.now().getMonthValue()+" and year = "+LocalDate.now().getYear();
        JasperDesign jasdi = null;
        String savePath = "G:\\IJSE\\GDSE 63\\DBP\\FinalProject\\Kalana\\lk.ijse.BookShopManagementSystemUpdated_1\\lk.ijse.BookShopManagementSystem\\src\\lk\\ijse\\BookShopManagementSystem\\bills\\" + LocalDate.now().getYear() + LocalDate.now().getMonth().toString() + LocalDate.now().getDayOfMonth() + LocalTime.now().getHour() + LocalTime.now().getMinute() + LocalTime.now().getSecond() + ".pdf";
        HashMap<String,Object> hm = new HashMap<>();
        //hm.put("total",lblTotal.getText());
        try {
            jasdi = JRXmlLoader.load(billPath);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasdi.setQuery(newQuery);
            JasperReport js = JasperCompileManager.compileReport(jasdi);
            JasperPrint jp = JasperFillManager.fillReport(js, null, DbConnection.getInstance().getConnection());
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
}
