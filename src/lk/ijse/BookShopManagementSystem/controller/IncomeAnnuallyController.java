package lk.ijse.BookShopManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BookShopManagementSystem.db.DbConnection;
import lk.ijse.BookShopManagementSystem.model.SalesModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;

public class IncomeAnnuallyController {
    public AnchorPane ancAnnually;
    public BarChart chrtIncome;

    public void initialize(){
        setChart();
    }

    public void setChart(){
        try {
            HashMap hm = SalesModel.getAnnualyIncome(LocalDate.now().getYear());
            XYChart.Series series = new XYChart.Series();
            series.setName(String.valueOf(LocalDate.now().getYear()));
            for(int i = 1 ; i <=12 ; i++) {
                if (hm.get(i) == null) {
                    series.getData().add(new XYChart.Data<>(Month.of(i).toString(), 0));
                } else {
                    series.getData().add(new XYChart.Data<>(Month.of(i).toString(), Double.parseDouble(String.valueOf(hm.get(i)))));
                }
            }
            chrtIncome.getData().add(series);
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
        String billPath = "G:\\IJSE\\GDSE 63\\DBP\\FinalProject\\Kalana\\lk.ijse.BookShopManagementSystemUpdated_1\\lk.ijse.BookShopManagementSystem\\src\\lk\\ijse\\BookShopManagementSystem\\report\\BookShopIncome.jrxml";
        String sql = "SELECT sum(income) as income ,date,count(DISTINCT id) as order_count ,Year(date) as year,Month(date) as month from sales  group by month having  year = "+LocalDate.now().getYear();
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
