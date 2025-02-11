package com.fazenda.service;

import com.fazenda.dao.AnimalDAO;
import com.fazenda.dao.EstoqueProdutoDAO;
import com.fazenda.dao.InsumoDAO;
import com.fazenda.dao.LojaDAO;
import com.fazenda.model.GameSession;
import com.fazenda.model.ItemLoja;
import com.fazenda.model.Transacao;
import com.fazenda.util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LojaService {
    private final LojaDAO lojaDAO = new LojaDAO();
    private final AnimalDAO animalDAO = new AnimalDAO();
    private final Connection connection = DatabaseConnection.getConnection();
    
    private final GameSession session;
    private final TransacaoService transacaoService = new TransacaoService();
    private final FazendeiroService fazendeiroService = new FazendeiroService();
    private final EstoqueProdutoDAO estoqueProdutoDAO = new EstoqueProdutoDAO();
    
    public LojaService(GameSession session) {
        this.session = session;
    }
//
    public void comprarAnimais(Scanner scanner) throws SQLException {
        // Exibir animais disponíveis usando o DAO
        BigDecimal saldoAtual = fazendeiroService.getSaldo(session.getCurrentFarmerId());
        System.out.printf("\nSALDO: R$ %.2f%n\n", saldoAtual);

        System.out.println("Animais Disponíveis:");
        lojaDAO.listarAnimaisDisponiveis().forEach(animal ->
            System.out.printf("- %s: $%.2f (Disponível: %d)%n",
                    animal.getNomeAnimal(),
                    animal.getPrecoUnitario(),
                    animal.getQuantidadeDisponivel())
        );
        
        System.out.print("Digite o nome do animal que deseja comprar (ou 'voltar'): ");
        String animalNome = scanner.nextLine();
        if(animalNome.equalsIgnoreCase("voltar")) return;
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        
        processarCompraAnimal(animalNome, quantidade);
    }
    
    private void processarCompraAnimal(String animalNome, int quantidade) throws SQLException {
        connection.setAutoCommit(false);
        try {
            // Utiliza o DAO para consultar os dados do item da loja
            ItemLoja item = lojaDAO.consultarItemPorNome(animalNome);
            if(item == null) {
                System.out.println("Animal não encontrado na loja.");
                connection.rollback();
                return;
            }
            if(quantidade > item.getQuantidadeDisponivel()) {
                System.out.println("Quantidade indisponível!");
                connection.rollback();
                return;
            }
            
            // Atualizar saldo do fazendeiro
            BigDecimal totalCompra = item.getPrecoUnitario().multiply(new BigDecimal(quantidade));
            System.out.println("Total da compra: " + totalCompra);
            
            // Verificar o saldo atual do fazendeiro e se ele possui saldo suficiente para a compra
            BigDecimal saldoAtual = fazendeiroService.getSaldo(session.getCurrentFarmerId());
            if (saldoAtual.compareTo(totalCompra) < 0) {
                System.out.println("Saldo insuficiente para a compra!");
                connection.rollback();
                return;
            }

            // Atualizar estoque da loja
            lojaDAO.atualizarEstoqueLoja(item.getTipoAnimalId(), quantidade);
            
            // Atualizar o estoque do rancho
            animalDAO.atualizarEstoque(item.getTipoAnimalId(), quantidade, session.getCurrentRanchoId());
            
            // Atualizar o saldo do fazendeiro
            fazendeiroService.atualizarSaldoFazendeiro(session.getCurrentFarmerId(), totalCompra.negate());

            // Registrar a transação usando o TransacaoService
            Transacao transacao = new Transacao();
            transacao.setTipoTransacao("Compra");
            transacao.setQuantidade(quantidade);
            transacao.setProduto(animalNome);
            transacao.setValorTotal(totalCompra);
            transacao.setTipoItem("Animal");
            transacao.setFkIdFazendeiro(session.getCurrentFarmerId());
            transacaoService.registrarTransacao(transacao);
            
            connection.commit();

            // Após o commit, busca e exibe o novo saldo atualizado
            BigDecimal novoSaldo = fazendeiroService.getSaldo(session.getCurrentFarmerId());
            System.out.println("Compra realizada com sucesso!");
            System.out.println("Novo saldo: R$ " + novoSaldo);
        } catch(SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void comprarInsumos(Scanner scanner) throws SQLException {

        BigDecimal saldoAtual = fazendeiroService.getSaldo(session.getCurrentFarmerId());
        System.out.printf("\nSALDO ATUAL: R$ %.2f%n\n", saldoAtual);

        // Exibir insumos disponíveis usando o DAO
        System.out.println("Insumos Disponíveis:");
        lojaDAO.listarInsumosDisponiveis().forEach(insumo -> 
            System.out.printf("- %s, Marca: %s, Preço: $%.2f, Disponível: %d%n",
                insumo.getNome(),
                insumo.getMarca(),
                insumo.getPrecoUnitario(),
                insumo.getQuantidadeDisponivel())
        );
        
        System.out.print("Digite o nome do insumo que deseja comprar (ou 'voltar'): ");
        String insumoNome = scanner.nextLine();
        if (insumoNome.equalsIgnoreCase("voltar")) return;
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        
        processarCompraInsumos(insumoNome, quantidade);
    }
    
    private void processarCompraInsumos(String insumoNome, int quantidade) throws SQLException {
    connection.setAutoCommit(false);
        try {
            // Consultar os dados do insumo na loja
            ItemLoja item = lojaDAO.consultarInsumoPorNome(insumoNome);
            if (item == null) {
                System.out.println("Insumo não encontrado na loja.");
                connection.rollback();
                return;
            }
            if (quantidade > item.getQuantidadeDisponivel()) {
                System.out.println("Quantidade indisponível!");
                connection.rollback();
                return;
            }
        
            // Calcular o custo total da compra
            BigDecimal totalCompra = item.getPrecoUnitario().multiply(new BigDecimal(quantidade));
            System.out.println("Total da compra de insumos: " + totalCompra);

            // Verificar o saldo atual do fazendeiro
            BigDecimal saldoAtual = fazendeiroService.getSaldo(session.getCurrentFarmerId());
            if (saldoAtual.compareTo(totalCompra) < 0) {
                System.out.println("Saldo insuficiente para a compra de insumos!");
                connection.rollback();
                return;
            }

            // Atualizar o estoque da loja para insumos
            lojaDAO.atualizarEstoqueInsumos(item.getTipoInsumoId(), quantidade);
        
            // Atualizar o estoque de insumos no rancho usando o InsumoDAO (crie essa classe conforme mostrado abaixo)
            new InsumoDAO().atualizarEstoqueInsumos(item.getTipoInsumoId(), quantidade, session.getCurrentRanchoId());
        
            // Atualizar o saldo do fazendeiro (deduzindo o custo da compra)
            fazendeiroService.atualizarSaldoFazendeiro(session.getCurrentFarmerId(), totalCompra.negate());
        
            // Registrar a transação de compra de insumos
                Transacao transacao = new Transacao();
                transacao.setTipoTransacao("Compra");
                transacao.setQuantidade(quantidade);
                transacao.setProduto(insumoNome);
                transacao.setValorTotal(totalCompra);
                transacao.setTipoItem("Insumo");
                transacao.setFkIdFazendeiro(session.getCurrentFarmerId());
                transacaoService.registrarTransacao(transacao);
        
                connection.commit();

                // Após o commit, busca e exibe o novo saldo atualizad
                BigDecimal novoSaldo = fazendeiroService.getSaldo(session.getCurrentFarmerId());
                System.out.println("Compra de insumos realizada com sucesso!");
                System.out.println("Novo saldo: R$ " + novoSaldo);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
    }

    // Método para vender produtos que o fazendeiro possui (usando a tabela estoque_produtos)
    public void venderProdutos(Scanner scanner) throws SQLException {
        // Listar os produtos disponíveis no estoque do fazendeiro
        System.out.println("Produtos Disponíveis para Venda (Seu Estoque):");
        List<ItemLoja> produtos = estoqueProdutoDAO.listarProdutosFazendeiro(session.getCurrentFarmerId());
        if (produtos.isEmpty()) {
            System.out.println("Você não possui produtos para vender.");
            return;
        }
        for (ItemLoja produto : produtos) {
            System.out.printf("- %s: R$%.2f (Quantidade: %d)%n",
                    produto.getNome(),
                    produto.getPrecoUnitario(),
                    produto.getQuantidadeDisponivel());
        }
        
        System.out.print("Digite o nome do produto que deseja vender (ou 'voltar'): ");
        String produtoNome = scanner.nextLine().trim();
        if (produtoNome.equalsIgnoreCase("voltar")) return;
        
        System.out.print("Quantidade: ");
        int quantidade;
        try {
            quantidade = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido para a quantidade.");
            return;
        }
        
        processarVendaProdutos(produtoNome, quantidade);
    }

    private void processarVendaProdutos(String produtoNome, int quantidade) throws SQLException {
        connection.setAutoCommit(false);
        try {
            // Consulta os dados do produto no estoque do fazendeiro
            List<ItemLoja> produtos = estoqueProdutoDAO.listarProdutosFazendeiro(session.getCurrentFarmerId());
            ItemLoja item = null;
            for (ItemLoja p : produtos) {
                if (p.getNome().equalsIgnoreCase(produtoNome)) {
                    item = p;
                    break;
                }
            }
            if (item == null) {
                System.out.println("Produto não encontrado no seu estoque para venda.");
                connection.rollback();
                return;
            }
            
            if (quantidade > item.getQuantidadeDisponivel()) {
                System.out.println("Quantidade solicitada (" + quantidade + 
                                   ") maior que a disponível (" + item.getQuantidadeDisponivel() + ").");
                connection.rollback();
                return;
            }
            
            // Calcula o total da venda
            BigDecimal totalVenda = item.getPrecoUnitario().multiply(new BigDecimal(quantidade));
            System.out.println("Total da venda: R$" + totalVenda);
            
            // Atualiza o estoque do produto no fazendeiro (diminui a quantidade vendida)
            boolean atualizado = estoqueProdutoDAO.atualizarEstoqueProduto(item.getTipoInsumoId(), quantidade, session.getCurrentFarmerId());
            if (!atualizado) {
                System.out.println("Erro ao atualizar o estoque do produto.");
                connection.rollback();
                return;
            }
            
            // Atualiza o saldo do fazendeiro (a venda aumenta o saldo)
            fazendeiroService.atualizarSaldoFazendeiro(session.getCurrentFarmerId(), totalVenda);
            
            // Registra a transação de venda
            Transacao transacao = new Transacao();
            transacao.setTipoTransacao("Venda");
            transacao.setQuantidade(quantidade);
            transacao.setProduto(produtoNome);
            transacao.setValorTotal(totalVenda);
            transacao.setTipoItem("Produto");
            transacao.setFkIdFazendeiro(session.getCurrentFarmerId());
            transacaoService.registrarTransacao(transacao);
            
            connection.commit();
            System.out.println("Venda realizada com sucesso!");
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    

}
