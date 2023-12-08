package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class HRController implements Initializable {

    @FXML
    private Label forclosing;

    @FXML
    private Label hrname;

    @FXML
    private Label hremail;

    @FXML
    private Label hrphone;

    @FXML
    private Label hraddress;

    HRModel hrModel=new HRModel();


    AccessID accessID=new AccessID();

    int HR_id = accessID.getHRId();



    HRMain.HRCRUD hrCRUD = new HRMain.HRCRUD();
    HRMain.HR hr;





    @Override
    public void initialize(URL location, ResourceBundle resources)   {

        try {

            hr = hrCRUD.getHR(HR_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String name = hr.getName();
        String address = hr.getAddress();
        String ph = hr.getContact();
        String email = hr.getEmail();

        hrname.setText(name);
        hraddress.setText(address);
        hrphone.setText(ph);
        hremail.setText(email);


    }



    @FXML
    void onclickrgistrationbtn() throws IOException {

            hrModel.hr_registrationpage(new Stage());
            close_stage();

    }

    @FXML
    void onclickupdateinfobtn() throws IOException {

            hrModel.hr_updateinfopage(new Stage());
            close_stage();
    }

    @FXML
    void OnClickCreateAccount(ActionEvent event) throws IOException{

        hrModel.hr_CreateAccountPage(new Stage());
        close_stage();

    }

    @FXML
    void OnClickNotification (ActionEvent event) throws IOException{

        hrModel.hr_Notification(new Stage());
        close_stage();

    }

    public void close_stage()
    {
        Stage closestage = (Stage) forclosing.getScene().getWindow();
        closestage.close();
    }
}
