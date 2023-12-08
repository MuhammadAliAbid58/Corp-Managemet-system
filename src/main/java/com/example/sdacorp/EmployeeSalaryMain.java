package com.example.sdacorp;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class EmployeeSalaryMain {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "mahad";

    public static void main(String[] args) {
        EmployeeSalaryCRUD employeeSalaryCRUD = new EmployeeSalaryCRUD();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Update Employee Salary");
            System.out.println("2. View Employee Salary");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Update employee salary
                    System.out.print("Enter Employee ID: ");
                    int eid = scanner.nextInt();
                    System.out.print("Enter Base Salary: ");
                    double baseSalary = scanner.nextDouble();
                    System.out.print("Enter Bonus: ");
                    double bonus = scanner.nextDouble();
                    System.out.print("Enter Fine: ");
                    double fine = scanner.nextDouble();
                    EmployeeSalary employeeSalary = new EmployeeSalary(eid, baseSalary, bonus, fine);
                    try {
                        employeeSalaryCRUD.updateSalary(employeeSalary);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // View employee salary
                    System.out.print("Enter Employee ID to View Salary: ");
                    int eidToView = scanner.nextInt();
                    try {
                        EmployeeSalary employeeSalaryToView = employeeSalaryCRUD.getSalary(eidToView);
                        if (employeeSalaryToView != null) {
                            System.out.println("Employee ID: " + employeeSalaryToView.getEid());
                            System.out.println("Base Salary: " + employeeSalaryToView.getBaseSalary());
                            System.out.println("Bonus: " + employeeSalaryToView.getBonus());
                            System.out.println("Fine: " + employeeSalaryToView.getFine());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Exit
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    // EmployeeSalary
    public static class EmployeeSalary {
        private int eid;
        private double baseSalary;
        private double bonus;
        private double fine;

        public EmployeeSalary(int eid, double baseSalary, double bonus, double fine) {
            this.eid = eid;
            this.baseSalary = baseSalary;
            this.bonus = bonus;
            this.fine = fine;
        }

        // Getters and setters for each variable
        public int getEid() {
            return eid;
        }

        public void setEid(int eid) {
            this.eid = eid;
        }

        public double getBaseSalary() {
            return baseSalary;
        }

        public void setBaseSalary(double baseSalary) {
            this.baseSalary = baseSalary;
        }

        public double getBonus() {
            return bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }

        public double getFine() {
            return fine;
        }

        public void setFine(double fine) {
            this.fine = fine;
        }
    }

    // EmployeeSalaryCRUD
    public static class EmployeeSalaryCRUD {
        private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
        private static final String DATABASE_USERNAME = "root";
        private static final String DATABASE_PASSWORD = "12345678";

        public void updateSalary(EmployeeSalary employeeSalary) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "UPDATE employee_salary SET base_salary = ?, bonus = ?, fine = ? WHERE eid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDouble(1, employeeSalary.getBaseSalary());
                statement.setDouble(2, employeeSalary.getBonus());
                statement.setDouble(3, employeeSalary.getFine());
                statement.setInt(4, employeeSalary.getEid());
                statement.executeUpdate();
            }
        }

        public EmployeeSalary getSalary(int eid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT * FROM employee_salary WHERE eid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, eid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    double baseSalary = resultSet.getDouble("base_salary");
                    double bonus = resultSet.getDouble("bonus");
                    double fine = resultSet.getDouble("fine");
                    return new EmployeeSalary(eid, baseSalary, bonus, fine);
                } else {
                    return null;
                }
            }
        }

        public double getBaseSalary(int eid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT base_salary FROM corp.employee_salary WHERE eid =  ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, eid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    double baseSalary = resultSet.getDouble("base_salary");
                    return baseSalary;
                } else {
                    return 0;
                }
            }
        }

        public double getTotalSalary(int eid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT base_salary + bonus - fine AS total_salary FROM corp.employee_salary WHERE eid =  ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, eid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    double totalSalary = resultSet.getDouble("total_salary");
                    return totalSalary;
                } else {
                    return 0;
                }
            }
        }

    }
}
