<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Project Management System</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
  <h1 class="text-center text-primary mb-4">Welcome to the Project Management System</h1>

  <!-- Navigation Bar -->
  <nav class="nav justify-content-center bg-light border p-3 rounded shadow-sm">
    <a class="nav-link text-dark fw-bold" href="index.jsp">Home</a>
    <a class="nav-link text-dark fw-bold" href="ProjectServlet?action=list">Projects</a>
    <a class="nav-link text-dark fw-bold" href="TaskServlet?action=list">Tasks</a>
    <a class="nav-link text-dark fw-bold" href="ResourceServlet?action=list">Resources</a>
  </nav>
</div>

</body>
</html>
