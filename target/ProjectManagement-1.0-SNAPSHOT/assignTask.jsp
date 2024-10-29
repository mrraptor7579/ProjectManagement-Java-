<%@ page import="com.project.grintech.projectmanagement.model.Task" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Assign Resource Alter</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<%
    Task task = (Task)request.getAttribute("task");

%>
<div class="container">
    <h1>Assign Resource To Task</h1>
    <form action="TaskServlet" method="post">
        <input type="hidden" name="action" value="assignTask">
        <div class="mb-3">
            <label for="taskId" class="form-label">Task Id</label>
            <input type="text" class="form-control" id="taskId" name="id" value="<%=task.getId()%>" required readonly>
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Task Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%=task.getName()%>" required >
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select class="form-select" id="status" name="status" required >
                <option value="PENDING"<%=task.getStatus().equals("PENDING")?"SELECTED":""%>>Pending</option>
                <option value="INPROGRESS" <%=task.getStatus().equals("INPROGRESS")?"SELECTED":""%>>In Progress</option>
                <option value="COMPLETED" <%=task.getStatus().equals("COMPLETED")?"SELECTED":""%>>Completed</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="projectId" class="form-label">Project ID</label>
            <input type="text" class="form-control" id="projectId" value="<%=task.getProjectId()%>" name="project_id" required >
        </div>
        <div class="mb-3">
            <label for="resourceId" class="form-label">Resource ID</label>
            <input type="text" class="form-control" value="<%=task.getResourceId()%>" id="resourceId" name="resource_id" required>
        </div>
        <button type="submit" class="btn btn-primary">Assign Resource</button>
    </form>
</div>
</body>
</html>
