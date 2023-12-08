package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Instant;
import java.sql.Date;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;


public class ManagerManageProjectController implements Initializable {

    @FXML
    private TextField P_ID;

    @FXML
    private Label forclosing;

    @FXML
    private ListView<String> listview;

    @FXML
    private DatePicker pdeadline;

    @FXML
    private TextField pdesc;

    @FXML
    private Label plabel;

    @FXML
    private TextField pname;


    Date start_date;


    ProjectMain.ProjectCRUD projectCRUD = new ProjectMain.ProjectCRUD();
    ProjectMain.Project project;


    ManagerModel managerModel = new ManagerModel();


    @FXML
    void OnClickManageBtn(ActionEvent event) {
        String project_id_str = P_ID.getText();
        int project_id = Integer.parseInt(project_id_str);


        Date pstartdate = Date.valueOf(pdeadline.getValue().toString());
        String project_name = pname.getText();
        String project_desc = pdesc.getText();
        String project_status =  project.getStatus();
        Date project_deadline = Date.valueOf(pdeadline.getValue().toString());
        Date project_end_date = Date.valueOf(pdeadline.getValue().toString());

        project = new ProjectMain.Project(
                project_id,
                project_name,
                project_desc,
                project_status,
                project_deadline,
                start_date,
                project_end_date
        );
        try {
            projectCRUD.updateProject(project);
            plabel.setText("UPDATED");
        } catch (SQLException e) {
            plabel.setText("NOT UPDATED");
            e.printStackTrace();
        }



    }




    @FXML

    void handleKeyPressed(KeyEvent event) {

            if (P_ID.getText().isBlank()) {
                plabel.setText("Enter the PROJECT ID FIRST");
            } else if (event.getCode().equals(KeyCode.ENTER)) {

                try {
                    String project_id_str = P_ID.getText();
                    int project_id = Integer.parseInt(project_id_str);

                    try {
                        project = projectCRUD.getProject(project_id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    String name = project.getName();
                    String description = project.getDescription();
                    Date deadline = project.getDeadline();
                    start_date = project.getStartDate();
                    // Convert java.sql.Date to LocalDate
                    Instant instant = deadline.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant();
                    LocalDate localDeadline = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                    pname.setText(name);
                    pdesc.setText(description);
                    pdeadline.setValue(localDeadline);

                } catch (NumberFormatException e) {
                    plabel.setText("Invalid input for project ID.");
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
                String s = project.getKey() + "\t\t" + project.getValue() + " \n";
                listview.getItems().add(s);
            }

//            ProjectMain.Project oldproject;
//            String project_id_str = P_ID.getText();
//            int project_id = Integer.parseInt(project_id_str);
//
//            try {
//                oldproject = projectCRUD.getProject(project_id);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }







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
