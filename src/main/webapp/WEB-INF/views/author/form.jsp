<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${author.id == null ? 'Add New Author' : 'Edit Author'} - Library Management System</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .form-container {
            background-color: #f8f9fa;
            padding: 2rem;
            border-radius: 0.25rem;
            box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
        }
        .error {
            color: #dc3545;
            font-size: 0.875rem;
            margin-top: 0.25rem;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Library Management System</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/books">Books</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/authors">Authors</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/books/with-authors">Books with Authors</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-4">
    <div class="row mb-4">
        <div class="col-md-8">
            <h1>${author.id == null ? 'Add New Author' : 'Edit Author'}</h1>
            <p>${author.id == null ? 'Create a new author in your library.' : 'Update author information.'}</p>
        </div>
        <div class="col-md-4 text-right">
            <a href="/authors" class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> Back to Authors
            </a>
        </div>
    </div>

    <!-- Display messages if any -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMessage}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>

    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="form-container">
                <c:choose>
                    <c:when test="${author.id == null}">
                        <form:form method="POST" action="/authors" modelAttribute="author">
                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <form:input path="firstName" class="form-control" id="firstName" placeholder="Enter first name" />
                                <form:errors path="firstName" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="lastName">Last Name</label>
                                <form:input path="lastName" class="form-control" id="lastName" placeholder="Enter last name" />
                                <form:errors path="lastName" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="email">Email</label>
                                <form:input path="email" type="email" class="form-control" id="email" placeholder="Enter email" />
                                <form:errors path="email" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="birthYear">Birth Year</label>
                                <form:input path="birthYear" type="number" class="form-control" id="birthYear" placeholder="Enter birth year" />
                                <form:errors path="birthYear" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="biography">Biography</label>
                                <form:textarea path="biography" class="form-control" id="biography" rows="5" placeholder="Enter biography" />
                                <form:errors path="biography" cssClass="error" />
                            </div>

                            <button type="submit" class="btn btn-primary">Create Author</button>
                            <a href="/authors" class="btn btn-secondary">Cancel</a>
                        </form:form>
                    </c:when>
                    <c:otherwise>
                        <form:form method="POST" action="/authors/update/${author.id}" modelAttribute="author">
                            <form:hidden path="id" />

                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <form:input path="firstName" class="form-control" id="firstName" placeholder="Enter first name" />
                                <form:errors path="firstName" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="lastName">Last Name</label>
                                <form:input path="lastName" class="form-control" id="lastName" placeholder="Enter last name" />
                                <form:errors path="lastName" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="email">Email</label>
                                <form:input path="email" type="email" class="form-control" id="email" placeholder="Enter email" />
                                <form:errors path="email" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="birthYear">Birth Year</label>
                                <form:input path="birthYear" type="number" class="form-control" id="birthYear" placeholder="Enter birth year" />
                                <form:errors path="birthYear" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="biography">Biography</label>
                                <form:textarea path="biography" class="form-control" id="biography" rows="5" placeholder="Enter biography" />
                                <form:errors path="biography" cssClass="error" />
                            </div>

                            <button type="submit" class="btn btn-primary">Update Author</button>
                            <a href="/authors" class="btn btn-secondary">Cancel</a>
                        </form:form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<footer class="bg-dark text-white text-center py-3 mt-5">
    <p>Library Management System &copy; 2023</p>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>