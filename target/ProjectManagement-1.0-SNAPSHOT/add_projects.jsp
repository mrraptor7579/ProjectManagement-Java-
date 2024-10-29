<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Project</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center text-primary mb-4">Add New Project</h2>
    <a href="index.jsp" class="btn btn-primary">Home</a>
    <!-- Project Form -->
    <form action="ProjectServlet" method="post" class="border p-4 rounded shadow-sm bg-light">
        <input type="hidden" name="action" value="add">

        <!-- Project Name Field -->
        <div class="mb-3">
            <label for="name" class="form-label fw-bold">Project Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>

        <!-- Project Description Field -->
        <div class="mb-3">
            <label for="description" class="form-label fw-bold">Description:</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>

        <!-- Submit Button -->
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Add Project</button>
        </div>
    </form>
</div>

</body>
</html>
