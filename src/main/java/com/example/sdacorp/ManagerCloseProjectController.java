package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Pair;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class ManagerCloseProjectController implements Initializable {

    @FXML
    private Label forclosing;

    @FXML
    private Label label;

    @FXML
    private ListView<String> listview;

    @FXML
    private TextField pid;

    ManagerModel managerModel = new ManagerModel();





    @FXML
    void OncClickCloseBtn(ActionEvent event) {

        if (pid.getText().isEmpty()) {
            label.setText("Please fill all the fields");
        } else {
            int project_id = Integer.parseInt(pid.getText());
            try {
                ProjectMain.ProjectCRUD projectCRUD = new ProjectMain.ProjectCRUD();
                ProjectMain.Project project;
                if((project = projectCRUD.getProject(project_id)) == null){
                    label.setText("Project Not Found");
                    return;
                }
                if (Objects.equals(project.getStatus(), "Closed")){
                    label.setText("Project Already Closed");
                    return;
                }
                project.setStatus("Closed");
                try {
                    projectCRUD.updateProject(project);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                label.setText("Project Closed Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ProjectMain.ProjectCRUD projectCRUD = new ProjectMain.ProjectCRUD();
            List<Pair<Integer,String>> allprojects = projectCRUD.getAllProject();
            // assign the data to the table

            for (Pair<Integer,String> project : allprojects) {
                String s = "\t\t" +project.getKey() + "\t\t\t\t\t\t\t\t\t\t" + project.getValue() + " \n";
                listview.getItems().add(s);
            }







            //project_table = (TableColumn<Integer, String>) projectCRUD.getAllProject();
        } catch (Exception e) {
            e.printStackTrace();
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
