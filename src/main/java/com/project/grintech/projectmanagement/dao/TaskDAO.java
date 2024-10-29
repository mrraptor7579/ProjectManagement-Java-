package com.project.grintech.projectmanagement.dao;

import com.project.grintech.projectmanagement.model.Project;
import com.project.grintech.projectmanagement.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private Connection conn;

    public TaskDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getString("id"));
                task.setName(rs.getString("name"));
                task.setStatus(rs.getString("status"));
                task.setProjectId(rs.getString("project_id"));
                task.setResourceId(rs.getString("resource_id"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    public List<Task> getAllTasksByProjectId(String projectId) {
        List<Task> tasks = new ArrayList<>();

        String query = "SELECT * FROM tasks WHERE project_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, projectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task = new Task();
                    task.setId(rs.getString("id"));
                    task.setName(rs.getString("name"));
                    task.setStatus(rs.getString("status"));
                    task.setProjectId(rs.getString("project_id"));
                    task.setResourceId(rs.getString("resource_id"));
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    public List<Task> getAllTasksByResourceId(String resourceId) {
        List<Task> tasks = new ArrayList<>();

        String query = "SELECT * FROM tasks WHERE resource_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, resourceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task = new Task();
                    task.setId(rs.getString("id"));
                    task.setName(rs.getString("name"));
                    task.setStatus(rs.getString("status"));
                    task.setProjectId(rs.getString("project_id"));
                    task.setResourceId(rs.getString("resource_id"));
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

        public void createTask(Task task) {
        String query = "INSERT INTO tasks (id,name, status,project_id) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, task.getId());
            pstmt.setString(2, task.getName());
            pstmt.setString(3, task.getStatus());
            pstmt.setString(4,task.getProjectId());
            System.out.println(task.getProjectId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(Task task) {
        String query = "UPDATE tasks SET status = ?, name=?,resource_id=? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, task.getStatus());
            pstmt.setString(2,task.getName());
            pstmt.setString(3,task.getResourceId());
            pstmt.setString(4, task.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletetask(String taskId) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, taskId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task getTaskById(String taskId) {
        Task task = null;
        String query = "SELECT * FROM tasks WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, taskId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    task = new Task();
                    task.setId(rs.getString("id"));
                    task.setName(rs.getString("name"));
                    task.setStatus(rs.getString("status"));
                    task.setProjectId(rs.getString("project_id"));
                    task.setResourceId(rs.getString("resource_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public long getResouceCountInSameProject(String projectId, String resourceId) {
        long cnt=0;
        String query="SELECT COUNT(*) FROM tasks WHERE project_id=? and resource_id=? and status not in ('COMPLETED')";
        try(PreparedStatement pstmt=conn.prepareStatement(query)){
            pstmt.setString(1,projectId);
            pstmt.setString(2,resourceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                     cnt=rs.getInt(1);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }
}
