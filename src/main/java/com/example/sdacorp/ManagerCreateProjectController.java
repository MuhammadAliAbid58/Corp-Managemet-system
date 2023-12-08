package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ManagerCreateProjectController {


    @FXML
    private Label forclosing;


    @FXML
    private DatePicker pdeadline;

    @FXML
    private TextField pdesc;

    @FXML
    private TextField pname;

    @FXML
    private Label plabel;

    @FXML
    private DatePicker pstartdate;

    ManagerModel managerModel = new ManagerModel();

    ProjectMain.ProjectCRUD projectCRUD = new ProjectMain.ProjectCRUD();
    ProjectMain.Project project;


    @FXML
    void OnClickCreateBtn(ActionEvent event) {

        if (pname.getText().isBlank() || pdesc.getText().isBlank()){
            plabel.setText("Please fill all the fields");
            return;
        }


            String project_name = pname.getText();
            String project_desc = pdesc.getText();
            String project_status = "In Progress";
            Date project_start_date = Date.valueOf(pstartdate.getValue().toString());
            Date project_deadline = Date.valueOf(pdeadline.getValue().toString());



        if( project_name.isEmpty() || project_desc.isEmpty() ) {
                plabel.setText("Please fill all the fields");
                return;
        } else {
                project = new ProjectMain.Project(project_name, project_desc, project_status, project_deadline,project_start_date, project_deadline);
                try {
                    projectCRUD.addProject(project);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                plabel.setText("Project Created Successfully");
            }
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
