package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {

    ManagerModel managerModel = new ManagerModel();

    @FXML
    private Label man_add;

    @FXML
    private Label man_email;

    @FXML
    private Label man_name;

    @FXML
    private Label man_phone;


    @FXML
    private Label forclosing;

    AccessID accessID=new AccessID();


    private int manager_id=accessID.getManagerId();



    ManagerMain.ManagerCRUD managerCRUD = new ManagerMain.ManagerCRUD();
    ManagerMain.Manager manager;

    @Override
    public void initialize(URL location, ResourceBundle resources)   {

        try {

            manager = managerCRUD.getManager(manager_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String name = manager.getName();
        String address = manager.getAddress();
        String ph = manager.getContact();
        String email = manager.getEmail();

        man_name.setText(name);
        man_add.setText(address);
        man_phone.setText(ph);
        man_email.setText(email);


    }

    @FXML
    void OnClickHomeBtn(ActionEvent event) throws IOException {

        managerModel.manager_home_page(new Stage());
        close_stage();

    }



    @FXML
    void OnClickCreateProjectBtn(ActionEvent event)throws IOException {

        managerModel.manager_create_project_page(new Stage());

       close_stage();

    }

    @FXML
    void OnClickUpdateInfoBtn(ActionEvent event)throws IOException {

        managerModel.manager_Update_Info_page(new Stage());
        close_stage();

    }



    @FXML
    void OncClickCloseProjectBtn(ActionEvent event) throws IOException {

        managerModel.manager_Close_project_stage(new Stage());
        close_stage();

    }

    @FXML
    void OnClickManageProjectBtn(ActionEvent event)  throws IOException{
        managerModel.manager_manage_project(new Stage());
        close_stage();
    }

    @FXML
    void OnClickIncrementBtn(ActionEvent event)  throws IOException{
        managerModel.manager_Increments_page(new Stage());
        close_stage();
    }

    @FXML
    void OnClickRating(ActionEvent event) throws IOException {
        managerModel.manager_Rating_page(new Stage());
        close_stage();
    }

    @FXML
    void OnClickNotificationBtn(ActionEvent event) throws IOException{
        managerModel.manager_notification_page(new Stage());
        close_stage();
    }


    public void close_stage()
    {
        Stage closestage = (Stage) forclosing.getScene().getWindow();
        closestage.close();
    }

}
