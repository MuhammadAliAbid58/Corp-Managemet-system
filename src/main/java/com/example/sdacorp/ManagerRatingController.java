package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.sdacorp.EmployeeMain.EmployeeCRUD;

public class ManagerRatingController implements Initializable {

    @FXML
    private TextField emp_ID;

    @FXML
    private TextField emp_curr_salary;

    @FXML
    private Label forclosing;

    @FXML
    private TextField increment_amount;

    @FXML
    private ListView<String> listview;

    @FXML
    private Label label;

    EmployeeCRUD employeeCRUD = new EmployeeCRUD();
    EmployeeMain.Employee employee;

    EmployeeSalaryMain.EmployeeSalaryCRUD employeeSalaryCRUD = new EmployeeSalaryMain.EmployeeSalaryCRUD();
    EmployeeSalaryMain.EmployeeSalary employeeSalary;


    ManagerModel managerModel = new ManagerModel();




    @FXML
    void handleKeyPressed(KeyEvent event) {
        if (emp_ID.getText().isBlank()) {
            label.setText("Enter the Employee ID FIRST");
        } else if (event.getCode().equals(KeyCode.ENTER)) {

            try {
                String emp_id_str = emp_ID.getText();
                int emp_id = Integer.parseInt(emp_id_str);
                double base_salary;
                try {
                    if(0 == (base_salary = employeeSalaryCRUD.getBaseSalary(emp_id))){
                        label.setText("Employee ID not found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                emp_curr_salary.setText(String.valueOf(base_salary));
                label.setText("Enter the increment amount");

            } catch (NumberFormatException e) {
                label.setText("Invalid input for Employee ID.");
            }

        }
    }

    @FXML
    void OnClickIncremntBtn(ActionEvent event) {
        String emp_id_str = emp_ID.getText();
        int emp_id = Integer.parseInt(emp_id_str);
        double total_salary;

        if (increment_amount.getText().isBlank()){
            label.setText("Please! Enter the Rating");
        } else {
            double increment_amount_double = Double.parseDouble(increment_amount.getText());
            try {
                employeeSalary.setBonus(increment_amount_double);
                employeeSalaryCRUD.updateSalary(employeeSalary);


//                total_salary = employeeSalaryCRUD.getTotalSalary(emp_id);
//                label.setText("Incremented, Total Salary is : " + total_salary);
            } catch (Exception e) {
                label.setText("Rated !!!!");
            }
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            EmployeeCRUD EmployeeCRUD = new EmployeeCRUD();
            List<Pair<Integer,String>> allemployee = EmployeeCRUD.getAllEmployees();
            // assign the data to the table

            for (Pair<Integer,String> employee : allemployee) {
                String s = "\t\t" + employee.getKey()  + "\t\t\t\t\t\t\t\t\t\t" + employee.getValue() + " \n";
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
