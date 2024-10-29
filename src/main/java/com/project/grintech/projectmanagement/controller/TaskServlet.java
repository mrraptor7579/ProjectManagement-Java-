package com.project.grintech.projectmanagement.controller;


import com.project.grintech.projectmanagement.exceptions.ResourceOverAllocationException;
import com.project.grintech.projectmanagement.model.Project;
import com.project.grintech.projectmanagement.model.Resource;
import com.project.grintech.projectmanagement.model.Task;
import com.project.grintech.projectmanagement.service.ProjectService;
import com.project.grintech.projectmanagement.service.ResourceService;
import com.project.grintech.projectmanagement.service.TaskService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
    private TaskService taskService;
    private ResourceService resourceService;
    private ProjectService projectService;

    @Override
    public void init() {
        taskService = new TaskService();
        resourceService=new ResourceService();
        projectService=new ProjectService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "list":
                listTasks(request, response);
                break;
            case "edit":
                    showEditForm(request,response);
                    break;
            case "update":
                    updateTask(request,response);
                    break;
            case "delete":
                deleteTask(request, response);
                break;

            case "viewTask":
                viewTask(request,response);
                break;


            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }



    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId=request.getParameter("id");
        Task task=taskService.getTaskById(taskId);
        request.setAttribute("task",task);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateTask.jsp");
        dispatcher.forward(request, response);

    }


    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> tasks = taskService.getAllTasks();
        request.setAttribute("tasks", tasks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task.jsp");
        dispatcher.forward(request, response);
    }

    private void viewTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId = request.getParameter("id");
        Task existingTask = taskService.getTaskById(taskId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("add_task.jsp");
        request.setAttribute("task", existingTask);
        dispatcher.forward(request, response);
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String taskId = request.getParameter("id");
        taskService.deleteTask(taskId);
        response.sendRedirect("TaskServlet?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            createTask(request, response);
        } else if ("update".equals(action)) {
            updateTask(request, response);
        }
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=UUID.randomUUID().toString();
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String projectId=request.getParameter("project_id");

        Task task =new Task(id,name,status,projectId);
        taskService.createTask(task);
        response.sendRedirect("TaskServlet?action=list");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String resourceId = request.getParameter("resource_id");
        String projectId = request.getParameter("project_id");

        Resource resource = resourceService.getResourceById(resourceId);
        // Check for over-allocation of resource
        long assignedTasksInSameProject = taskService.countResouceInSameProject(projectId, resourceId);
        // Update resource status if the task is completed
        try {
        if ("COMPLETED".equals(status)) {
            resource.setStatus("AVAILABLE");
            resourceService.updateResource(resource);
            taskService.updateTask(new Task(id,name,status,projectId,resourceId));



        }
        else if (assignedTasksInSameProject >= 2) {
                resource.setStatus("ASSIGNED");
                resourceService.updateResource(resource);
                throw new ResourceOverAllocationException("Resource cannot be assigned to more than 2 tasks in the same project.");
            } else {
                // Update task details
                Task task = new Task();
                task.setId(id);
                task.setName(name);
                task.setStatus(status);
                task.setProjectId(projectId);
                task.setResourceId(resourceId);

                if (assignedTasksInSameProject > 0) {
                    resource.setStatus("ASSIGNED");
                    resourceService.updateResource(resource);
                }

                taskService.updateTask(task);
            }
            response.sendRedirect("TaskServlet?action=list");
        } catch (ResourceOverAllocationException e) {
            System.out.println("Exception detected: " + e.getMessage());

            // Redirect with error message as a URL parameter
            String errorMessage = "resources are over-allocated";
            response.sendRedirect("TaskServlet?action=list&errorMessage=" + errorMessage);

            // Forwarding to the error page with error details

        }
    }

}
