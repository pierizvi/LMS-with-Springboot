<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bad Request - Library Management System</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .error-container {
            margin-top: 100px;
            text-align: center;
        }
        .error-code {
            font-size: 8rem;
            font-weight: bold;
            color: #dc3545;
        }
        .error-message {
            font-size: 1.5rem;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="error-container">
        <div class="error-code">400</div>
        <div class="error-message">Bad Request</div>
        <p>${message}</p>
        <a href="javascript:history.back()" class="btn btn-secondary">Go Back</a>
        <a href="/" class="btn btn-primary">Go to Home</a>
    </div>
</div>
</body>
</html>