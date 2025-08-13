CREATE OR REPLACE FUNCTION cancelar_pedido(
    p_pedido_id BIGINT,
    p_cliente_id BIGINT
)
RETURNS VOID AS $$
DECLARE
v_pedido_status VARCHAR(20);
    v_pedido_cliente_id BIGINT;
BEGIN
SELECT status, cliente_id INTO v_pedido_status, v_pedido_cliente_id
FROM pedidos WHERE id = p_pedido_id;

IF NOT FOUND THEN
        RAISE EXCEPTION 'Pedido com ID % não encontrado.', p_pedido_id;
END IF;

    IF v_pedido_cliente_id != p_cliente_id THEN
        RAISE EXCEPTION 'Você não tem permissão para cancelar este pedido.';
END IF;

    IF v_pedido_status != 'PENDENTE' THEN
        RAISE EXCEPTION 'Este pedido não pode mais ser cancelado (Status: %).', v_pedido_status;
END IF;

UPDATE pedidos
SET status = 'CANCELADO'
WHERE id = p_pedido_id;

END;
$$ LANGUAGE plpgsql;