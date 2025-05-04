<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${book.id == null ? 'Add New Book' : 'Edit Book'} - Library Management System</title>
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
            <li class="nav-item active">
                <a class="nav-link" href="/books">Books</a>
            </li>
            <li class="nav-item">
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
            <h1>${book.id == null ? 'Add New Book' : 'Edit Book'}</h1>
            <p>${book.id == null ? 'Add a new book to your library.' : 'Update book information.'}</p>
        </div>
        <div class="col-md-4 text-right">
            <a href="/books" class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> Back to Books
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
                    <c:when test="${book.id == null}">
                        <form:form method="POST" action="/books" modelAttribute="book">
                            <div class="form-group">
                                <label for="title">Title</label>
                                <form:input path="title" class="form-control" id="title" placeholder="Enter title" />
                                <form:errors path="title" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="isbn">ISBN</label>
                                <form:input path="isbn" class="form-control" id="isbn" placeholder="Enter ISBN (10 or 13 digits)" />
                                <form:errors path="isbn" cssClass="error" />
                                <small class="form-text text-muted">ISBN must be 10 or 13 digits.</small>
                            </div>

                            <div class="form-group">
                                <label for="authorId">Author</label>
                                <form:select path="authorId" class="form-control" id="authorId">
                                    <form:option value="" label="-- Select Author --" />
                                    <c:forEach var="author" items="${authors}">
                                        <form:option value="${author.id}" label="${author.firstName} ${author.lastName}" />
                                    </c:forEach>
                                </form:select>
                                <form:errors path="authorId" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="genre">Genre</label>
                                <form:select path="genre" class="form-control" id="genre">
                                    <form:option value="" label="-- Select Genre --" />
                                    <form:option value="Fiction" label="Fiction" />
                                    <form:option value="Non-Fiction" label="Non-Fiction" />
                                    <form:option value="Science Fiction" label="Science Fiction" />
                                    <form:option value="Fantasy" label="Fantasy" />
                                    <form:option value="Mystery" label="Mystery" />
                                    <form:option value="Thriller" label="Thriller" />
                                    <form:option value="Romance" label="Romance" />
                                    <form:option value="Biography" label="Biography" />
                                    <form:option value="History" label="History" />
                                    <form:option value="Science" label="Science" />
                                    <form:option value="Technology" label="Technology" />
                                </form:select>
                                <form:errors path="genre" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="publicationDate">Publication Date</label>
                                <form:input path="publicationDate" type="date" class="form-control" id="publicationDate" />
                                <form:errors path="publicationDate" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="price">Price</label>
                                <form:input path="price" type="number" step="0.01" class="form-control" id="price" placeholder="Enter price" />
                                <form:errors path="price" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="description">Description</label>
                                <form:textarea path="description" class="form-control" id="description" rows="5" placeholder="Enter book description" />
                                <form:errors path="description" cssClass="error" />
                            </div>

                            <button type="submit" class="btn btn-primary">Create Book</button>
                            <a href="/books" class="btn btn-secondary">Cancel</a>
                        </form:form>
                    </c:when>
                    <c:otherwise>
                        <form:form method="POST" action="/books/update/${book.id}" modelAttribute="book">
                            <form:hidden path="id" />

                            <div class="form-group">
                                <label for="title">Title</label>
                                <form:input path="title" class="form-control" id="title" placeholder="Enter title" />
                                <form:errors path="title" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="isbn">ISBN</label>
                                <form:input path="isbn" class="form-control" id="isbn" placeholder="Enter ISBN (10 or 13 digits)" />
                                <form:errors path="isbn" cssClass="error" />
                                <small class="form-text text-muted">ISBN must be 10 or 13 digits.</small>
                            </div>

                            <div class="form-group">
                                <label for="authorId">Author</label>
                                <form:select path="authorId" class="form-control" id="authorId">
                                    <form:option value="" label="-- Select Author --" />
                                    <c:forEach var="author" items="${authors}">
                                        <form:option value="${author.id}" label="${author.firstName} ${author.lastName}" />
                                    </c:forEach>
                                </form:select>
                                <form:errors path="authorId" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="genre">Genre</label>
                                <form:select path="genre" class="form-control" id="genre">
                                    <form:option value="" label="-- Select Genre --" />
                                    <form:option value="Fiction" label="Fiction" />
                                    <form:option value="Non-Fiction" label="Non-Fiction" />
                                    <form:option value="Science Fiction" label="Science Fiction" />
                                    <form:option value="Fantasy" label="Fantasy" />
                                    <form:option value="Mystery" label="Mystery" />
                                    <form:option value="Thriller" label="Thriller" />
                                    <form:option value="Romance" label="Romance" />
                                    <form:option value="Biography" label="Biography" />
                                    <form:option value="History" label="History" />
                                    <form:option value="Science" label="Science" />
                                    <form:option value="Technology" label="Technology" />
                                </form:select>
                                <form:errors path="genre" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="publicationDate">Publication Date</label>
                                <form:input path="publicationDate" type="date" class="form-control" id="publicationDate" />
                                <form:errors path="publicationDate" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="price">Price</label>
                                <form:input path="price" type="number" step="0.01" class="form-control" id="price" placeholder="Enter price" />
                                <form:errors path="price" cssClass="error" />
                            </div>

                            <div class="form-group">
                                <label for="description">Description</label>
                                <form:textarea path="description" class="form-control" id="description" rows="5" placeholder="Enter book description" />
                                <form:errors path="description" cssClass="error" />
                            </div>

                            <button type="submit" class="btn btn-primary">Update Book</button>
                            <a href="/books" class="btn btn-secondary">Cancel</a>
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