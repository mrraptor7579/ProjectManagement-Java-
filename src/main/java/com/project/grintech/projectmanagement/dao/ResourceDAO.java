package com.project.grintech.projectmanagement.dao;

import com.project.grintech.projectmanagement.model.Resource;
import com.project.grintech.projectmanagement.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAO {
    private Connection conn;

    public ResourceDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Resource> getAllResources() {
        List<Resource> resources = new ArrayList<>();
        String query = "SELECT * FROM resources";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Resource resource = new Resource();
                resource.setId(rs.getString("id"));
                resource.setName(rs.getString("name"));
                resource.setStatus(rs.getString("status"));

                resources.add(resource);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }

    public void addResource(Resource resource) {
        String query = "INSERT INTO resources (id,name, status) VALUES (?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, resource.getId());
            pstmt.setString(2, resource.getName());
            pstmt.setString(3, resource.getStatus());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateResource(Resource resource) {
        String query = "UPDATE resources SET name = ?, status = ?  WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, resource.getName());
            pstmt.setString(2, resource.getStatus());
            pstmt.setString(3, resource.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteResource(String resourceId) {
        String query = "DELETE FROM resources WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, resourceId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Resource getResourceById(String resourceId) {
        Resource resource = null;
        String query = "SELECT * FROM resources WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, resourceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    resource = new Resource();
                    resource.setId(rs.getString("id"));
                    resource.setName(rs.getString("name"));
                    resource.setStatus(rs.getString("status"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resource;
    }
}
