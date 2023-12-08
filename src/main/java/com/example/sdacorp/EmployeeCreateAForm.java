package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeCreateAForm {

    @FXML
    private Label forclosing;

    @FXML
    private TextField form_description;

    @FXML
    private TextField form_type;


    @FXML
    private Label label;


    AccessID accessID=new AccessID();

    EmployeeModel employeeModel = new EmployeeModel();


    int Employee_id = accessID.getEmployeeId();


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
    @FXML
    void OnClcikSubmitBtn(ActionEvent event) {

        if(form_type.getText().isBlank() || form_description.getText().isBlank())
        {
            label.setText("Fill all the fields");
        }
        else {


            label.setText("Submitted");

        }







    }

}
