<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books with Authors - Library Management System</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .book-row:hover {
            background-color: #f8f9fa;
        }
        .table-responsive {
            box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
            border-radius: 0.25rem;
        }
        .special-heading {
            border-bottom: 2px solid #007bff;
            padding-bottom: 0.5rem;
            margin-bottom: 1.5rem;
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
            <li class="nav-item">
                <a class="nav-link" href="/authors">Authors</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/books/with-authors">Books with Authors</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-4">
    <div class="row mb-4">
        <div class="col-12">
            <h1 class="special-heading">Books with Authors (Inner Join)</h1>
            <p class="lead">This page demonstrates the use of a custom query to perform an inner join between books and authors.</p>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Book ID</th>
                <th>Book Title</th>
                <th>ISBN</th>
                <th>Genre</th>
                <th>Author ID</th>
                <th>Author Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bookWithAuthor" items="${booksWithAuthors}">
                <tr class="book-row">
                    <td>${bookWithAuthor.bookId}</td>
                    <td>${bookWithAuthor.bookTitle}</td>
                    <td>${bookWithAuthor.isbn}</td>
                    <td>${bookWithAuthor.genre}</td>
                    <td>${bookWithAuthor.authorId}</td>
                    <td>${bookWithAuthor.authorName}</td>
                    <td>
                        <a href="/books/${bookWithAuthor.bookId}" class="btn btn-info btn-sm">View Book</a>
                        <a href="/authors/${bookWithAuthor.authorId}" class="btn btn-primary btn-sm">View Author</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty booksWithAuthors}">
                <tr>
                    <td colspan="7" class="text-center">No data found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

    <div class="card mt-4">
        <div class="card-header bg-info text-white">
            <h5 class="mb-0">Technical Details</h5>
        </div>
        <div class="card-body">
            <h6>Query Used:</h6>
            <pre><code>@Query("SELECT b, a FROM Book b INNER JOIN b.author a")
List&lt;Object[]&gt; findBooksWithAuthors();</code></pre>

            <h6 class="mt-3">Implementation:</h6>
            <p>This query performs an inner join between the Book and Author entities using JPA's JPQL (Java Persistence Query Language). The result is processed in the service layer to create a list of maps containing both book and author information.</p>
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