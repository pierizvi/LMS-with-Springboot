<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${book.title} - Library Management System</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .details-container {
            background-color: #f8f9fa;
            padding: 2rem;
            border-radius: 0.25rem;
            box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
        }
        .detail-row {
            margin-bottom: 1rem;
        }
        .detail-label {
            font-weight: bold;
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
            <h1>Book Details</h1>
            <p>View complete information about the book.</p>
        </div>
        <div class="col-md-4 text-right">
            <a href="/books" class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> Back to Books
            </a>
            <a href="/books/edit/${book.id}" class="btn btn-primary">
                <i class="fas fa-edit"></i> Edit
            </a>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="details-container">
                <div class="detail-row">
                    <div class="detail-label">ID:</div>
                    <div>${book.id}</div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Title:</div>
                    <div>${book.title}</div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">ISBN:</div>
                    <div>${book.isbn}</div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Author:</div>
                    <div><a href="/authors/${book.authorId}">${book.authorName}</a></div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Genre:</div>
                    <div>${book.genre}</div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Publication Date:</div>
                    <div>
                        <c:if test="${not empty book.publicationDate}">
                            ${book.publicationDate}
                        </c:if>
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Price:</div>
                    <div>
                        <c:if test="${not empty book.price}">
                            <fmt:formatNumber value="${book.price}" type="currency" currencySymbol="$" />
                        </c:if>
                    </div>
                </div>

                <div class="detail-row">
                    <div class="detail-label">Description:</div>
                    <div style="white-space: pre-line;">${book.description}</div>
                </div>

                <hr>

                <div class="mt-4">
                    <a href="/books/edit/${book.id}" class="btn btn-primary">
                        <i class="fas fa-edit"></i> Edit Book
                    </a>
                    <a href="/authors/${book.authorId}" class="btn btn-info">
                        <i class="fas fa-user"></i> View Author
                    </a>
                </div>
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
