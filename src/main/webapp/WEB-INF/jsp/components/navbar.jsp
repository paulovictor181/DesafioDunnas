<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <div class="container-fluid">
        <img class="d-inline-block align-text-top m-2" src="https://www.dunnastecnologia.com.br/img/logo-dark.png" alt="Logo Dunnas">

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <%-- Links do Cliente --%>
                <sec:authorize access="hasRole('CLIENTE')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/cliente/home'/>">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/cliente/relatorios/compras'/>">Minhas Compras</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/cliente/relatorios/creditos'/>">Meus Créditos</a>
                    </li>
                </sec:authorize>

                <%-- Links do Fornecedor --%>
                <sec:authorize access="hasRole('FORNECEDOR')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/fornecedor/home'/>">Meus Produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/fornecedor/relatorios/vendas'/>">Minhas Vendas</a>
                    </li>
                </sec:authorize>
                <%-- Links do Admin --%>
                <sec:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/admin/home'/>">Usuários</a>
                    </li>
                </sec:authorize>
            </ul>
            <%-- Botão de Sair --%>
            <form action="<c:url value='/logout'/>" method="post" class="d-flex">
                <button class="btn btn-danger btn-lg" type="submit">Sair</button>
            </form>
        </div>
    </div>
</nav>