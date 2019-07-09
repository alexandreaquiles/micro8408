insert into eats_pagamento.pagamento
    (id, valor, nome, numero, expiracao, codigo, status, forma_de_pagamento_id, pedido_id)
      select id, valor, nome, numero, expiracao, codigo, status, forma_de_pagamento_id, pedido_id
        from eats.pagamento;
