<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Home - Cliente</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Meus Pedidos Pendentes</h1>
        <form action="<c:url value='/logout'/>" method="post">
            <button type="submit" class="btn btn-danger">Sair</button>
        </form>
    </div>

    <form action="<c:url value='/cliente/home'/>" method="get" class="mb-3">
        <div class="input-group">
            <input type="text" name="filtro" class="form-control" placeholder="Filtrar pedidos..." value="${filtro}">
            <button type="submit" class="btn btn-primary">Filtrar</button>
        </div>
    </form>

    <%-- A tabela de pedidos viria aqui --%>
    <p><i>Funcionalidade de pedidos a ser implementada.</i></p>
</div>
</body>
</html>