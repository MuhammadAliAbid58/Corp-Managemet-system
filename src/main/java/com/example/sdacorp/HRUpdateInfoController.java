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

public class HRUpdateInfoController implements Initializable {



    HRModel hrModel = new HRModel();

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

    int HR_id = accessID.getHRId();



    HRMain.HRCRUD hrCRUD = new HRMain.HRCRUD();
    HRMain.HR hr;





    @FXML
    void onclickupdatebtn() {


        hr = new HRMain.HR(
                HR_id, username.getText(),
                password.getText(),
                hr.getName(),
                hr.getDob(),
                "HRroom",
                0,
                address.getText(),
                contact.getText(),
                email.getText(),
                true
        );
        try {
            hrCRUD.updateHR(hr);
            updlabel.setText("UPDATED");
        } catch (SQLException e) {
            updlabel.setText("NOT UPDATED");
            e.printStackTrace();
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




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setdata();
    }


    public void setdata() {
        try {

            hr = hrCRUD.getHR(HR_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String ph = hr.getContact();
        String eemail = hr.getEmail();
        String uname = hr.getUsername();
        String passw = hr.getPassword();
        String add=hr.getAddress();
        String name = hr.getName();
        Date dob = hr.getDob();

        email.setText(eemail);
        address.setText(add);
        contact.setText(ph);
        username.setText(uname);
        password.setText(passw);

    }







}
