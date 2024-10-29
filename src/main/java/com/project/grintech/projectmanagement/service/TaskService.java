package com.project.grintech.projectmanagement.service;

import com.project.grintech.projectmanagement.dao.ProjectDAO;
import com.project.grintech.projectmanagement.dao.TaskDAO;
import com.project.grintech.projectmanagement.model.Project;
import com.project.grintech.projectmanagement.model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TaskService {
    private TaskDAO taskDAO;

    public TaskService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjectManagement", "root", "root");
            System.out.println("connected");
            taskDAO = new TaskDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }
    public List<Task> getAllTasksByProjectId(String projectId) {
        return taskDAO.getAllTasksByProjectId(projectId);
    }
    public List<Task> getAllTasksByResourceId(String resourceId) {
        return taskDAO.getAllTasksByResourceId(resourceId);
    }

    public void createTask(Task task) {
        taskDAO.createTask(task);
    }

    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    public void deleteTask(String taskId) {
        taskDAO.deletetask(taskId);
    }

    public Task getTaskById(String taskId) {
        return taskDAO.getTaskById(taskId);
    }

    public long countResouceInSameProject(String projectId, String resourceId) {
        return taskDAO.getResouceCountInSameProject(projectId,resourceId);

    }
}
