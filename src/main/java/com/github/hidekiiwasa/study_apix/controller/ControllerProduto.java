package com.github.hidekiiwasa.study_apix.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hidekiiwasa.study_apix.dto.ProdutoRequestCreate;
import com.github.hidekiiwasa.study_apix.dto.ProdutoRequestUpdate;
import com.github.hidekiiwasa.study_apix.dto.ProdutoResponse;
import com.github.hidekiiwasa.study_apix.model.Produto;
import com.github.hidekiiwasa.study_apix.service.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ControllerProduto {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> create(@RequestBody ProdutoRequestCreate dto) {
        return ResponseEntity.status(201).body(new ProdutoResponse().toDto(produtoService.save(dto)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponse> update(@PathVariable Long id, @RequestBody ProdutoRequestUpdate dto) {
        return produtoService.update(id, dto)
                    .map(produto -> {
                        return ResponseEntity.status(200).body(new ProdutoResponse().toDto(produto));
                    })
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> findAll() {
        return ResponseEntity.ok(produtoService.findAll().stream()
        .map(produto -> {
            return new ProdutoResponse().toDto(produto);
        })
        .collect(Collectors.toList())); 
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable Long id) {
        return produtoService.findById(id)
                    .map(produto -> {
                        return ResponseEntity.status(200).body(new ProdutoResponse().toDto(produto));
                    })
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean result = produtoService.deleteById(id);

        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}