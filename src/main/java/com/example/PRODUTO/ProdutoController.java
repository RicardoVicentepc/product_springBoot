package com.example.PRODUTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("produtos/")
public class ProdutoController {
    static ArrayList<Produto> produtos = new ArrayList<>();
    @GetMapping
    public static ResponseEntity<ArrayList<Produto>> exibirProdutos(){
        return produtos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(produtos);
    }

    @GetMapping("estoque/{qtdEstoque}")
    public static ArrayList<Produto> exibirProdutos(@PathVariable int qtdEstoque){
        ArrayList<Produto> produtosComEstoqueGrandes = new ArrayList<>();

        for (Produto produto : produtos){
            if(produto.getQtdEstoque() <= qtdEstoque && produto.getQtdEstoque() > 10){produtosComEstoqueGrandes.add(produto);}
        }
        return produtosComEstoqueGrandes;
    }

    @PostMapping
    public static ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
        produtos.add(produto);
        return ResponseEntity.status(201).body(produto);
    }

    @PutMapping("{indice}")
    public static Produto atualizarProduto(@PathVariable int indice,@RequestBody Produto produto){
        produtos.set(indice, produto);
        return produto;
    }

    @DeleteMapping("{indice}")
    public static void deletarProduto(@PathVariable int indice){
        produtos.remove(indice);
    }

}
