<%-- DesafioDunnas/src/main/webapp/WEB-INF/jsp/relatorios/compras.jsp --%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Histórico de Compras</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="../components/navbar.jsp"/>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Histórico de Compras</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <h4>Todos os Pedidos</h4>
        </div>
        <div class="card-body">
            <form action="/cliente/relatorios/compras" method="get" class="mb-4">
                <div class="row g-3 align-items-end">
                    <div class="col-md-5">
                        <label for="dataInicio" class="form-label">Data de Início</label>
                        <input type="date" class="form-control" id="dataInicio" name="dataInicio" value="${dataInicio}">
                    </div>
                    <div class="col-md-5">
                        <label for="dataFim" class="form-label">Data de Fim</label>
                        <input type="date" class="form-control" id="dataFim" name="dataFim" value="${dataFim}">
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-dark w-100">Filtrar</button>
                    </div>
                </div>
            </form>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Fornecedor</th>
                    <th>Valor (R$)</th>
                    <th>Data</th>
                    <th>Status</th>
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
                        <td>
                            <c:choose>
                                <c:when test="${pedido.status == 'CONCLUIDO'}">
                                    <span class="badge bg-success">${pedido.status}</span>
                                </c:when>
                                <c:when test="${pedido.status == 'PENDENTE'}">
                                    <span class="badge bg-warning text-dark">${pedido.status}</span>
                                </c:when>
                                <c:when test="${pedido.status == 'CANCELADO'}">
                                    <span class="badge bg-danger">${pedido.status}</span>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty pedidos}">
                    <tr>
                        <td colspan="5" class="text-center">Nenhum pedido encontrado.</td>
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