package io.github.fatec.introducao.controller;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

record PessoaRequest(String id, String nome, String telefone, String endereco) {}
record PessoaResponse(String id, String nome) {}

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    // BD na memória RAM
    private static final Map<String, PessoaRequest> bancoDeDados = new HashMap<>();

    // POST: Recebe Nome, Telefone e Endereço -> Retorna ID e Nome
    @PostMapping
    public PessoaResponse criar(@RequestBody PessoaRequest request) {
        String novoId = UUID.randomUUID().toString();
        
        PessoaRequest novaPessoa = new PessoaRequest(novoId, request.nome(), request.telefone(), request.endereco());
        bancoDeDados.put(novoId, novaPessoa);
        
        return new PessoaResponse(novoId, request.nome());
    }

    // PUT: Recebe ID, Nome, Telefone e Endereço -> Retorna ID e Nome
    @PutMapping
    public PessoaResponse atualizar(@RequestBody PessoaRequest request) {
        if (bancoDeDados.containsKey(request.id())) {
            bancoDeDados.put(request.id(), request); 
            return new PessoaResponse(request.id(), request.nome());
        }
        return new PessoaResponse("ERRO", "ID não encontrado no sistema");
    }

    // DELETE: Recebe o ID -> Retorna a String pedida
    @DeleteMapping
    public String deletar(@RequestBody PessoaRequest request) {
        if (bancoDeDados.containsKey(request.id())) {
            bancoDeDados.remove(request.id());
            return "Usuario: " + request.id() + " Deletado";
        }
        return "Erro: O ID " + request.id() + " não existe";
    }

    // GET: Utilizado apenas para listagem
    @GetMapping
    public Collection<PessoaRequest> listar() {
        return bancoDeDados.values();
    }
}