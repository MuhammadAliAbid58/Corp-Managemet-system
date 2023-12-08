package com.example.sdacorp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeModel {


    public void Employee_Homepage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Emp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);
        stage.setTitle("Employee Page");
        stage.setScene(scene);
        stage.show();

    }

    public void Employee_Update_Info_Page(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EmployeeUpdateInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);
        stage.setTitle("Employee Update Information Page");
        stage.setScene(scene);
        stage.show();

    }


    public void Employee_Report_a_Complaint_page(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EmployeeReportAComplain.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);
        stage.setTitle("Report Complain");
        stage.setScene(scene);
        stage.show();

    }


    public void Employee_Create_a_Form_page(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EmployeeCreateAForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);
        stage.setTitle("Create Form");
        stage.setScene(scene);
        stage.show();

    }

    public void Employee_Submit_a_Report_page(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EmployeeSubmitReport.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);
        stage.setTitle("Submit Report");
        stage.setScene(scene);
        stage.show();

    }


    public void Employee_Notification(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EmpNotification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);
        stage.setTitle("Employee Notification");
        stage.setScene(scene);
        stage.show();

    }









}
