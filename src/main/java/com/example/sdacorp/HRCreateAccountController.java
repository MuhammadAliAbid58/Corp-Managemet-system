package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

public class HRCreateAccountController {

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

    @FXML
    private ComboBox<?> CreateFor;

    HRModel hrModel = new HRModel();


    ManagerMain.ManagerCRUD managerCRUD= new ManagerMain.ManagerCRUD();
    ManagerMain.Manager manager;

    HRMain.HRCRUD hrCRUD = new HRMain.HRCRUD();
    HRMain.HR hr;








    @FXML
    void OnClickCreateBtn(ActionEvent event) {


        if(username.getText().isBlank() || address.getText().isBlank() || contact.getText().isBlank() ||dob == null || email.getText().isBlank()
                || name.getText().isBlank() || password.getText().isBlank() )
        {
            setlabel_registration.setText("Fill all the fields");
            return;
        }
        else if(Objects.equals(CreateFor.getValue(), "Manager"))
        {
            create_manager();
        } else if (Objects.equals(CreateFor.getValue(), "HR"))
        {
            create_hr();
        } else {
            setlabel_registration.setText("Select an option");
        }



    }
    void create_hr(){
        String hr_name = name.getText();
        String hr_address = address.getText();
        String hr_contact = contact.getText();
        Date hr_dob = Date.valueOf(dob.getValue().toString());
        String hr_email = email.getText();
        String hr_username = username.getText();
        String hr_password = password.getText();

        hr = new HRMain.HR(hr_username, hr_password, hr_name,hr_dob, "1",1, hr_address, hr_contact, hr_email, true);
        try {
            hrCRUD.addHR(hr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setlabel_registration.setText("HR Created Successfully");
    }
    void create_manager(){
        String manager_name = name.getText();
        String manager_address = address.getText();
        String manager_contact = contact.getText();
        Date manager_dob = Date.valueOf(dob.getValue().toString());
        String manager_email = email.getText();
        String manager_username = username.getText();
        String manager_password = password.getText();

        manager = new ManagerMain.Manager(manager_username, manager_password, manager_name,manager_dob, "1", manager_address, manager_contact, manager_email, true);
        try {
            managerCRUD.addManager(manager);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setlabel_registration.setText("Manager Created Successfully");
    }

    @FXML
    void OnClickCreateAccount(ActionEvent event) throws IOException{

        hrModel.hr_CreateAccountPage(new Stage());
        close_stage();

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

    public void close_stage()
    {
        Stage closestage = (Stage) forclosing.getScene().getWindow();
        closestage.close();
    }







}
