<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Acesso Negado</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container text-center mt-5">
    <h1 class="display-1">403</h1>
    <h2>Acesso Negado</h2>
    <p class="lead">Você não tem permissão para acessar esta página.</p>
    <a href="<c:url value='/'/>" class="btn btn-primary">Voltar para a Home</a>
</div>
</body>
</html>