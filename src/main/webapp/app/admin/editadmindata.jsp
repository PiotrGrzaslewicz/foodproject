<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Edit User Data</title>
</head>
<body>

<div class="m-4 p-3 width-medium text-color-darker">
    <div class="m-4 border-dashed view-height">

        <form action="/app/edit-admin-data" method="post">
            <div class="mt-4 ml-4 mr-4">
                <div class="row border-bottom border-3">
                    <div class="col"><h3 class="color-header text-uppercase">Edytuj dane</h3></div>
                    <div class="col d-flex justify-content-end mb-2">
                        <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz
                        </button>
                    </div>
                </div>


                <div style="color: #FF0000;">${errorMsg}</div>
                <table class="table borderless">
                    <tbody>
                    <tr class="d-flex">
                        <th scope="row" class="col-2"><h4>Imię</h4></th>
                        <td class="col-7">
                            <input class="w-100 p-1" name="firstName" value="${fn:escapeXml(admin.firstName)}">
                        </td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2"><h4>Nazwisko</h4></th>
                        <td class="col-7">
                            <input class="w-100 p-1" name="lastName" value="${fn:escapeXml(admin.lastName)}">
                        </td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2"><h4>Email</h4></th>
                        <td class="col-3">
                            <input class="p-1 w-100" type="text" name="email" value="${fn:escapeXml(admin.email)}">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>

</body>
</html>
