<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Question</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height centered">
        <svg class="q-icon" xmlns="http://www.w3.org/2000/svg" width="240" height="240" viewBox="0 0 24 24">
            <path d="M12 5.177l8.631 15.823h-17.262l8.631-15.823zm0-4.177l-12 22h24l-12-22zm-1 9h2v6h-2v-6zm1 9.75c-.689 0-1.25-.56-1.25-1.25s.561-1.25 1.25-1.25 1.25.56 1.25 1.25-.561 1.25-1.25 1.25z"/>
        </svg>
        <h2><c:out value="${message}"/></h2>
        <h2 class="color-header text-uppercase center">Na pewno usunąć?</h2>
        <div class="q-btn-cont">
            <a href="${okAction}" class="q-button btn btn-danger rounded-0 text-light m-1">Usuń</a>
            <a href="${cancelAction}" class="q-button btn btn-info rounded-0 text-light m-1">Anuluj</a>
        </div>
    </div>
</div>
</body>
</html>
