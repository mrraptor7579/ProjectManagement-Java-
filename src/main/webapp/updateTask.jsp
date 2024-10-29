<%@ page import="com.project.grintech.projectmanagement.model.Task" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Task</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<%
    Task task = (Task)request.getAttribute("task");
%>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <h1 class="text-center mb-4 text-primary">Update Task</h1>
            <a href="index.jsp" class="btn btn-primary">Home</a>
            <form action="TaskServlet" method="post" class="border p-4 rounded shadow-sm bg-light">
                <input type="hidden" name="action" value="update">

                <!-- Task ID Field (Read-only) -->
                <div class="mb-3">
                    <label for="id" class="form-label fw-bold">Task ID</label>
                    <input type="text" class="form-control" id="id" name="id" value="<%=task.getId()%>" required readonly>
                </div>

                <!-- Task Name Field -->
                <div class="mb-3">
                    <label for="name" class="form-label fw-bold">Task Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="<%=task.getName()%>" required>
                </div>

                <!-- Status Dropdown -->
                <div class="mb-3">
                    <label for="status" class="form-label fw-bold">Status</label>
                    <select class="form-select" id="status" name="status" required>
                        <option value="PENDING" <%= task.getStatus().equals("PENDING") ? "selected" : "" %>>Pending</option>
                        <option value="INPROGRESS" <%= task.getStatus().equals("INPROGRESS") ? "selected" : "" %>>In Progress</option>
                        <option value="COMPLETED" <%= task.getStatus().equals("COMPLETED") ? "selected" : "" %>>Completed</option>
                    </select>
                </div>

                <!-- Project ID Field -->
                <div class="mb-3">
                    <label for="projectId" class="form-label fw-bold">Project ID</label>
                    <input type="text" class="form-control" id="projectId" name="project_id" value="<%=task.getProjectId()%>" required>
                </div>

                <!-- Resource ID Field -->
                <div class="mb-3">
                    <label for="resourceId" class="form-label fw-bold">Resource ID</label>
                    <input type="text" class="form-control" id="resourceId" name="resource_id" value="<%=task.getResourceId()%>" required>
                </div>

                <!-- Submit Button -->
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Update Task</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
