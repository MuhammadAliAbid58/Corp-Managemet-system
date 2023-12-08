package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class EmployeeUpdateInfoController implements Initializable {



    EmployeeModel employeeModel= new EmployeeModel();

    @FXML
    private TextField address;

    @FXML
    private TextField contact;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label updlabel;

    @FXML
    private TextField username;

    @FXML
    private Label forclosing;



    AccessID accessID=new AccessID();

    int Employee_id = accessID.getEmployeeId();



    EmployeeMain.EmployeeCRUD employeeCRUD = new EmployeeMain.EmployeeCRUD();
    EmployeeMain.Employee employee;






    @FXML
    void onclickupdatebtn() {


        employee = new EmployeeMain.Employee(
                Employee_id,
                username.getText(),
                password.getText(),
                employee.getName(),
                employee.getDob(),
                address.getText(),
                contact.getText(),
                email.getText(),
                true
        );
        try {
            employeeCRUD.updateEmployee(employee);
            updlabel.setText("UPDATED");
        } catch (SQLException e) {
            updlabel.setText("NOT UPDATED");
            e.printStackTrace();
        }

    }

    @FXML
    void ONClickReportAComplainBtn(ActionEvent event) throws IOException {

        employeeModel.Employee_Report_a_Complaint_page(new Stage());
        close_stage();

    }

    @FXML
    void OnClickCreateAFormBtn(ActionEvent event)throws IOException {

        employeeModel.Employee_Create_a_Form_page(new Stage());
        close_stage();

    }

    @FXML
    void OnClickHomeBtn(ActionEvent event) throws IOException {

        employeeModel.Employee_Homepage(new Stage());
        close_stage();

    }

    @FXML
    void OnClickUpdateInfoBtn(ActionEvent event) throws IOException {

        employeeModel.Employee_Update_Info_Page(new Stage());
        close_stage();

    }

    @FXML
    void OnclickSubmitReportBtn(ActionEvent event) throws IOException{
        employeeModel.Employee_Submit_a_Report_page(new Stage());
        close_stage();

    }
    @FXML
    void OnclickNotificationBtn(ActionEvent event) throws IOException{
        employeeModel.Employee_Notification(new Stage());

    }


    public void close_stage()
    {
        Stage closestage = (Stage) forclosing.getScene().getWindow();
        closestage.close();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setdata();
    }


    public void setdata() {
        try {

            employee = employeeCRUD.getEmployee(Employee_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String ph = employee.getContact();
        String eemail = employee.getEmail();
        String uname = employee.getUsername();
        String passw = employee.getPassword();
        String add=employee.getAddress();


        email.setText(eemail);
        address.setText(add);
        contact.setText(ph);
        username.setText(uname);
        password.setText(passw);

    }







}
