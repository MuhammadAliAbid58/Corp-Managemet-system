package com.example.sdacorp;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Date;

public class ProjectMain {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "mahad";

    public static void main(String[] args) {
        ProjectCRUD projectCRUD = new ProjectCRUD();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Project");
            System.out.println("2. View Project");
            System.out.println("3. Update Project");
            System.out.println("4. Delete Project");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter pid: ");
                    int pid = scanner.nextInt();
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter description: ");
                    String description = scanner.next();
                    System.out.print("Enter status: ");
                    String status = scanner.next();
                    System.out.print("Enter deadline (yyyy-MM-dd): ");
                    String deadline = scanner.next();
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    String startDate = scanner.next();
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    String endDate = scanner.next();
                    try {
                        projectCRUD.addProject(new Project(pid, name, description, status, Date.valueOf(deadline), Date.valueOf(startDate), Date.valueOf(endDate)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Enter pid: ");
                    pid = scanner.nextInt();
                    try {
                        Project project = projectCRUD.getProject(pid);
                        if (project != null) {
                            System.out.println("Name: " + project.getName());
                            System.out.println("Description: " + project.getDescription());
                            System.out.println("Status: " + project.getStatus());
                            System.out.println("Deadline: " + project.getDeadline());
                            System.out.println("Start Date: " + project.getStartDate());
                            System.out.println("End Date: " + project.getEndDate());
                        } else {
                            System.out.println("No project found with pid: " + pid);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter pid: ");
                    pid = scanner.nextInt();
                    System.out.print("Enter name: ");
                    name = scanner.next();
                    System.out.print("Enter description: ");
                    description = scanner.next();
                    System.out.print("Enter status: ");
                    status = scanner.next();
                    System.out.print("Enter deadline (yyyy-MM-dd): ");
                    deadline = scanner.next();
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    startDate = scanner.next();
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    endDate = scanner.next();
                    try {
                        projectCRUD.updateProject(new Project(pid, name, description, status, Date.valueOf(deadline), Date.valueOf(startDate), Date.valueOf(endDate)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.print("Enter pid: ");
                    pid = scanner.nextInt();
                    try {
                        projectCRUD.deleteProject(pid);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }


    static class Project {
        private int pid;
        private String name;
        private String description;
        private String status;
        private Date deadline;
        private Date startDate;
        private Date endDate;

        public Project(int pid, String name, String description, String status, Date deadline, Date startDate, Date endDate) {
            this.pid = pid;
            this.name = name;
            this.description = description;
            this.status = status;
            this.deadline = deadline;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Project( String name, String description, String status, Date deadline, Date startDate, Date endDate) {
            this.name = name;
            this.description = description;
            this.status = status;
            this.deadline = deadline;
            this.startDate = startDate;
            this.endDate = endDate;
        }


        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Date getDeadline() {
            return deadline;
        }

        public void setDeadline(Date deadline) {
            this.deadline = deadline;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }

    // EmployeeSalaryCRUD

    static class ProjectCRUD {
        private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/corp";
        private static final String DATABASE_USERNAME = "root";
        private static final String DATABASE_PASSWORD = "12345678";

        public void addProject(Project project) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "INSERT INTO project (name, description, status, deadline, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, project.getName());
                statement.setString(2, project.getDescription());
                statement.setString(3, project.getStatus());
                statement.setTimestamp(4, new java.sql.Timestamp(project.getDeadline().getTime()));
                statement.setTimestamp(5, new java.sql.Timestamp(project.getStartDate().getTime()));
                statement.setTimestamp(6, new java.sql.Timestamp(project.getEndDate().getTime()));
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Project getProject(int pid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT * FROM project WHERE pid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, pid);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String status = resultSet.getString("status");
                    Date deadline = resultSet.getDate("deadline");
                    Date startDate = resultSet.getDate("start_date");
                    Date endDate = resultSet.getDate("end_date");
                    return new Project(pid, name, description, status, deadline, startDate, endDate);
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void updateProject(Project project) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "UPDATE project SET name = ?, description = ?, status = ?, deadline = ?, start_date = ?, end_date = ? WHERE pid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, project.getName());
                statement.setString(2, project.getDescription());
                statement.setString(3, project.getStatus());
                statement.setTimestamp(4, new java.sql.Timestamp(project.getDeadline().getTime()));
                statement.setTimestamp(5, new java.sql.Timestamp(project.getStartDate().getTime()));
                statement.setTimestamp(6, new java.sql.Timestamp(project.getEndDate().getTime()));
                statement.setInt(7, project.getPid());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteProject(int pid) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "DELETE FROM project WHERE pid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, pid);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // return all project pid and name
        public List<Pair<Integer, String>> getAllProject() throws SQLException {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
                String sql = "SELECT pid, name FROM project";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                List<Pair<Integer, String>> projectList = new ArrayList<>();
                while (resultSet.next()) {
                    int pid = resultSet.getInt("pid");
                    String name = resultSet.getString("name");
                    projectList.add(new Pair<>(pid, name));
                }
                return projectList;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}