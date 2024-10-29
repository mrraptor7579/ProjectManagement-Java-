package com.project.grintech.projectmanagement.controller;





import com.project.grintech.projectmanagement.model.Project;
import com.project.grintech.projectmanagement.model.Task;
import com.project.grintech.projectmanagement.service.ProjectService;
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

@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
    private ProjectService projectService;
    private TaskService taskService;

    @Override
    public void init() {
        projectService = new ProjectService();
        taskService=new TaskService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "list":
                listProjects(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateProject(request,response);
                break;

            case "delete":
                deleteProject(request, response);
                break;
            case "viewProject":
                    viewProject(request,response);
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void listProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Project> projects = projectService.getAllProjects();
        request.setAttribute("projects", projects);
        RequestDispatcher dispatcher = request.getRequestDispatcher("projects.jsp");
        dispatcher.forward(request, response);
    }

    private void viewProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectId = request.getParameter("id");
        List<Task>tasks=taskService.getAllTasksByProjectId(projectId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task.jsp");
        request.setAttribute("tasks", tasks);
        dispatcher.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectId = request.getParameter("id");
        Project existingProject = projectService.getProjectById(projectId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateProject.jsp");
        request.setAttribute("project", existingProject);
        dispatcher.forward(request, response);
    }

    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String projectId = request.getParameter("id");
        projectService.deleteProject(projectId);
        response.sendRedirect("ProjectServlet?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addProject(request, response);
        } else if ("update".equals(action)) {
            updateProject(request, response);
        }
    }

    private void addProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String id= UUID.randomUUID().toString();
        Project project =new Project(id,name,description);
        projectService.addProject(project);
        response.sendRedirect("ProjectServlet?action=list");
    }
    private void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setDescription(description);

        projectService.updateProject(project);
        response.sendRedirect("ProjectServlet?action=list");
    }
}
