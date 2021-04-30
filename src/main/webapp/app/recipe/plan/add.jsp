<%--
  Created by IntelliJ IDEA.
  User: juliaszumowska
  Date: 29/04/2021
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dodaj przepis do planu</title>
</head>
<body>
<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <form action="/app/recipe/plan/add" method="post">
            <div class="mt-4 ml-4 mr-4">
                <div class="row border-bottom border-3">
                    <div class="col"><h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3></div>
                    <div class="col d-flex justify-content-end mb-2">
                        <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="selectplan" class="col-sm-2 label-size col-form-label">
                        Wybierz plan
                    </label>
                    <div class="col-sm-3">
                        <label id="selectplan">
                            <select name="plan">
                                <c:forEach items="${plan}" var="plan">
                                    <option value="${plan.id}">${plan.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Nazwa posiłku
                    </label>
                    <div class="col-sm-10">
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Nazwa posiłku</th>
                            <td class="col-7"> <textarea class="w-100 p-1" rows="5" name="mealname"></textarea></td>
                        </tr>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="number" class="col-sm-2 label-size col-form-label">
                        Numer posiłku
                    </label>
                    <div class="col-sm-2">
                        <input type="number" min="1" class="form-control" value="1" id="number" name="displayorder" placeholder="Numer posiłku">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="selectrecipe" class="col-sm-2 label-size col-form-label">
                        Przepis
                    </label>
                    <div class="col-sm-4">
                        <label id="selectrecipe">
                            <select name="recipe">
                                <c:forEach items="${recipe}" var="recipe">
                                    <option value="${recipe.id}">${recipe.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 label-size col-form-label">
                        Dzień
                    </label>
                    <div class="col-sm-2">
                        <select name="day">
                            <option value="1">poniedziałek</option>
                            <option value="2">wtorek</option>
                            <option value="3">środa</option>
                            <option value="4">czwartek</option>
                            <option value="5">piątek</option>
                            <option value="6">sobota</option>
                            <option value="7">niedziela</option>
                        </select>
                    </div>
                </div>
            </div>
        </form>
    </div>


</div>
</form>


</div>
</div>
</body>
</html>
