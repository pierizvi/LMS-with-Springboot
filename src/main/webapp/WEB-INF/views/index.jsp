<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .jumbotron {
            background-color: #f8f9fa;
            padding: 2rem;
            border-radius: 0.3rem;
            margin-bottom: 2rem;
        }
        .card {
            margin-bottom: 1.5rem;
            box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
            transition: transform 0.3s;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .card-footer {
            background-color: rgba(0, 0, 0, 0.03);
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
            <li class="nav-item">
                <a class="nav-link" href="/books/with-authors">Books with Authors</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-4">
    <div class="jumbotron">
        <h1 class="display-4">Welcome to the Library Management System</h1>
        <p class="lead">Manage your books and authors with this simple application.</p>
        <hr class="my-4">
        <p>Choose an option below to get started.</p>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Books Management</h5>
                    <p class="card-text">Manage the books in your library. Add new books, update existing ones, or view book details.</p>
                </div>
                <div class="card-footer">
                    <a href="/books" class="btn btn-primary">View All Books</a>
                    <a href="/books/new" class="btn btn-success">Add New Book</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Authors Management</h5>
                    <p class="card-text">Manage the authors in your library. Add new authors, update their information, or view author details.</p>
                </div>
                <div class="card-footer">
                    <a href="/authors" class="btn btn-primary">View All Authors</a>
                    <a href="/authors/new" class="btn btn-success">Add New Author</a>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Special Feature</h5>
                    <p class="card-text">View books with their authors using our special join query feature.</p>
                </div>
                <div class="card-footer">
                    <a href="/books/with-authors" class="btn btn-info">View Books with Authors</a>
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
</body>
</html>