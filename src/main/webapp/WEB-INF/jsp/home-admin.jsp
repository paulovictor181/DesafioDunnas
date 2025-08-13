<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Home - Admin</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Usuários</h1>
    </div>
    <div class="card">
        <div class="card-header">
            <h4>Usuários Cadastrados</h4>
        </div>
        <div class="card-body">
            <form action="<c:url value='/admin/home'/>" method="get" class="mb-3">
                <div class="input-group">
                    <input type="text" name="filtro" class="form-control" placeholder="Filtrar por nome de usuário..." value="${filtro}">
                    <button type="submit" class="btn btn-dark">Filtrar</button>
                </div>
            </form>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Usuário</th>
                    <th>Tipo</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="usuario" items="${usuarios}">
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.usuario}</td>
                        <td>${usuario.tipo}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>