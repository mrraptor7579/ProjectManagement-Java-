<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.grintech.projectmanagement.model.Project" %>
<%@ page import="com.project.grintech.projectmanagement.model.Resource" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Resource List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center text-primary mb-4">Resource List</h2>

    <!-- Add Resource Button -->
    <div class="text-end mb-3">
        <a href="add_resource.jsp" class="btn btn-success">Add New Resource</a>
        <a href="index.jsp" class="btn btn-primary">Home</a>
    </div>

    <!-- Resource Table -->
    <table class="table table-bordered table-striped shadow-sm">
        <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Resource> resources = (List<Resource>) request.getAttribute("resources");
            if (resources != null) {
                for (Resource resource : resources) {
        %>
        <tr>
            <td><%= resource.getId() %></td>
            <td><%= resource.getName() %></td>
            <td><%= resource.getStatus() %></td>
            <td>
                <a href="ResourceServlet?action=showUpdateForm&id=<%= resource.getId() %>" class="btn btn-sm btn-warning">Edit</a>
                <a href="ResourceServlet?action=delete&id=<%= resource.getId() %>" class="btn btn-sm btn-danger">Delete</a>
                <a href="ResourceServlet?action=viewDetails&id=<%= resource.getId() %>" class="btn btn-sm btn-info">View Details</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4" class="text-center">No resources available</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
