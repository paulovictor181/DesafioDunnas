CREATE TABLE fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    usuario_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_usuario_fornecedor FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);