<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registrar Novo Usuário</title>
    <link href="<c:url value="/webjars/bootstrap/5.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h1 class="text-center mb-4">Registrar</h1>

            <c:if test="${not empty erro}">
                <div class="alert alert-danger">${erro}</div>
            </c:if>

            <form method="post" action="<c:url value='/registrar'/>">
                <h5 class="mt-4">Dados de Acesso</h5>
                <hr>
                <div class="mb-3">
                    <label for="usuario" class="form-label">Nome de Usuário:</label>
                    <input type="text" id="usuario" name="usuario" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha:</label>
                    <input type="password" id="senha" name="senha" class="form-control" required />
                </div>

                <h5 class="mt-4">Tipo de Conta</h5>
                <hr>
                <div class="mb-3">
                    <label for="tipo" class="form-label">Eu sou:</label>
                    <select id="tipo" name="tipo" class="form-select" required onchange="mostrarCamposAdicionais()">
                        <option value="" selected disabled>Selecione...</option>
                        <c:forEach var="tipoUsuario" items="${tipos}">
                            <option value="${tipoUsuario}">${tipoUsuario}</option>
                        </c:forEach>
                    </select>
                </div>

                <div id="camposCliente" style="display: none;">
                    <h5 class="mt-4">Dados do Cliente</h5>
                    <hr>
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome Completo:</label>
                        <input type="text" id="nome" name="nome" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF:</label>
                        <input type="text" id="cpf" name="cpf" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <label for="dataNascimento" class="form-label">Data de Nascimento:</label>
                        <input type="date" id="dataNascimento" name="dataNascimento" class="form-control" />
                    </div>
                </div>

                <div id="camposFornecedor" style="display: none;">
                    <h5 class="mt-4">Dados do Fornecedor</h5>
                    <hr>
                    <div class="mb-3">
                        <label for="nomeFornecedor" class="form-label">Nome do Fornecedor:</label>
                        <input type="text" id="nomeFornecedor" name="nomeFornecedor" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <label for="cnpj" class="form-label">CNPJ:</label>
                        <input type="text" id="cnpj" name="cnpj" class="form-control" />
                    </div>
                </div>

                <div class="d-grid mt-4">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </div>
            </form>
            <div class="text-center mt-3">
                <a href="<c:url value='/login'/>">Já tem uma conta? Faça login</a>
            </div>
        </div>
    </div>
</div>

<script>
    function mostrarCamposAdicionais() {
        var tipo = document.getElementById('tipo').value;
        var camposCliente = document.getElementById('camposCliente');
        var camposFornecedor = document.getElementById('camposFornecedor');

        // Esconde ambos os grupos por padrão
        camposCliente.style.display = 'none';
        camposFornecedor.style.display = 'none';

        // Desmarca os campos como obrigatórios
        document.getElementById('nome').required = false;
        document.getElementById('cpf').required = false;
        document.getElementById('dataNascimento').required = false;
        document.getElementById('nomeFornecedor').required = false;
        document.getElementById('cnpj').required = false;


        if (tipo === 'CLIENTE') {
            camposCliente.style.display = 'block';
            document.getElementById('nome').required = true;
            document.getElementById('cpf').required = true;
            document.getElementById('dataNascimento').required = true;
        } else if (tipo === 'FORNECEDOR') {
            camposFornecedor.style.display = 'block';
            document.getElementById('nomeFornecedor').required = true;
            document.getElementById('cnpj').required = true;
        }
    }
</script>

<script src="<c:url value="/webjars/bootstrap/5.3.7/js/bootstrap.bundle.min.js"/>"></script>
</body>
</html>