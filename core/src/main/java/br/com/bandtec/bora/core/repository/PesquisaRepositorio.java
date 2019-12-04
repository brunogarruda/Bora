package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.Pesquisa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PesquisaRepositorio extends MongoRepository<Pesquisa,String> {
    @Query("{$or : [{'nome': { $regex: ?0, $options:'i' }},{'descricao': { $regex: ?0, $options:'i' }},{'participantes': { $regex: ?0, $options:'i' }}, {'organizador': { $regex: ?0, $options:'i' }}]}")
    public Optional<List<Pesquisa>> searchEngine(String text);

}
