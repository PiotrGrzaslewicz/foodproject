<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 03.05.2021
  Time: 01:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Użytkownicy</title>
</head>
<body>

<div class="m-4 p-3 width-medium">
    <div class="m-4 p-3 border-dashed view-height">

        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="/app/dashboard" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <table class="table">
                <thead>
                <tr class="d-flex">
                    <th class="col-1">ID</th>
                    <th class="col-3">IMIĘ</th>
                    <th class="col-6">NAZWISKO</th>
                    <th class="col-2 center">AKCJE</th>
                </tr>
                </thead>
                <tbody class="text-color-lighter">

                <c:forEach var="admin" items="${adminMap}">
                    <tr class="d-flex">
                        <td class="col-1">${admin.key.id}</td>
                        <td class="col-3">${admin.key.firstName}</td>
                        <td class="col-6">${admin.key.lastName}</td>
                        <c:choose>
                            <c:when test="${admin.value==1}">
                                <td class="col-2 center">
                                    <a href="/app/modifyadmin?action=block&id=${admin.key.id}"
                                       class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                                </td>
                            </c:when>
                            <c:when test="${admin.value==0}">
                                <td class="col-2 center">
                                    <a href="/app/modifyadmin?action=unblock&id=${admin.key.id}"
                                       class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Odblokuj</a>
                                </td>
                            </c:when>
                            <c:when test="${admin.value==3}">
                            <td class="col-2 center">
                                Administrator
                            </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</div>

</body>
</html>
