package com.example.sdacorp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;
import java.util.Objects;





public class HelloController {
    int hrId = 0, managerId = 0, employeeId = 0;

    @FXML
    private ComboBox<String> rank;

    @FXML
    private Label setlabel;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    private Button loginbtn;

    private HRModel hrModel = new HRModel();

    private ManagerModel managerModel = new ManagerModel();

    private EmployeeModel employeeModel = new EmployeeModel();

    private AccessID accessID = new AccessID();




    @FXML
    void onclickloginbtn(ActionEvent event)throws IOException {

        if (txtusername.getText().isBlank() && txtpassword.getText().isBlank()) {
            setlabel.setText("Enter Username and Password");
        } else if (txtusername.getText().isBlank()) {
            setlabel.setText("Enter Username Please");
        } else if (txtpassword.getText().isBlank()) {
            setlabel.setText("Enter Password Please");
        } else {
            validateLogin();
        }
    }

    public void validateLogin() throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLoginHR = "SELECT COUNT(1), hid FROM HR WHERE Username = ? AND Password = ?";
        String verifyLoginManager = "SELECT COUNT(1), mid FROM manager WHERE Username = ? AND Password = ?";
        String verifyLoginEmployee = "SELECT COUNT(1), eid FROM employee WHERE Username = ? AND Password = ?";

        try {
            PreparedStatement statementHR = connectDB.prepareStatement(verifyLoginHR);
            statementHR.setString(1, txtusername.getText());
            statementHR.setString(2, txtpassword.getText());

            PreparedStatement statementManager = connectDB.prepareStatement(verifyLoginManager);
            statementManager.setString(1, txtusername.getText());
            statementManager.setString(2, txtpassword.getText());

            PreparedStatement statementEmployee = connectDB.prepareStatement(verifyLoginEmployee);
            statementEmployee.setString(1, txtusername.getText());
            statementEmployee.setString(2, txtpassword.getText());

            ResultSet queryResultHR = statementHR.executeQuery();
            ResultSet queryResultManager = statementManager.executeQuery();
            ResultSet queryResultEmployee = statementEmployee.executeQuery();



            if (queryResultHR.next() && queryResultManager.next() && queryResultEmployee.next()) {
                int countHR = queryResultHR.getInt(1);
                int countManager = queryResultManager.getInt(1);
                int countEmployee = queryResultEmployee.getInt(1);

                if (countHR == 1 && Objects.equals(rank.getValue(), "HR")) {

                    hrId = queryResultHR.getInt("hid");
                    accessID.setHRId(hrId);
                    hrModel.hr_Homepage(new Stage());


                    close_stage();
                } else if (countManager == 1 && Objects.equals(rank.getValue(), "Manager")) {

                    managerId = queryResultManager.getInt("mid");
                    accessID.setManagerId(managerId);
                    managerModel.manager_home_page(new Stage());

                    close_stage();;

                } else if (countEmployee == 1 && Objects.equals(rank.getValue(), "Employee")) {
                    employeeId = queryResultEmployee.getInt("eid");
                    accessID.setEmployeeId(employeeId);
                    employeeModel.Employee_Homepage(new Stage());


                    close_stage();
                } else {
                    setlabel.setText("Invalid Login. Please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close_stage()
    {
        Stage closestage = (Stage) loginbtn.getScene().getWindow();
        closestage.close();
    }













}
