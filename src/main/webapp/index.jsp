<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<h2>${dialogInfo.getHeaderName()}</h2>
<c:choose>
    <c:when test="${dialogInfo.isLeaf()}">
        <button type="button" class="btn btn-secondary btn-md" onclick="window.location='/start?namePlayer=${namePlayer}'">Начать игру заново ?</button>
    </c:when>
    <c:otherwise>
        <form method="get" action="reload">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="flexRadio" id="flexRadio1" value="0">
                <label class="form-check-label" for="flexRadio1">${dialogInfo.getFirstLabel()}</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="flexRadio" id="flexRadio2" value="1">
                <label class="form-check-label" for="flexRadio2">${dialogInfo.getSecondLabel()}</label>
            </div>
            <button type="submit" class="btn btn-secondary btn-md">Ответить</button>
        </form>
    </c:otherwise>
</c:choose>
<p class="text-start fw-bold">Статистика:</p>
<p class="text-start">IP address: ${address}</p>
<p class="text-start">Имя в игре: ${namePlayer}</p>
<p class="text-start">Количество игр: ${countGames}</p>
</body>
</html>