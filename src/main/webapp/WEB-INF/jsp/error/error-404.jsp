<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Página Não Encontrada</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container text-center mt-5">
    <h1 class="display-1">404</h1>
    <h2>Página Não Encontrada</h2>
    <p class="lead">Desculpe, a página que você está procurando não existe.</p>
    <a href="<c:url value='/'/>" class="btn btn-primary">Voltar para a Home</a>
</div>
</body>
</html>