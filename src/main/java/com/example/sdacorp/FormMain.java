package com.example.sdacorp;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.SQLException;

public class FormMain {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "mahad";

    public static void main(String[] args) {
        FormCRUD formCRUD = new FormCRUD();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Form");
            System.out.println("2. View Form");
            System.out.println("3. Update Form");
            System.out.println("4. Delete Form");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Add form
                    System.out.print("Enter Form ID: ");
                    int fid = scanner.nextInt();
                    System.out.print("Enter Form Name: ");
                    String fname = scanner.next();
                    System.out.print("Enter Description: ");
                    String description = scanner.next();
                    System.out.print("Enter Type: ");
                    String type = scanner.next();
                    System.out.print("Enter Employee ID: ");
                    int eid = scanner.nextInt();
                    System.out.print("Enter Manager ID: ");
                    int mid = scanner.nextInt();
                    System.out.print("Is Accepted (true/false): ");
                    boolean isAccepted = scanner.nextBoolean();
                    Form form = new Form(fid, fname, description, type, eid, mid, isAccepted);
                    try {
                        formCRUD.addForm(form);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // View form
                    System.out.print("Enter Form ID: ");
                    fid = scanner.nextInt();
                    try {
                        form = formCRUD.getForm(fid);
                        if (form != null) {
                            System.out.println("Form ID: " + form.getFid());
                            System.out.println("Form Name: " + form.getFname());
                            System.out.println("Description: " + form.getDescription());
                            System.out.println("Type: " + form.getType());
                            System.out.println("Employee ID: " + form.getEid());
                            System.out.println("Manager ID: " + form.getMid());
                            System.out.println("Is Accepted: " + form.isAccepted());
                        } else {
                            System.out.println("No form found with ID: " + fid);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Update form
                    System.out.print("Enter Form ID: ");
                    fid = scanner.nextInt();
                    System.out.print("Enter Form Name: ");
                    fname = scanner.next();
                    System.out.print("Enter Description: ");
                    description = scanner.next();
                    System.out.print("Enter Type: ");
                    type = scanner.next();
                    System.out.print("Enter Employee ID: ");
                    eid = scanner.nextInt();
                    System.out.print("Enter Manager ID: ");
                    mid = scanner.nextInt();
                    System.out.print("Is Accepted (true/false): ");
                    isAccepted = scanner.nextBoolean();
                    form = new Form(fid, fname, description, type, eid, mid, isAccepted);
                    try {
                        formCRUD.updateForm(form);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    // Delete form
                    System.out.print("Enter Form ID: ");
                    fid = scanner.nextInt();
                    try {
                        formCRUD.deleteForm(fid);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    // Exit
                    return;
            }
        }
    }
}
 class Form {
    private int fid;
    private String fname;
    private String description;
    private String type;
    private int eid;
    private int mid;
    private boolean isAccepted;

    public Form(int fid, String fname, String description, String type, int eid, int mid, boolean isAccepted) {
        this.fid = fid;
        this.fname = fname;
        this.description = description;
        this.type = type;
        this.eid = eid;
        this.mid = mid;
        this.isAccepted = isAccepted;
    }

    // Getters and setters for each variable
    // ...

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}


 class FormCRUD {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "12345678";
    public void addForm(Form form) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
            String sql = "INSERT INTO form (fid, fname, description, type, eid, mid, isaccepted) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, form.getFid());
            statement.setString(2, form.getFname());
            statement.setString(3, form.getDescription());
            statement.setString(4, form.getType());
            statement.setInt(5, form.getEid());
            statement.setInt(6, form.getMid());
            statement.setBoolean(7, form.isAccepted());
            statement.executeUpdate();
        }
    }

    public Form getForm(int fid) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
            String sql = "SELECT * FROM form WHERE fid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, fid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String fname = resultSet.getString("fname");
                String description = resultSet.getString("description");
                String type = resultSet.getString("type");
                int eid = resultSet.getInt("eid");
                int mid = resultSet.getInt("mid");
                boolean isAccepted = resultSet.getBoolean("isaccepted");
                return new Form(fid, fname, description, type, eid, mid, isAccepted);
            } else {
                return null;
            }
        }
    }

    public void updateForm(Form form) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
            String sql = "UPDATE form SET fname = ?, description = ?, type = ?, eid = ?, mid = ?, isaccepted = ? WHERE fid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, form.getFname());
            statement.setString(2, form.getDescription());
            statement.setString(3, form.getType());
            statement.setInt(4, form.getEid());
            statement.setInt(5, form.getMid());
            statement.setBoolean(6, form.isAccepted());
            statement.setInt(7, form.getFid());
            statement.executeUpdate();
        }
    }

    public void deleteForm(int fid) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
            String sql = "DELETE FROM form WHERE fid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, fid);
            statement.executeUpdate();
        }
    }
}
