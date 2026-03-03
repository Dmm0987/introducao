package io.github.fatec.introducao.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetController {

    @GetMapping(value = "/teste")
    public String teste() {
        String id = UUID.randomUUID().toString();
        return id;
    }

@RestController
public class PostController {

    @PostMapping(value = "/teste")
    public String postar() {
        return "POST recebido: Recurso criado!";
    }
}

@RestController
public class PutController {

    @PutMapping(value = "/teste")
    public String atualizar() {
        return "PUT recebido: Recurso atualizado!";
    }
}

@RestController
public class DeleteController {

    @DeleteMapping(value = "/teste")
    public String deletar() {
        return "DELETE recebido: Recurso removido!";
    }
}

}
