package com.example.sdacorp;

import javafx.util.Pair;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeMain {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "12345678";
    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("1. Add EmployeeMain");
            System.out.println("2. Update EmployeeMain");
            System.out.println("3. Delete EmployeeMain");
            System.out.println("4. View EmployeeMain");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add employee
                    System.out.print("Enter EmployeeMain ID: ");
                    int eid = scanner.nextInt();
                    System.out.print("Enter Username: ");
                    String username = scanner.next();
                    System.out.print("Enter Password: ");
                    String password = scanner.next();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter DOB (dd/MM/yyyy): ");
                    String dobStr = scanner.next();
                    Date dob = null;
                    try {
                        dob = sdf.parse(dobStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.print("Enter Address: ");
                    String address = scanner.next();
                    System.out.print("Enter Contact: ");
                    String contact = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    System.out.print("Enter Availability (true or false): ");
                    boolean isAvailable = scanner.nextBoolean();

                    Employee employee = new Employee(eid, username, password, name, dob, address, contact, email, isAvailable);
                    try {
                        employeeCRUD.addEmployee(employee);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Update employee
                    System.out.print("Enter EmployeeMain ID to Update: ");
                    int eidToUpdate = scanner.nextInt();
                    try {
                        Employee existingEmployee = employeeCRUD.getEmployee(eidToUpdate);
                        if (existingEmployee != null) {
                            System.out.print("Enter New Username: ");
                            existingEmployee.setUsername(scanner.next());
                            employeeCRUD.updateEmployee(existingEmployee);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Delete employee
                    System.out.print("Enter EmployeeMain ID to Delete: ");
                    int eidToDelete = scanner.nextInt();
                    try {
                        employeeCRUD.deleteEmployee(eidToDelete);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    // View employee
                    System.out.print("Enter EmployeeMain ID to View: ");
                    int eidToView = scanner.nextInt();
                    try {
                        Employee employeeToView = employeeCRUD.getEmployee(eidToView);
                        if (employeeToView != null) {
                            System.out.println("EmployeeMain ID: " + employeeToView.getEid());
                            System.out.println("Username: " + employeeToView.getUsername());
                            System.out.println("Password: " + employeeToView.getPassword());
                            System.out.println("Name: " + employeeToView.getName());
                            System.out.println("DOB: " + new SimpleDateFormat("dd/MM/yyyy").format(employeeToView.getDob()));
                            System.out.println("Address: " + employeeToView.getAddress());
                            System.out.println("Contact: " + employeeToView.getContact());
                            System.out.println("Email: " + employeeToView.getEmail());
                            System.out.println("Availability: " + (employeeToView.isAvailable() ? "Available" : "Not Available"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    // Exit
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    public static class EmployeeCRUD {

        private Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }
        public Employee getEmployee(int eid) throws SQLException {
            String sql = "SELECT * FROM employee WHERE eid = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, eid);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Employee(
                                resultSet.getInt("eid"),
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("name"),
                                resultSet.getDate("dob"),
                                resultSet.getString("address"),
                                resultSet.getString("contact"),
                                resultSet.getString("email"),
                                resultSet.getBoolean("isavailable")
                        );
                    } else {
                        return null;
                    }
                }
            }
        }

        public void addEmployee(Employee employee) throws SQLException {
            String sql = "INSERT INTO employee (eid, username, password, name, dob, address, contact, email, isavailable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employee.getEid());
                statement.setString(2, employee.getUsername());
                statement.setString(3, employee.getPassword());
                statement.setString(4, employee.getName());
                statement.setDate(5, new java.sql.Date(employee.getDob().getTime()));
                statement.setString(6, employee.getAddress());
                statement.setString(7, employee.getContact());
                statement.setString(8, employee.getEmail());
                statement.setBoolean(9, employee.isAvailable());
                statement.executeUpdate();
            }
        }


        public void updateEmployee(Employee employee) throws SQLException {
            String sql = "UPDATE employee SET username = ?, password = ?, name = ?, dob = ?, address = ?, contact = ?, email = ?, isavailable = ? WHERE eid = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, employee.getUsername());
                statement.setString(2, employee.getPassword());
                statement.setString(3, employee.getName());
                statement.setDate(4, new java.sql.Date(employee.getDob().getTime()));
                statement.setString(5, employee.getAddress());
                statement.setString(6, employee.getContact());
                statement.setString(7, employee.getEmail());
                statement.setBoolean(8, employee.isAvailable());
                statement.setInt(9, employee.getEid());
                statement.executeUpdate();
            }
        }

        public void deleteEmployee(int eid) throws SQLException {
            String sql = "DELETE FROM employee WHERE eid = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, eid);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // need to check for zero value
        public int getEmployeeId(String username) throws SQLException {
            String sql = "SELECT eid FROM employee WHERE username = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql))  {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("eid");
                    } else {
                        return 0;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }

        public List<Pair<Integer, String>> getAllEmployees() throws SQLException
        {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD))
            {
                String sql = "SELECT eid, username FROM employee";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<Pair<Integer, String>> projectList = new ArrayList<>();
                while (resultSet.next()) {
                    int eid = resultSet.getInt("eid");
                    String name = resultSet.getString("username");
                    projectList.add(new Pair<>(eid, name));
                }
                return projectList;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class Employee {
        private int eid;
        private String username;
        private String password;
        private String name;
        private Date dob;
        private String address;
        private String contact;
        private String email;
        private boolean isAvailable;

        public Employee(int eid, String username, String password, String name, Date dob, String address, String contact, String email, boolean isAvailable) {
            this.eid = eid;
            this.username = username;
            this.password = password;
            this.name = name;
            this.dob = dob;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.isAvailable = isAvailable;
        }

        // constructor without eid
        public Employee(String username, String password, String name, Date dob, String address, String contact, String email, boolean isAvailable) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.dob = dob;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.isAvailable = isAvailable;
        }

        public Employee(int eid, String username, String password, String name, java.sql.Date dob,  String address, String contact, String email, boolean isAvailable) {
            this.eid = eid;
            this.username = username;
            this.password = password;
            this.name = name;
            this.dob = dob;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.isAvailable = isAvailable;
        }


        // Getters and setters for each variable
        public int getEid() {
            return eid;
        }

        public void setEid(int eid) {
            this.eid = eid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date dob) {
            this.dob = dob;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
        }

    }
}
