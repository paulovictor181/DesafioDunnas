CREATE TABLE itens_pedido (
      id BIGSERIAL PRIMARY KEY,
      pedido_id BIGINT NOT NULL,
      produto_id BIGINT NOT NULL,
      quantidade INTEGER NOT NULL,
      preco_unitario NUMERIC(10, 2) NOT NULL,
      CONSTRAINT fk_item_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos (id) ON DELETE CASCADE,
      CONSTRAINT fk_item_produto FOREIGN KEY (produto_id) REFERENCES produtos (id)
);