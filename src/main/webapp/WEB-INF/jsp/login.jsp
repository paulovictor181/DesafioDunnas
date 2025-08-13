<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">

            <div class="d-flex justify-content-center align-items-center py-4 mb-4">
                <img src="https://www.dunnastecnologia.com.br/img/logo-dark.png" alt="Logo Dunnas">
            </div>

            <c:if test="${param.error}">
                <div class="alert alert-danger">
                    Usuário ou senha inválidos.
                </div>
            </c:if>
            <c:if test="${not empty mensagem}">
                <div class="alert alert-success">
                        ${mensagem}
                </div>
            </c:if>

            <form method="post" action="<c:url value='/login'/>">
                <div class="mb-3">
                    <label for="username" class="form-label">Usuário:</label>
                    <input type="text" id="username" name="username" class="form-control" required autofocus />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Senha:</label>
                    <input type="password" id="password" name="password" class="form-control" required />
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-dark">Entrar</button>
                </div>
            </form>
            <div class="text-center mt-3">
                <a href="<c:url value='/registrar'/>">Não tem uma conta? Registre-se</a>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/webjars/bootstrap/5.3.7/js/bootstrap.bundle.min.js"/>"></script>
</body>
</html>