CREATE OR REPLACE FUNCTION adicionar_saldo(
    p_cliente_id BIGINT,
    p_valor NUMERIC(10, 2)
)
RETURNS VOID AS $$
BEGIN
    IF p_valor <= 0 THEN
        RAISE EXCEPTION 'O valor a ser adicionado deve ser maior que zero.';
END IF;

    IF NOT EXISTS (SELECT 1 FROM clientes WHERE id = p_cliente_id) THEN
        RAISE EXCEPTION 'Cliente com ID % não encontrado.', p_cliente_id;
END IF;

UPDATE clientes
SET saldo = saldo + p_valor
WHERE id = p_cliente_id;

END;
$$ LANGUAGE plpgsql;