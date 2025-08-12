package com.paulo.desafiodunnas.service;

import com.paulo.desafiodunnas.model.Fornecedor;
import com.paulo.desafiodunnas.model.Produto;
import com.paulo.desafiodunnas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarPorFornecedor(Fornecedor fornecedor) {
        return produtoRepository.findByFornecedorId(fornecedor.getId());
    }

    public List<Produto> buscarPorFornecedorComFiltro(Fornecedor fornecedor, String nome) {
        return produtoRepository.findByFornecedorIdAndNome(fornecedor.getId(), nome);
    }

    public void cadastrarProduto(Produto produto) {
        produtoRepository.save(produto);
    }
}
