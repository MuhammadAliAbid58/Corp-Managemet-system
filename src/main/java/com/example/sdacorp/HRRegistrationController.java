package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class HRRegistrationController {

    @FXML
    private Label forclosing;

    @FXML
    private TextField address;

    @FXML
    private TextField contact;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private Label setlabel_registration;

    @FXML
    private TextField username;

    HRModel hrModel = new HRModel();



    EmployeeMain.EmployeeCRUD employeeCRUD = new EmployeeMain.EmployeeCRUD();
    EmployeeMain.Employee employee;



    @FXML
    void onclickregisterbtn(ActionEvent event) {

        Date employee_dob = Date.valueOf(dob.getValue().toString());

        if(username.getText().isBlank() || address.getText().isBlank() || contact.getText().isBlank() ||dob == null || email.getText().isBlank()
        || name.getText().isBlank() || password.getText().isBlank() )
        {
            setlabel_registration.setText("Fill all the fields");
            return;
        } else {
            employee = new EmployeeMain.Employee(
                    username.getText(),
                    password.getText(),
                    name.getText(),
                    employee_dob,
                    address.getText(),
                    contact.getText(),
                    email.getText(),
                    false
                    );
            try {
                employeeCRUD.addEmployee(employee);
                setlabel_registration.setText("Employee Registered Successfully");
            } catch (SQLException e) {
                setlabel_registration.setText("Employee Registration Failed");
                e.printStackTrace();
            }
        }

    }

    @FXML
    void onclickrgistrationbtn(ActionEvent event) throws IOException {

        hrModel.hr_registrationpage(new Stage());
        close_stage();


    }

    @FXML
    void onclickupdateinfobtn(ActionEvent event) throws IOException {

        hrModel.hr_updateinfopage(new Stage());
        close_stage();


    }

    @FXML
    void OnClickHomeBtn(ActionEvent event)throws IOException {

        hrModel.hr_Homepage(new Stage());
        close_stage();

    }

    @FXML
    void OnClickCreateAccount(ActionEvent event) throws IOException{

        hrModel.hr_CreateAccountPage(new Stage());
        close_stage();

    }

    public void close_stage()
    {
        Stage closestage = (Stage) forclosing.getScene().getWindow();
        closestage.close();
    }







}
