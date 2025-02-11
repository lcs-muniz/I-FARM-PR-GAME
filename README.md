# Protótipo de Gestão de Fazenda

## Observação:
 
 Este código não está finalizado e pode sofrer alterações e melhorias. A conexão com o banco de dados está hardcoded para facilitar o desenvolvimento do protótipo, para o funcionamento do código deve alterar as credenciais (usuário/senha) e o endereço do banco de dados no arquivo DatabaseConnection.java, localizado na pasta com/fazenda/util.

### 1. Objetivo Geral
Este protótipo foi desenvolvido para um atividade avaliada da disciplina de Banco de Dados. A aplicação simula a gestão de um jogo de fazenda, onde o usuário (fazendeiro) pode gerenciar animais, comprar insumos, vender produtos gerados pelos animais, alimentar os animais e acompanhar as transações financeiras.

A aplicação está organizada em camadas:

    Model: Representa as entidades do sistema (Fazendeiro, Animal, Insumos, etc.).
    DAO: Contém os comandos SQL para acesso e manipulação dos dados.
    Service: Contém a lógica de negócio, orquestrando as operações entre os DAOs.
    View: Responsável pela interação com o usuário (menus, exibições e leitura de inputs).

### 2. Configuração e Pré-Requisitos
    - Java
    
### 2.1. Criação das Tabelas

Antes de compilar e executar o código, certifique-se de que todas as tabelas do banco de dados foram criadas, o código das tabelas a serem criadas está no arquivo createTables.sql.
As tabelas principais são:

    tiposAnimais
    fazendeiro
    produtoAnimais
    rancho
    estoque_animais
    animais
    animais_produtoAnimais
    locais
    insumos
    estoque_insumos
    lojaUniversal
    loja_insumos
    loja_animais
    alimentacao
    transacao
    animais_locais
    estoque_produtos

Verifique se as tabelas estão criadas conforme o esquema esperado.
Logo após insira uma população inicial para o teste, os inserts estão no arquivo population.sql, certifique que as tabelas a seguir contenha dados:
    
    tiposAnimais
    lojaUniversal
    loja_animais
    insumos
    loja_insumos
    produtoAnimais

### 2.2. Configuração da Conexão

O arquivo de configuração da conexão com o banco está localizado em:
com/fazenda/util/DatabaseConnection.java

## Atenção:

    Altere as credenciais (user, password e URL) de acordo com o seu ambiente.
    Este protótipo utiliza uma conexão hardcoded para simplificar o desenvolvimento.

## 3. Instruções de Compilação e Execução
### 3.1. Navegue até a pasta correta

Antes de compilar, entre na pasta que contém a classe App.java e o JAR do MySQL Connector.
No caso, abra o terminal e execute:
```
cd vscode/src/main/java/com/fazenda
```
### 3.2. Compilação
Para Linux (Bash):

Utilize o seguinte comando para compilar todos os arquivos .java:

``` bash
javac -cp ".:mysql-connector-j-9.2.0.jar" -d . $(find . -type f -name "*.java")
```
Para Windows (PowerShell):

Utilize o seguinte comando para compilar todas as classes Java:
``` powershell
javac -cp ".;mysql-connector-j-9.2.0.jar" -d . (Get-ChildItem -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName)
```
### 3.3. Execução
Para Linux (Bash):

Para executar o programa, use:

``` bash 
java -cp ".:mysql-connector-j-9.2.0.jar" com.fazenda.App
```
Para Windows (PowerShell):

Após a compilação, execute o programa com:

``` powershell 
java -cp ".;mysql-connector-j-9.2.0.jar" com.fazenda.App
```