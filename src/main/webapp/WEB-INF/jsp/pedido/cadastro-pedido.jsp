<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Novo Pedido - ${fornecedor.nome}</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3>Montar Pedido - Fornecedor: ${fornecedor.nome}</h3>
        </div>
        <form action="<c:url value='/cliente/pedidos/criar'/>" method="post">
            <input type="hidden" name="fornecedorId" value="${fornecedor.id}">
            <div class="card-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Produto</th>
                        <th>Descrição</th>
                        <th>Preço Unitário</th>
                        <th style="width: 15%;">Quantidade</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="produto" items="${produtos}">
                        <tr>
                            <td>${produto.nome}</td>
                            <td>${produto.descricao}</td>
                            <td>R$ ${produto.preco}</td>
                            <td>
                                <input type="number" name="quantidades[${produto.id}]" class="form-control" value="0" min="0">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer text-end">
                <a href="<c:url value='/cliente/pedidos/novo'/>" class="btn btn-secondary">Voltar</a>
                <button type="submit" class="btn btn-dark">Finalizar Pedido</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>