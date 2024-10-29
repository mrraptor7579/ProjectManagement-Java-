


<%--
  Created by IntelliJ IDEA.
  User: akash
  Date: 28-10-2024
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.grintech.projectmanagement.model.Task" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tasks</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- Optional JavaScript and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">Task List</h2>
        <a href="add_task.jsp" class="btn btn-primary">Add New Task</a>
        <a href="index.jsp" class="btn btn-primary">Home</a>
    </div>

    <%
        // Retrieve and decode errorMessage parameter if present
        String errorMessage = request.getParameter("errorMessage");

    %>
    <!-- Bootstrap Alert for error message -->


    <!-- Task Table -->
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Status</th>
                <th>Project Id</th>
                <th>Resource Id</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                // Retrieve the list of tasks from the request attribute
                List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                if (tasks != null && !tasks.isEmpty()) {
                    for (Task task : tasks) {
            %>
            <tr>
                <td><%= task.getId() %></td>
                <td><%= task.getName() %></td>
                <td><%= task.getStatus() %></td>
                <td><%= task.getProjectId() %></td>
                <td><%= task.getResourceId() %></td>
                <td>
                    <a href="TaskServlet?action=edit&id=<%= task.getId() %>" class="btn btn-sm btn-warning">Edit</a>
                    <a href="TaskServlet?action=delete&id=<%= task.getId() %>" class="btn btn-sm btn-danger">Delete</a>

                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6" class="text-center">No tasks available.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    <% if (errorMessage != null) { %>
    alert("<%= errorMessage %>");
    <% } %>
</script>
</body>
</html>


