package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController  implements Initializable {

    @FXML
    private Label emp_add;

    @FXML
    private Label emp_email;

    @FXML
    private Label emp_name;

    @FXML
    private Label emp_phone;

    @FXML
    private Label forclosing;


    EmployeeModel employeeModel = new EmployeeModel();


    AccessID accessID=new AccessID();

    int employee_id = accessID.getEmployeeId();



    EmployeeMain.EmployeeCRUD employeeCRUD = new EmployeeMain.EmployeeCRUD();
    EmployeeMain.Employee employee;





    @Override
    public void initialize(URL location, ResourceBundle resources)   {

        try {

            employee = employeeCRUD.getEmployee(employee_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String name = employee.getName();
        String address = employee.getAddress();
        String ph = employee.getContact();
        String email = employee.getEmail();

        emp_name.setText(name);
        emp_add.setText(address);
        emp_phone.setText(ph);
        emp_email.setText(email);


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
        close_stage();
    }

    public void close_stage()
    {
        Stage closestage = (Stage) forclosing.getScene().getWindow();
        closestage.close();
    }

}
