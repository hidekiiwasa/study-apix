package com.github.hidekiiwasa.study_apix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hidekiiwasa.study_apix.dto.ProdutoRequestCreate;
import com.github.hidekiiwasa.study_apix.model.Produto;
import com.github.hidekiiwasa.study_apix.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto save(ProdutoRequestCreate dto) {

        Produto produto = new Produto();
        produto.setNome(dto.getNome());

        return produtoRepository.save(produto);
    }
}