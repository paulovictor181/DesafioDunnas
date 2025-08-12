CREATE TABLE produtos (
      id BIGSERIAL PRIMARY KEY,
      nome VARCHAR(255) NOT NULL,
      descricao TEXT,
      preco NUMERIC(10, 2) NOT NULL,
      fornecedor_id BIGINT NOT NULL,
      CONSTRAINT fk_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedores (id) ON DELETE CASCADE
);