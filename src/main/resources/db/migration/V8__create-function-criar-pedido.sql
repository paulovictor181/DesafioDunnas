CREATE OR REPLACE FUNCTION criar_pedido(
    p_cliente_id BIGINT,
    p_fornecedor_id BIGINT,
    p_cupom_id BIGINT,
    p_produto_ids BIGINT[],
    p_quantidades INT[]
)
RETURNS BIGINT AS $$
DECLARE
v_pedido_id BIGINT;
    v_valor_total NUMERIC(10, 2) := 0;
    v_preco_produto NUMERIC(10, 2);
    v_item_count INT := 0;
BEGIN
    IF NOT EXISTS (SELECT 1 FROM clientes WHERE id = p_cliente_id) THEN
        RAISE EXCEPTION 'Cliente com ID % não encontrado', p_cliente_id;
END IF;

    IF NOT EXISTS (SELECT 1 FROM fornecedores WHERE id = p_fornecedor_id) THEN
        RAISE EXCEPTION 'Fornecedor com ID % não encontrado', p_fornecedor_id;
END IF;

    IF p_cupom_id IS NOT NULL AND NOT EXISTS (SELECT 1 FROM cupons WHERE id = p_cupom_id) THEN
        RAISE EXCEPTION 'Cupom com ID % não encontrado', p_cupom_id;
END IF;

    IF array_length(p_produto_ids, 1) IS NULL OR array_length(p_produto_ids, 1) = 0 THEN
        RAISE EXCEPTION 'O pedido não pode estar vazio.';
END IF;

INSERT INTO pedidos (cliente_id, fornecedor_id, cupom_id, status, data_pedido, valor_total)
VALUES (p_cliente_id, p_fornecedor_id, p_cupom_id, 'PENDENTE', NOW(), 0)
    RETURNING id INTO v_pedido_id;

FOR i IN 1..array_length(p_produto_ids, 1) LOOP
        IF p_quantidades[i] > 0 THEN
            v_item_count := v_item_count + 1;
SELECT preco INTO v_preco_produto FROM produtos WHERE id = p_produto_ids[i];

INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario)
VALUES (v_pedido_id, p_produto_ids[i], p_quantidades[i], v_preco_produto);

v_valor_total := v_valor_total + (v_preco_produto * p_quantidades[i]);
END IF;
END LOOP;

    IF v_item_count = 0 THEN
        RAISE EXCEPTION 'O pedido deve ter pelo menos um item com quantidade maior que zero.';
END IF;

UPDATE pedidos SET valor_total = v_valor_total WHERE id = v_pedido_id;

RETURN v_pedido_id;
END;
$$ LANGUAGE plpgsql;