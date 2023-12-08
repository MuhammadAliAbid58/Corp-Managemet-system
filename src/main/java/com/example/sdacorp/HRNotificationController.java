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


public class HRNotificationController implements Initializable {

    @FXML
    private Label forclosing;

    HRModel hrModel=new HRModel();


    AccessID accessID=new AccessID();

    int HR_id = accessID.getHRId();



    HRMain.HRCRUD hrCRUD = new HRMain.HRCRUD();
    HRMain.HR hr;





    @Override
    public void initialize(URL location, ResourceBundle resources)   {
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
