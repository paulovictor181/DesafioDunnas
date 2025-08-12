<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Home - Fornecedor</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<div class="modal fade" id="modalAdicionarProduto" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel">Cadastrar Novo Produto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/fornecedor/produtos/add" method="post" modelAttribute="novoProduto">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome do Produto:</label>
                        <form:input path="nome" cssClass="form-control" required="true"/>
                    </div>
                    <div class="mb-3">
                        <label for="descricao" class="form-label">Descrição:</label>
                        <form:textarea path="descricao" cssClass="form-control"/>
                    </div>
                    <div class="mb-3">
                        <label for="preco" class="form-label">Preço (R$):</label>
                        <form:input type="number" step="0.01" path="preco" cssClass="form-control" required="true"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-dark">Adicionar Produto</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Meus Produtos</h1>
        <form action="<c:url value='/logout'/>" method="post">
            <button type="submit" class="btn btn-danger">Sair</button>
        </form>
    </div>

    <c:if test="${not empty sucesso}">
        <div class="alert alert-success">${sucesso}</div>
    </c:if>
    <c:if test="${not empty erro}">
        <div class="alert alert-danger">${erro}</div>
    </c:if>

    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h4>Produtos Cadastrados</h4>
            <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#modalAdicionarProduto">
                + Novo Produto
            </button>
        </div>
        <div class="card-body">
            <form action="<c:url value='/fornecedor/home'/>" method="get" class="mb-3">
                <div class="input-group">
                    <input type="text" name="filtro" class="form-control" placeholder="Filtrar por nome do produto..." value="${filtro}">
                    <button type="submit" class="btn btn-dark">Filtrar</button>
                </div>
            </form>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Preço (R$)</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="produto" items="${produtos}">
                    <tr>
                        <td>${produto.id}</td>
                        <td>${produto.nome}</td>
                        <td>${produto.descricao}</td>
                        <td>${produto.preco}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty produtos}">
                    <tr>
                        <td colspan="4" class="text-center">Nenhum produto encontrado.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="<c:url value="/webjars/bootstrap/5.3.7/js/bootstrap.bundle.min.js"/>"></script>
</body>
</html>