package br.com.bandtec.bora.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "pesquisaEngine")
public class Pesquisa {
    @Id
    public ObjectId _id;

//    @Indexed(name = "titulo")
//    @TextIndexed
    private String titulo;

//    @Indexed(name = "descricao")
//    @TextIndexed
    private String descricao;

//    @Indexed(name = "participantes")
//    @TextIndexed
//    private List<String> participantes;
//
//    @Indexed(name = "organizador")
//    @TextIndexed
    private Usuario organizador;

    public String get_id() {
        return _id.toHexString();
    }
}
