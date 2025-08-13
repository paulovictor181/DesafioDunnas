CREATE TABLE pagamentos (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    metodo_pagamento VARCHAR(20) NOT NULL,
    data_pagamento TIMESTAMP NOT NULL,
    CONSTRAINT fk_pagamento_cliente FOREIGN KEY (cliente_id) REFERENCES clientes (id) ON DELETE CASCADE
);