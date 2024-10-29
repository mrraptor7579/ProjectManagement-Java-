<%@ page import="com.project.grintech.projectmanagement.model.Resource" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Resource</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<%
    Resource resource=(Resource) request.getAttribute("resource");
%>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h1 class="text-center mb-4 text-primary">Update Resource</h1>
            <a href="index.jsp" class="btn btn-primary">Home</a>
            <form action="ResourceServlet" method="post" class="border p-4 rounded shadow-sm bg-light">
                <input type="hidden" name="action" value="update">

                <div class="mb-3">
                    <label for="id" class="form-label fw-bold">Resource Id</label>
                    <input type="text" class="form-control" id="id" name="id" value="<%=resource.getId()%>" required readonly>
                </div>
                <!-- Resource Name Field -->
                <div class="mb-3">
                    <label for="name" class="form-label fw-bold">Resource Name</label>
                    <input type="text" class="form-control" id="name"  value="<%=resource.getName()%>" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="status" class="form-label fw-bold">Resource Status</label>
                    <select class="form-select" id="status" name="status" required>
                        <option value="ASSIGNED" <%= resource.getStatus().equals("ASSIGNED") ? "selected" : "" %>>Assigned</option>
                        <option value="AVAILABLE" <%= resource.getStatus().equals("AVAILABLE") ? "selected" : "" %>>Available</option>

                    </select>
                </div>
                <!-- Submit Button -->
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Update Resource</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
