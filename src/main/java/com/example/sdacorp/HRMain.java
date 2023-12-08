package com.example.sdacorp;

import java.sql.*;
import java.util.Scanner;
import java.sql.SQLException;

public class HRMain {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "mahad";

    public static void main(String[] args) {
        HRCRUD hrCRUD = new HRCRUD();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add HR");
            System.out.println("2. View HR");
            System.out.println("3. Update HR");
            System.out.println("4. Delete HR");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Add HR
                    System.out.print("Enter HR ID: ");
                    int hid = scanner.nextInt();
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
                    System.out.print("Enter No of Hiring: ");
                    int noOfHiring = scanner.nextInt();
                    System.out.print("Enter Address: ");
                    String address = scanner.next();
                    System.out.print("Enter Contact: ");
                    String contact = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    System.out.print("Is Available (true/false): ");
                    boolean isAvailable = scanner.nextBoolean();
                    HR hr = new HR(hid, username, password, name, dob, roomNo, noOfHiring, address, contact, email, isAvailable);
                    try {
                        hrCRUD.addHR(hr);
                        System.out.println("HR added successfully.");
                    } catch (SQLException e) {
                        System.out.println("Error while adding HR: " + e.getMessage());
                    }
                    break;
                case 2:
                    // View HR
                    System.out.print("Enter HR ID: ");
                    hid = scanner.nextInt();
                    try {
                        hr = hrCRUD.getHR(hid);
                        if (hr != null) {
                            System.out.println("HR ID: " + hr.getHid());
                            System.out.println("Username: " + hr.getUsername());
                            System.out.println("Password: " + hr.getPassword());
                            System.out.println("Name: " + hr.getName());
                            System.out.println("DOB: " + hr.getDob());
                            System.out.println("Room No: " + hr.getRoomNo());
                            System.out.println("No of Hiring: " + hr.getNoOfHiring());
                            System.out.println("Address: " + hr.getAddress());
                            System.out.println("Contact: " + hr.getContact());
                            System.out.println("Email: " + hr.getEmail());
                            System.out.println("Is Available: " + hr.isAvailable());
                        } else {
                            System.out.println("No HR found with ID: " + hid);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error while getting HR: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Update HR
                    System.out.print("Enter HR ID: ");
                    hid = scanner.nextInt();
                    try {
                        hr = hrCRUD.getHR(hid);
                        if (hr != null) {
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
                            System.out.print("Enter No of Hiring: ");
                            noOfHiring = scanner.nextInt();
                            System.out.print("Enter Address: ");
                            address = scanner.next();
                            System.out.print("Enter Contact: ");
                            contact = scanner.next();
                            System.out.print("Enter Email: ");
                            email = scanner.next();
                            System.out.print("Is Available (true/false): ");
                            isAvailable = scanner.nextBoolean();
                            hr = new HR(hid, username, password, name, dob, roomNo, noOfHiring, address, contact, email, isAvailable);
                            hrCRUD.updateHR(hr);
                            System.out.println("HR updated successfully.");
                        } else {
                            System.out.println("No HR found with ID: " + hid);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error while updating HR: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Delete HR
                    System.out.print("Enter HR ID: ");
                    hid = scanner.nextInt();
                    try {
                        hr = hrCRUD.getHR(hid);
                        if (hr != null) {
                            hrCRUD.deleteHR(hid);
                            System.out.println("HR deleted successfully.");
                        } else {
                            System.out.println("No HR found with ID: " + hid);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error while deleting HR: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    static class HR
    {
        private int hid;
        private String username;
        private String password;
        private String name;
        private Date dob;
        private String roomNo;
        private int noOfHiring;
        private String address;
        private String contact;
        private String email;
        private boolean isAvailable;

        // Constructor
        public HR(int hid, String username, String password, String name, Date dob, String roomNo, int noOfHiring, String address, String contact, String email, boolean isAvailable) {
            this.hid = hid;
            this.username = username;
            this.password = password;
            this.name = name;
            this.dob = dob;
            this.roomNo = roomNo;
            this.noOfHiring = noOfHiring;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.isAvailable = isAvailable;
        }

        public HR( String username, String password, String name, Date dob, String roomNo, int noOfHiring, String address, String contact, String email, boolean isAvailable) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.dob = dob;
            this.roomNo = roomNo;
            this.noOfHiring = noOfHiring;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.isAvailable = isAvailable;
        }

        // Getters and Setters
        public int getHid() {
            return hid;
        }

        public void setHid(int hid) {
            this.hid = hid;
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

        public int getNoOfHiring() {
            return noOfHiring;
        }

        public void setNoOfHiring(int noOfHiring) {
            this.noOfHiring = noOfHiring;
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

    public static class HRCRUD
    {
        private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
        private static final String DATABASE_USERNAME = "root";
        private static final String DATABASE_PASSWORD = "12345678";

        public void addHR(HR hr) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "INSERT INTO hr (hid, username, password, name, dob, room_no, no_of_hiring, address, contact, email, isavailable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, hr.getHid());
                statement.setString(2, hr.getUsername());
                statement.setString(3, hr.getPassword());
                statement.setString(4, hr.getName());
                statement.setDate(5, new java.sql.Date(hr.getDob().getTime()));
                statement.setString(6, hr.getRoomNo());
                statement.setInt(7, hr.getNoOfHiring());
                statement.setString(8, hr.getAddress());
                statement.setString(9, hr.getContact());
                statement.setString(10, hr.getEmail());
                statement.setBoolean(11, hr.isAvailable());
                statement.executeUpdate();
            }
        }

        public HR getHR(int hid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT * FROM hr WHERE hid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, hid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    Date dob = resultSet.getDate("dob");
                    String roomNo = resultSet.getString("room_no");
                    int noOfHiring = resultSet.getInt("no_of_hiring");
                    String address = resultSet.getString("address");
                    String contact = resultSet.getString("contact");
                    String email = resultSet.getString("email");
                    boolean isAvailable = resultSet.getBoolean("isavailable");
                    return new HR(hid, username, password, name, dob, roomNo, noOfHiring, address, contact, email, isAvailable);
                } else {
                    return null;
                }
            }
        }

        public void updateHR(HR hr) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "UPDATE hr SET username = ?, password = ?, name = ?, dob = ?, room_no = ?, no_of_hiring = ?, address = ?, contact = ?, email = ?, isavailable = ? WHERE hid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, hr.getUsername());
                statement.setString(2, hr.getPassword());
                statement.setString(3, hr.getName());
                statement.setDate(4, new java.sql.Date(hr.getDob().getTime()));
                statement.setString(5, hr.getRoomNo());
                statement.setInt(6, hr.getNoOfHiring());
                statement.setString(7, hr.getAddress());
                statement.setString(8, hr.getContact());
                statement.setString(9, hr.getEmail());
                statement.setBoolean(10, hr.isAvailable());
                statement.setInt(11, hr.getHid());
                statement.executeUpdate();
            }
        }

        public void deleteHR(int hid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "DELETE FROM hr WHERE hid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, hid);
                statement.executeUpdate();
            }
        }

        // need to check for zero value
        public int getHRid(String username) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT hid FROM hr WHERE username = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("hid");
                } else {
                    return 0;
                }
            } catch (SQLException e) {
                System.out.println("Error while getting hid: " + e.getMessage());
                e.printStackTrace();
                return 0;
            }
        }
    }
}