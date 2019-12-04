package br.com.bandtec.bora.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(value = "searchEngine")
public class Pesquisa {
    @Id
    public ObjectId _id;

    private Long idEvento;

   @Indexed(name = "titulo")
   @TextIndexed
    private String titulo;

   @Indexed(name = "descricao")
   @TextIndexed
    private String descricao;

   @Indexed(name = "participantes")
   @TextIndexed
   private List<String> participantes;
//
   @Indexed(name = "organizador")
   @TextIndexed
    private String organizador;

    public String get_id() {
        return _id.toHexString();
    }
}
