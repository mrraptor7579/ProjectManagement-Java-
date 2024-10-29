package com.project.grintech.projectmanagement.service;

import com.project.grintech.projectmanagement.dao.ProjectDAO;
import com.project.grintech.projectmanagement.dao.ResourceDAO;
import com.project.grintech.projectmanagement.model.Project;
import com.project.grintech.projectmanagement.model.Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ResourceService {
    private ResourceDAO resourceDAO;

    public ResourceService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjectManagement", "root", "root");
            System.out.println("connected");
            resourceDAO = new ResourceDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Resource> getALLResources() {
        return resourceDAO.getAllResources();
    }

    public void addResource(Resource resource) {
        resourceDAO.addResource(resource);
    }

    public void updateResource(Resource resource) {
        resourceDAO.updateResource(resource);
    }

    public void deleteResource(String resourceId) {
        resourceDAO.deleteResource(resourceId);
    }

    public Resource getResourceById(String resourceId) {
        return resourceDAO.getResourceById(resourceId);
    }
}
