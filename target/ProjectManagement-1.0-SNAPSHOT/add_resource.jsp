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

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h1 class="text-center mb-4 text-primary">Add Resource</h1>
            <a href="index.jsp" class="btn btn-primary">Home</a>
            <form action="ResourceServlet" method="post" class="border p-4 rounded shadow-sm bg-light">
                <input type="hidden" name="action" value="add">

                <!-- Resource Name Field -->
                <div class="mb-3">
                    <label for="name" class="form-label fw-bold">Resource Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>

                <!-- Submit Button -->
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Add Resource</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
