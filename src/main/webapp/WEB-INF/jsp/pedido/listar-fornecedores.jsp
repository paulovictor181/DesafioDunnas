<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Novo Pedido - Selecionar Fornecedor</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3>Novo Pedido: Selecione um Fornecedor</h3>
        </div>
        <div class="list-group list-group-flush">
            <c:forEach var="fornecedor" items="${fornecedores}">
                <a href="<c:url value='/cliente/pedidos/fornecedor/${fornecedor.id}'/>" class="list-group-item list-group-item-action">
                        ${fornecedor.nome}
                </a>
            </c:forEach>
        </div>
        <div class="card-footer">
            <a href="<c:url value='/cliente/home'/>" class="btn btn-secondary">Voltar</a>
        </div>
    </div>
</div>
</body>
</html>