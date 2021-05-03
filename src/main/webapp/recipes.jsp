<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body class="recipes-section">

<%@ include file="jspf/header.jsp" %>

<section>
    <div class="row padding-small page-width">
        <div class="top-element">
            <div class="flex-row">
                <i class="fas fa-users icon-users"></i>
                <h1>Przepisy naszych użytkowników:</h1>
            </div>
            <form action="/recipes" method="get" class="flex-row align-baseline">
                <label class="m-3">Szukaj w:</label>
                <input type="checkbox" name="columns" value="name" class="checkbox" ${name}>
                <label>tytuł</label>
                <input type="checkbox" name="columns" value="description" class="checkbox" ${description}>
                <label>opis</label>
                <input type="checkbox" name="columns" value="ingredients" class="checkbox" ${ingredients}>
                <label>składniki</label>
                <input type="checkbox" name="columns" value="preparation" class="checkbox" ${preparation}>
                <label>przygotowanie</label>
                <div class="flex-row align-center m-2">
                    <input type="text" name="searchTxt" value="<c:out value="${queryTxt}"/>" class="search-height">
                    <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4 search-height"><i
                            class="fa fa-search"></i></button>
                </div>
            </form>
        </div>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>

<section class="mr-4 ml-4">
    <table class="table">
        <thead>
        <tr class="d-flex text-color-darker">
            <th scope="col" class="col-1">ID</th>
            <th scope="col" class="col-5">NAZWA</th>
            <th scope="col" class="col-5">OPIS</th>
            <th scope="col" class="col-1">AKCJE</th>
        </tr>
        </thead>
        <tbody class="text-color-lighter">
        <c:forEach items="${results}" var="recipe">
            <tr class="d-flex">
                <th scope="row" class="col-1">${recipe.id}</th>
                <td class="col-5">
                        <c:out value="${recipe.name}"/>
                </td>
                <td class="col-5"><c:out value="${recipe.description}"/>
                </td>
                <td class="col-1"><a href="/details?id=${recipe.id}" class="btn btn-info rounded-0 text-light">Szczegóły</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page-cont m-5">
        <c:forEach begin="1" end="${pagesCount}" step="1" var="page">
            <c:choose>
                <c:when test="${page eq actualPage}">
                    <span class="btn actual-page rounded-0 pt-0 pb-0 m-1">${page}</span>
                </c:when>
                <c:otherwise>
                    <a href="/recipes?page=${page}" class="btn btn-color rounded-0 pt-0 pb-0 m-1">${page}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</section>

<%@ include file="jspf/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
