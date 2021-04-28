<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 27.04.2021
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Recipe Details</title>
</head>
<body>
<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">
            <div class="row border-bottom border-3">
                <div class="col"><h3 class="color-header text-uppercase">Szczegóły przepisu</h3></div>
                <div class="col d-flex justify-content-end mb-2"><a href="/app/dashboard" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a></div>
            </div>

            <table class="table borderless">
                <tbody>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Nazwa Przepisu</th>
                    <td class="col-7">
                        ${recipe.name}
                    </td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Opis przepisu</th>
                    <td class="col-7">${recipe.description}</td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                    <td class="col-7">
                        ${recipe.preparationTime}
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="row d-flex">
                <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                <div class="col-2"></div>
                <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
            </div>
            <div class="row d-flex">
                <div class="col-5 p-4">
                    <p>${recipe.preparation}</p>
                </div>
                <div class="col-2"></div>

                <ul class="col-5 p-4 list-unstyled">
                    <li>${recipe.ingredients}</li>

<%--                   TODO LISTOWANIE SklADNIKOW Z TABELI - > zrobic przeksztalcenie skladnikow na tabele --%>

<%--                    <li>brukselka 300g</li>--%>
<%--                    <li>ziemniaki 500g</li>--%>
<%--                    <li>Fix Naturalnie makaronowa z szynką Knorr 1 szt.</li>--%>
<%--                    <li>średnia cebula 1szt.</li>--%>
<%--                    <li>ząbek czosnku 1szt.</li>--%>
<%--                    <li>kiełbasa np. śląska 500g</li>--%>
<%--                    <li>śmietana 18% 200 ml</li>--%>
<%--                    <li>Rama Smaż jak szef kuchni, wariant klasyczny 4 łyżki</li>--%>
<%--                    <li>gałązka tymianku 1 szt.</li>--%>
                </ul>
            </div>

        </div>
    </div>
</div>
</div>


</body>
</html>
