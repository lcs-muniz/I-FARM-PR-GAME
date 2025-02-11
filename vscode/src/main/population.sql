INSERT INTO tiposAnimais (nome, tipo_alimentacao, descricao) VALUES
('Galinha', 'Ração', 'Produz ovos frescos'),
('Vaca', 'Feno', 'Fornece leite'),
('Ovelha', 'Feno', 'Produz lã');

INSERT INTO lojaUniversal (nome, descricao) VALUES
('Loja Universal', 'Loja principal para compras básicas');

INSERT INTO loja_animais (fk_id_loja_universal, fk_id_tipo_animal, preco_unitario, quantidade_disponivel) VALUES
(1, 1, 50.00, 10),  -- Galinha
(1, 2, 500.00, 5),   -- Vaca
(1, 3, 200.00, 8);   -- Porco

INSERT INTO insumos (nome, marca, descricao, tipo_insumo) VALUES
('Ração', 'AgroTop', 'Ração para aves', 'Insumo'),
('Feno', 'Campo Verde', 'Alimento para bovinos e ovelha', 'Insumo'),
('Remédio', 'VetSaúde', 'Remédio para animais', 'Insumo');

INSERT INTO loja_insumos (fk_id_loja_universal, fk_id_insumo, preco_unitario, quantidade_disponivel) VALUES
(1, 1, 25.00, 100),  -- Ração
(1, 2, 45.00, 50),   -- Feno
(1, 3, 100.00, 30);   -- Remédio(teste)

INSERT INTO produtoAnimais (tipo_produto, nome, preco_unitario) VALUES
('Ovo', 'Ovo de Galinha', 2.00),
('Leite', 'Leite Fresco', 15.00),
('Lã', 'Lã de ovelha', 8.00);

-- Utilizar o comando abaixo para testar a venda de produtos animais,
-- porém arrumar o id do fazendeiro, lembrando que o id do fazendeiro
-- é criado ao iniciar o servidor.
INSERT INTO estoque_produtos (fk_id_produto, quantidade, fk_id_fazendeiro) VALUES
(1, 50, 1),   -- 50 ovos de galinha no estoque do fazendeiro 1
(2, 20, 1),   -- 20 unidades de leite fresco no estoque do fazendeiro 1
(3, 30, 1);   -- 30 unidades de lã de ovelha no estoque do fazendeiro 1