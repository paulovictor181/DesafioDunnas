<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Home - Cliente</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="modal fade" id="modalAdicionarSaldo" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel">Adicionar Saldo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<c:url value='/cliente/saldo/adicionar'/>" method="post">
                    <div class="mb-3">
                        <label for="valor" class="form-label">Valor a adicionar (R$):</label>
                        <input type="number" step="0.01" min="0.01" name="valor" class="form-control" required/>
                    </div>
                    <div class="mb-3">
                        <label for="metodoPagamento" class="form-label">Método de Pagamento:</label>
                        <select id="metodoPagamento" name="metodoPagamento" class="form-select" required>
                            <option value="" selected disabled>Selecione...</option>
                            <option value="PIX">PIX</option>
                            <option value="CARTAO_CREDITO">Cartão de Crédito</option>
                            <option value="CARTAO_DEBITO">Cartão de Débito</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-dark">Confirmar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Meus Pedidos Pendentes</h1>
    </div>

    <c:if test="${not empty sucesso}">
        <div class="alert alert-success">${sucesso}</div>
    </c:if>
    <c:if test="${not empty erro}">
        <div class="alert alert-danger">${erro}</div>
    </c:if>

    <div class="card mb-4">
        <div class="card-body d-flex justify-content-between align-items-center">
            <div>
                <h5 class="card-title mb-0">Meu Saldo</h5>
                <p class="card-text fs-4 fw-bold">
                    <fmt:setLocale value="pt-BR"/>
                    <fmt:formatNumber value="${cliente.saldo}" type="currency"/>
                </p>
            </div>
            <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#modalAdicionarSaldo">
                + Adicionar Saldo
            </button>
        </div>
    </div>

    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h4>Pedidos em Aberto</h4>
            <a href="<c:url value='/cliente/pedidos/novo'/>" class="btn btn-dark">
                + Fazer Novo Pedido
            </a>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Fornecedor</th>
                    <th>Valor (R$)</th>
                    <th>Data</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="pedido" items="${pedidos}">
                    <tr>
                        <td>${pedido.id}</td>
                        <td>${pedido.fornecedor.nome}</td>
                        <td>
                            <fmt:setLocale value="pt-BR"/>
                            <fmt:formatNumber value="${pedido.valor_total}" type="currency"/></td>
                        <td>
                            <fmt:formatNumber value="${pedido.dataPedido.dayOfMonth}" minIntegerDigits="2"/>/<fmt:formatNumber value="${pedido.dataPedido.monthValue}" minIntegerDigits="2"/>/${pedido.dataPedido.year}
                            <fmt:formatNumber value="${pedido.dataPedido.hour}" minIntegerDigits="2"/>:<fmt:formatNumber value="${pedido.dataPedido.minute}" minIntegerDigits="2"/>
                        </td>
                        <td><span class="badge bg-warning text-dark">${pedido.status}</span></td>
                        <td>
                            <form action="<c:url value='/cliente/pedidos/pagar/${pedido.id}'/>" method="post" style="display:inline;">
                                <button type="submit" class="btn btn-sm btn-success">Pagar</button>
                            </form>
                            <form action="<c:url value='/cliente/pedidos/cancelar/${pedido.id}'/>" method="post" style="display:inline;">
                                <button type="submit" class="btn btn-sm btn-outline-danger">Cancelar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty pedidos}">
                    <tr>
                        <td colspan="6" class="text-center">Nenhum pedido pendente encontrado.</td>
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