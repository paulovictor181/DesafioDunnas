<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Meus Créditos</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<jsp:include page="../components/navbar.jsp"/>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Meus Créditos</h1>
    </div>


    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h4>Histórico de Pagamentos</h4>
        </div>
        <div class="card-body">
            <form action="<c:url value='/cliente/relatorios/creditos'/>" method="get" class="mb-3">
                <div class="row g-3">
                    <div class="col-md-5">
                        <label for="dataInicio" class="form-label">Data Início</label>
                        <input type="date" class="form-control" id="dataInicio" name="dataInicio" value="${dataInicio}">
                    </div>
                    <div class="col-md-5">
                        <label for="dataFim" class="form-label">Data Fim</label>
                        <input type="date" class="form-control" id="dataFim" name="dataFim" value="${dataFim}">
                    </div>
                    <div class="col-md-2 d-flex align-items-end">
                        <button type="submit" class="btn btn-dark w-100">Filtrar</button>
                    </div>
                </div>
            </form>

            <table class="table table-striped mt-3">
                <thead>
                <tr>
                    <th>ID da Transação</th>
                    <th>Valor</th>
                    <th>Método</th>
                    <th>Data</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="pagamento" items="${pagamentos}">
                    <tr>
                        <td>${pagamento.id}</td>
                        <td>
                            <fmt:setLocale value="pt-BR"/>
                            <fmt:formatNumber value="${pagamento.valor}" type="currency"/>
                        </td>
                        <td>${pagamento.metodoPagamento}</td>
                        <td>
                            <fmt:formatNumber value="${pagamento.dataPagamento.dayOfMonth}" minIntegerDigits="2"/>/<fmt:formatNumber value="${pagamento.dataPagamento.monthValue}" minIntegerDigits="2"/>/${pagamento.dataPagamento.year}
                            <fmt:formatNumber value="${pagamento.dataPagamento.hour}" minIntegerDigits="2"/>:<fmt:formatNumber value="${pagamento.dataPagamento.minute}" minIntegerDigits="2"/>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty pagamentos}">
                    <tr>
                        <td colspan="4" class="text-center">Nenhum pagamento encontrado.</td>
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