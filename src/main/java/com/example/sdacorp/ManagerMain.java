package com.example.sdacorp;

import java.sql.*;
import java.util.Scanner;
import java.util.*;
import java.sql.Date;
import java.sql.SQLException;
public class ManagerMain
{
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "12345678";

    public static void main(String[] args) {
        ManagerCRUD managerCRUD = new ManagerCRUD();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Manager");
            System.out.println("2. View Manager");
            System.out.println("3. Update Manager");
            System.out.println("4. Delete Manager");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Add Manager
                    System.out.print("Enter Manager ID: ");
                    int mid = scanner.nextInt();
                    System.out.print("Enter Username: ");
                    String username = scanner.next();
                    System.out.print("Enter Password: ");
                    String password = scanner.next();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter DOB (yyyy-mm-dd): ");
                    Date dob = Date.valueOf(scanner.next());
                    System.out.print("Enter Room No: ");
                    String roomNo = scanner.next();
                    System.out.print("Enter Address: ");
                    String address = scanner.next();
                    System.out.print("Enter Contact: ");
                    String contact = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    System.out.print("Is Available (true/false): ");
                    boolean isAvailable = scanner.nextBoolean();
                    Manager manager = new Manager(mid, username, password, name, dob, roomNo, address, contact, email, isAvailable);
                    try {
                        managerCRUD.addManager(manager);
                        System.out.println("Manager added successfully.");
                    } catch (SQLException e) {
                        System.out.println("Error while adding Manager: " + e.getMessage());
                    }
                    break;
                case 2:
                    // View Manager
                    System.out.print("Enter Manager ID: ");
                    mid = scanner.nextInt();
                    try {
                        manager = managerCRUD.getManager(mid);
                        if (manager != null) {
                            System.out.println("Manager ID: " + manager.getMid());
                            System.out.println("Username: " + manager.getUsername());
                            System.out.println("Password: " + manager.getPassword());
                            System.out.println("Name: " + manager.getName());
                            System.out.println("DOB: " + manager.getDob());
                            System.out.println("Room No: " + manager.getRoomNo());
                            System.out.println("Address: " + manager.getAddress());
                            System.out.println("Contact: " + manager.getContact());
                            System.out.println("Email: " + manager.getEmail());
                            System.out.println("Is Available: " + manager.isAvailable());
                        } else {
                            System.out.println("No such Manager found!");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error while getting Manager: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Update Manager
                    System.out.print("Enter Manager ID: ");
                    mid = scanner.nextInt();
                    System.out.print("Enter Username: ");
                    username = scanner.next();
                    System.out.print("Enter Password: ");
                    password = scanner.next();
                    System.out.print("Enter Name: ");
                    name = scanner.next();
                    System.out.print("Enter DOB (yyyy-mm-dd): ");
                    dob = Date.valueOf(scanner.next());
                    System.out.print("Enter Room No: ");
                    roomNo = scanner.next();
                    System.out.print("Enter Address: ");
                    address = scanner.next();
                    System.out.print("Enter Contact: ");
                    contact = scanner.next();
                    System.out.print("Enter Email: ");
                    email = scanner.next();
                    System.out.print("Is Available (true/false): ");
                    isAvailable = scanner.nextBoolean();
                    manager = new Manager(mid, username, password, name, dob, roomNo, address, contact, email, isAvailable);
                    try {
                        managerCRUD.updateManager(manager);
                        System.out.println("Manager updated successfully.");
                    } catch (SQLException e) {
                        System.out.println("Error while updating Manager: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Delete Manager
                    System.out.print("Enter Manager ID: ");
                    mid = scanner.nextInt();
                    try {
                        managerCRUD.deleteManager(mid);
                        System.out.println("Manager deleted successfully.");
                    } catch (SQLException e) {
                        System.out.println("Error while deleting Manager: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Exit
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }



    public static class Manager
    {
        private int mid;
        private String username;
        private String password;
        private String name;
        private Date dob;
        private String roomNo;
        private String address;
        private String contact;
        private String email;
        private boolean isAvailable;

        public Manager(int mid, String username, String password, String name, Date dob, String roomNo, String address, String contact, String email, boolean isAvailable) {
            this.mid = mid;
            this.username = username;
            this.password = password;
            this.name = name;
            this.dob = dob;
            this.roomNo = roomNo;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.isAvailable = isAvailable;
        }

        public Manager( String username, String password, String name, Date dob, String roomNo, String address, String contact, String email, boolean isAvailable) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.dob = dob;
            this.roomNo = roomNo;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.isAvailable = isAvailable;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
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

        public String getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo;
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

    // EmployeeSalaryCRUD

    public static class ManagerCRUD {
        private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
        private static final String DATABASE_USERNAME = "root";
        private static final String DATABASE_PASSWORD = "12345678";


        public void addManager(Manager manager) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "INSERT INTO manager (mid, username, password, name, dob, room_no, address, contact, email, isavailable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, manager.getMid());
                statement.setString(2, manager.getUsername());
                statement.setString(3, manager.getPassword());
                statement.setString(4, manager.getName());
                statement.setDate(5, new java.sql.Date(manager.getDob().getTime()));
                statement.setString(6, manager.getRoomNo());
                statement.setString(7, manager.getAddress());
                statement.setString(8, manager.getContact());
                statement.setString(9, manager.getEmail());
                statement.setBoolean(10, manager.isAvailable());
                statement.executeUpdate();
            }
        }

        public Manager getManager(int mid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT * FROM manager WHERE mid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, mid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    Date dob = resultSet.getDate("dob");
                    String roomNo = resultSet.getString("room_no");
                    String address = resultSet.getString("address");
                    String contact = resultSet.getString("contact");
                    String email = resultSet.getString("email");
                    boolean isAvailable = resultSet.getBoolean("isavailable");
                    return new Manager(mid, username, password, name, dob, roomNo, address, contact, email, isAvailable);
                } else {
                    return null;
                }
            }
        }

        public void updateManager(Manager manager) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "UPDATE manager SET username = ?, password = ?, name = ?, dob = ?, room_no = ?, address = ?, contact = ?, email = ?, isavailable = ? WHERE mid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, manager.getUsername());
                statement.setString(2, manager.getPassword());
                statement.setString(3, manager.getName());
                statement.setDate(4, new java.sql.Date(manager.getDob().getTime()));
                statement.setString(5, manager.getRoomNo());
                statement.setString(6, manager.getAddress());
                statement.setString(7, manager.getContact());
                statement.setString(8, manager.getEmail());
                statement.setBoolean(9, manager.isAvailable());
                statement.setInt(10, manager.getMid());
                statement.executeUpdate();
            }
        }

        public void deleteManager(int mid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "DELETE FROM manager WHERE mid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, mid);
                statement.executeUpdate();
            }
        }

        // need to check for zero value
        public int getManagerId(String username) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT mid FROM manager WHERE username = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int mid = resultSet.getInt("mid");
                    return mid;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

    }


}