package br.com.bandtec.bora.pesquisa.controllers;

import br.com.bandtec.bora.core.model.Pesquisa;
import br.com.bandtec.bora.pesquisa.services.PesquisaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/api/eventos/pesquisa")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PesquisaController {
    private final PesquisaService service;

    @GetMapping("{text}")
    public ResponseEntity<List<Pesquisa>> pesquisarEventos(@PathVariable(value = "text")String text){
        List<Pesquisa> result = service.busca(text);
        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(service.busca(text));
    }
}
