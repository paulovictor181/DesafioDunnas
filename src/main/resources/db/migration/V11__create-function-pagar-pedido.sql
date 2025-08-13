CREATE OR REPLACE FUNCTION pagar_pedido(
    p_pedido_id BIGINT,
    p_cliente_id BIGINT
)
RETURNS VOID AS $$
DECLARE
v_pedido RECORD;
    v_cliente_saldo NUMERIC(10, 2);
BEGIN

SELECT * INTO v_pedido FROM pedidos WHERE id = p_pedido_id;

IF NOT FOUND THEN
        RAISE EXCEPTION 'Pedido com ID % não encontrado.', p_pedido_id;
END IF;

    IF v_pedido.cliente_id != p_cliente_id THEN
        RAISE EXCEPTION 'Você não tem permissão para pagar este pedido.';
END IF;

    IF v_pedido.status != 'PENDENTE' THEN
        RAISE EXCEPTION 'Este pedido não está mais pendente (Status: %).', v_pedido.status;
END IF;

SELECT saldo INTO v_cliente_saldo FROM clientes WHERE id = p_cliente_id;
IF v_cliente_saldo < v_pedido.valor_total THEN
        RAISE EXCEPTION 'Saldo insuficiente para realizar o pagamento.';
END IF;

UPDATE clientes
SET saldo = saldo - v_pedido.valor_total
WHERE id = p_cliente_id;

UPDATE pedidos
SET status = 'CONCLUIDO'
WHERE id = p_pedido_id;

END;
$$ LANGUAGE plpgsql;