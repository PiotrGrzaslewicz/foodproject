<%--
  Created by IntelliJ IDEA.
  User: juliaszumowska
  Date: 28/04/2021
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">
        <div class="dashboard-menu">
            <div class="menu-item border-dashed">
                <a href="/app/recipe/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="/app/plan/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj plan</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis do planu</span>
                </a>
            </div>
        </div>

        <div class="dashboard-alerts">
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">Liczba przepisów: ${numberAddedRecipes} </span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">Liczba planów: ${numberAddedPlans}</span>
            </div>
        </div>
    </div>
    <div class="m-4 p-4 border-dashed">
        <h2 class="dashboard-content-title">
            <span>Ostatnio dodany plan:</span> ${plan.name}
        </h2>
        <c:forEach items="${details}" var="entry">
            <table class="table">
                <thead>
                <tr class="d-flex">
                    <th class="col-2">${entry.key.name}</th>
                    <th class="col-7"></th>
                    <th class="col-1"></th>
                    <th class="col-2"></th>
                </tr>
                </thead>
                <tbody class="text-color-lighter">
                <c:forEach items="${entry.value}" var="meal">
                    <tr class="d-flex">
                        <td class="col-2">${meal.mealName}</td>
                        <td class="col-7">${meal.recipeName}</td>

                        <td class="col-2 center">
                            <a href="/app/recipe/details?id=${meal.recipeId}"
                               class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:forEach>

    </div>
</div>
</body>
</html>
