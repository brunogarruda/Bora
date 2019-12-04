package br.com.bandtec.bora.pesquisa.services;

import br.com.bandtec.bora.core.model.Pesquisa;
import br.com.bandtec.bora.core.repository.PesquisaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PesquisaService {
    private final PesquisaRepositorio repositorio;
    private final MongoOperations operations;

    public List<Pesquisa> busca(String text){
        TextQuery textQuery = TextQuery.queryText(new TextCriteria().matchingAny(text));
        List<Pesquisa> result = operations.find(textQuery,Pesquisa.class,"pesquisa-engine");
        Optional<List<Pesquisa>> pesquisa = Optional.empty();

        if(result.isEmpty()) pesquisa = repositorio.searchEngine(text);
        if(pesquisa.isPresent())repositorio.findAll();
        return result;
    }
}
