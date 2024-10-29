package com.project.grintech.projectmanagement.dao;



import com.project.grintech.projectmanagement.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectDAO {
    private Connection conn;

    public ProjectDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getString("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public void addProject(Project project) {
        String query = "INSERT INTO projects (id,name, description) VALUES (?,?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, project.getId());
            pstmt.setString(2, project.getName());
            pstmt.setString(3, project.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProject(Project project) {
        String query = "UPDATE projects SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, project.getName());
            pstmt.setString(2, project.getDescription());
            pstmt.setString(3, project.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(String projectId) {
        String query = "DELETE FROM projects WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, projectId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Project getProjectById(String projectId) {
        Project project = null;
        String query = "SELECT * FROM projects WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, projectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    project = new Project();
                    project.setId(rs.getString("id"));
                    project.setName(rs.getString("name"));
                    project.setDescription(rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }
}

