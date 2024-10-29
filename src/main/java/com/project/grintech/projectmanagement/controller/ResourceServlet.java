package com.project.grintech.projectmanagement.controller;

import com.project.grintech.projectmanagement.model.Resource;
import com.project.grintech.projectmanagement.model.Task;
import com.project.grintech.projectmanagement.service.ResourceService;
import com.project.grintech.projectmanagement.service.TaskService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/ResourceServlet")
public class ResourceServlet extends HttpServlet {
    private ResourceService resourceService;
    private TaskService taskService;

    @Override
    public void init() {
        resourceService = new ResourceService();
        taskService = new TaskService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "list":
                listResources(request, response);
                break;
            case "delete":
                deleteResource(request, response);
                break;
            case "viewDetails":
                viewDetails(request,response);
                break;
            case "showUpdateForm":
                showUpdateForm(request,response);
                break;
            case "update":
                    updateResource(request,response);
                    break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resourceId=request.getParameter("id");
        Resource resource=resourceService.getResourceById(resourceId);
        request.setAttribute("resource",resource);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateResource.jsp");
        dispatcher.forward(request, response);
    }

    private void listResources(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Resource> resources = resourceService.getALLResources();
        request.setAttribute("resources", resources);
        RequestDispatcher dispatcher = request.getRequestDispatcher("resource.jsp");
        dispatcher.forward(request, response);
    }

    private void viewDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resourceId = request.getParameter("id");
        List<Task>tasks=taskService.getAllTasksByResourceId(resourceId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task.jsp");
        request.setAttribute("tasks", tasks);
        dispatcher.forward(request, response);
    }

    private void deleteResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resourceId = request.getParameter("id");
        resourceService.deleteResource(resourceId);
        response.sendRedirect("ResourceServlet?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addResource(request, response);
        } else if ("update".equals(action)) {
            updateResource(request, response);
        }
    }

    private void addResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id= UUID.randomUUID().toString();
        String name = request.getParameter("name");
        String status ="AVAILABLE";
        Resource resource =new Resource(id,name,status);
        resourceService.addResource(resource);
        response.sendRedirect("ResourceServlet?action=list");
    }

    private void updateResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String name=request.getParameter("name");


        Resource resource = new Resource();
        resource.setName(name);
        resource.setId(id);
        resource.setStatus(status);

        resourceService.updateResource(resource);
        response.sendRedirect("ResourceServlet?action=list");
    }
}
