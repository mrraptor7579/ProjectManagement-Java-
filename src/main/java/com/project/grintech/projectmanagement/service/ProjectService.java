package com.project.grintech.projectmanagement.service;



import com.project.grintech.projectmanagement.dao.ProjectDAO;
import com.project.grintech.projectmanagement.model.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    private ProjectDAO projectDAO;

    public ProjectService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjectManagement", "root", "root");
            System.out.println("connected");
            projectDAO = new ProjectDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public void addProject(Project project) {
        projectDAO.addProject(project);
    }

    public void updateProject(Project project) {
        projectDAO.updateProject(project);
    }

    public void deleteProject(String projectId) {
        projectDAO.deleteProject(projectId);
    }

    public Project getProjectById(String projectId) {
        return projectDAO.getProjectById(projectId);
    }
}

