CREATE TABLE tiposAnimais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL UNIQUE,
    tipo_alimentacao VARCHAR(20) NOT NULL,
    descricao VARCHAR(100)
);

CREATE TABLE fazendeiro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(16) NOT NULL UNIQUE,
    sexo CHAR(1) NOT NULL,
    idade INT NOT NULL,
    saldo DECIMAL(10,2) NOT NULL DEFAULT 1,
    nivel INT NOT NULL DEFAULT 1
);

CREATE TABLE produtoAnimais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_produto VARCHAR(20) NOT NULL,
    nome VARCHAR(20) NOT NULL UNIQUE,
    preco_unitario DECIMAL(10,2) NOT NULL
);

CREATE TABLE rancho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL UNIQUE,
    nivel INT NOT NULL DEFAULT 1,
    fk_id_fazendeiro INT NULL,
    FOREIGN KEY (fk_id_fazendeiro) REFERENCES fazendeiro(id) ON DELETE CASCADE
);

CREATE TABLE estoque_animais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fk_id_tipo_animal INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    fk_id_rancho INT NOT NULL,
    FOREIGN KEY (fk_id_tipo_animal) REFERENCES tiposAnimais(id),
    FOREIGN KEY (fk_id_rancho) REFERENCES rancho(id)
);

CREATE TABLE animais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(16) DEFAULT NULL,
    sexo CHAR(1) NOT NULL,
    peso FLOAT NOT NULL,
    estado_saude ENUM('Saudável', 'Doente', 'Recuperando') NOT NULL,
    data_nascimento DATE NOT NULL,
    fk_id_tipo_animal INT NOT NULL,
    fk_id_rancho INT NOT NULL,
    FOREIGN KEY (fk_id_tipo_animal) REFERENCES tiposAnimais(id),
    FOREIGN KEY (fk_id_rancho) REFERENCES rancho(id) ON DELETE CASCADE
);

CREATE TABLE animais_produtoAnimais (
    fk_id_animais INT NOT NULL,
    fk_id_produto_animais INT NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (fk_id_animais, fk_id_produto_animais),
    FOREIGN KEY (fk_id_animais) REFERENCES animais(id) ON DELETE CASCADE,
    FOREIGN KEY (fk_id_produto_animais) REFERENCES produtoAnimais(id) ON DELETE CASCADE
);

CREATE TABLE locais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    capacidade INT NOT NULL,
    tipo_alimentacao VARCHAR(20) NOT NULL,
    nivel INT NOT NULL DEFAULT 1,
    tipo_local ENUM('Galinheiro', 'Pasto', 'Aprisco') NOT NULL,
    fk_id_rancho INT NOT NULL,
    FOREIGN KEY (fk_id_rancho) REFERENCES rancho(id) ON DELETE CASCADE
);

CREATE TABLE insumos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL UNIQUE,
    marca VARCHAR(20) NOT NULL,
    descricao VARCHAR(100),
    tipo_insumo ENUM('Insumo', 'Produto') NOT NULL
);

CREATE TABLE estoque_insumos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    fk_id_insumo INT NOT NULL,
    fk_id_rancho INT NOT NULL,
    FOREIGN KEY (fk_id_insumo) REFERENCES insumos(id) ON DELETE CASCADE,
    FOREIGN KEY (fk_id_rancho) REFERENCES rancho(id) ON DELETE CASCADE
);

CREATE TABLE lojaUniversal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL UNIQUE,
    descricao VARCHAR(100)
);

CREATE TABLE loja_insumos (
    fk_id_loja_universal INT NOT NULL,
    fk_id_insumo INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    quantidade_disponivel INT NOT NULL,
    PRIMARY KEY (fk_id_loja_universal, fk_id_insumo),
    FOREIGN KEY (fk_id_loja_universal) REFERENCES lojaUniversal(id) ON DELETE CASCADE,
    FOREIGN KEY (fk_id_insumo) REFERENCES insumos(id) ON DELETE CASCADE
);

CREATE TABLE loja_animais (
    fk_id_loja_universal INT NOT NULL,
    fk_id_tipo_animal INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    quantidade_disponivel INT NOT NULL,
    PRIMARY KEY (fk_id_loja_universal, fk_id_tipo_animal),
    FOREIGN KEY (fk_id_loja_universal) REFERENCES lojaUniversal(id) ON DELETE CASCADE,
    FOREIGN KEY (fk_id_tipo_animal) REFERENCES tiposAnimais(id) ON DELETE CASCADE
);

CREATE TABLE alimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    horarioAlimentacao DATETIME NOT NULL, 
    status ENUM('Completa', 'Pendente') NOT NULL, 
    fk_id_animais INT NOT NULL,
    fk_id_insumos INT NOT NULL, 
    FOREIGN KEY (fk_id_animais) REFERENCES animais(id) ON DELETE CASCADE, 
    FOREIGN KEY (fk_id_insumos) REFERENCES insumos(id) ON DELETE CASCADE 
);

CREATE TABLE transacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_transacao DATETIME NOT NULL,
    tipo_transacao VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    produto VARCHAR(20) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    tipo_item VARCHAR(20) NOT NULL,
    fk_id_animais INT DEFAULT NULL,
    fk_id_rancho INT DEFAULT NULL,
    fk_id_fazendeiro INT NOT NULL,
    FOREIGN KEY (fk_id_animais) REFERENCES animais(id) ON DELETE SET NULL,
    FOREIGN KEY (fk_id_rancho) REFERENCES rancho(id) ON DELETE SET NULL,
    FOREIGN KEY (fk_id_fazendeiro) REFERENCES fazendeiro(id) ON DELETE CASCADE
);

CREATE TABLE animais_locais (
    fk_id_animais INT NOT NULL,
    fk_id_locais INT NOT NULL,
    PRIMARY KEY (fk_id_animais, fk_id_locais),
    FOREIGN KEY (fk_id_animais) REFERENCES animais(id) ON DELETE CASCADE,
    FOREIGN KEY (fk_id_locais) REFERENCES locais(id) ON DELETE CASCADE
);

CREATE TABLE estoque_produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fk_id_produto INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    fk_id_fazendeiro INT NOT NULL,
    FOREIGN KEY (fk_id_produto) REFERENCES produtoAnimais(id),
    FOREIGN KEY (fk_id_fazendeiro) REFERENCES fazendeiro(id) ON DELETE CASCADE
);