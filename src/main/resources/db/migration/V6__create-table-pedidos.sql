CREATE TABLE pedidos (
     id BIGSERIAL PRIMARY KEY,
     cliente_id BIGINT NOT NULL,
     fornecedor_id BIGINT NOT NULL,
     cupom_id BIGINT,
     valor_total NUMERIC(10, 2) NOT NULL,
     status VARCHAR(20) NOT NULL,
     data_pedido TIMESTAMP NOT NULL,
     CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES clientes (id),
     CONSTRAINT fk_pedido_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedores (id),
     CONSTRAINT fk_pedido_cupom FOREIGN KEY (cupom_id) REFERENCES cupons (id)
);