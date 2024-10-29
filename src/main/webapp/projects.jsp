<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.grintech.projectmanagement.model.Project" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Projects</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center text-primary mb-4">Project List</h2>

    <!-- Add Project Button -->
    <div class="text-end mb-3">
        <a href="index.jsp" class="btn btn-primary">Home</a>
        <a href="add_projects.jsp" class="btn btn-success">Add New Project</a>
    </div>

    <!-- Project Table -->
    <table class="table table-bordered table-striped shadow-sm">
        <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Project> projects = (List<Project>) request.getAttribute("projects");
            if (projects != null) {
                for (Project project : projects) {
        %>
        <tr>
            <td><%= project.getId() %></td>
            <td><%= project.getName() %></td>
            <td><%= project.getDescription() %></td>
            <td>
                <a href="ProjectServlet?action=edit&id=<%= project.getId() %>" class="btn btn-sm btn-warning">Edit</a>
                <a href="ProjectServlet?action=delete&id=<%= project.getId() %>" class="btn btn-sm btn-danger">Delete</a>
                <a href="ProjectServlet?action=viewProject&id=<%= project.getId() %>" class="btn btn-sm btn-info">View Details</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4" class="text-center">No projects available</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
