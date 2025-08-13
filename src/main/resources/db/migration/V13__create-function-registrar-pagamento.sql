CREATE OR REPLACE FUNCTION registrar_pagamento(
    p_cliente_id BIGINT,
    p_valor NUMERIC(10, 2),
    p_metodo_pagamento VARCHAR(20)
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

INSERT INTO pagamentos (cliente_id, valor, metodo_pagamento, data_pagamento)
VALUES (p_cliente_id, p_valor, p_metodo_pagamento, NOW());

END;
$$ LANGUAGE plpgsql;