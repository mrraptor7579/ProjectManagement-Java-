<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Task</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Add Task</h1>
    <a href="index.jsp" class="btn btn-primary">Home</a>
    <form action="TaskServlet" method="post">
        <input type="hidden" name="action" value="create">

        <div class="mb-3">
            <label for="name" class="form-label">Task Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select class="form-select" id="status" name="status" required>
                <option value="PENDING">Pending</option>
                <option value="INPROGRESS">In Progress</option>
                <option value="COMPLETED">Completed</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="projectId" class="form-label">Project ID</label>
            <input type="text" class="form-control" id="projectId" name="project_id" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Task</button>
    </form>
</div>
</body>
</html>