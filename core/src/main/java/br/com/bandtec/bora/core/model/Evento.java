package br.com.bandtec.bora.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbd_evento")
public class Evento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    private String titulo;

//    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataInicio;
//    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataFim;
//    @JsonFormat(pattern = "HH:mm")
    private String horaInicio;
//    @JsonFormat(pattern = "HH:mm")
    private String horaFim;

    private String descricao;

    private String senha;

    private String endereco;

    private String rua;

    private String numero;

    private String cep;

    private String bairro;

    private Double latitude;

    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "fk_capa_evento_id")
    private ImageModel capaDoEvento;

    @ManyToOne
    @JoinColumn(name = "fk_categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonBackReference
    @JoinColumn(name = "organizador_id")
    private Usuario organizador;

    @Transient
    private List<Usuario> participantes;

//    public void formatarData(){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//    }

    public Evento(Long idEvento) {
        this.idEvento=idEvento;
    }

    public Evento(String titulo, String descricao,String rua,
                  String numero, String cep,
                  String bairro, Double latitude,
                  Double longitude,String dataInicio , String dataFim,
                  String horaInicio,String horaFim, Long categoria, Long apelido) {
        this.titulo = titulo;
        this.descricao=descricao;
        this.dataInicio=dataInicio;
        this.dataFim=dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.rua=rua;
        this.numero=numero;
        this.cep=cep;
        this.bairro=bairro;
        this.latitude=latitude;
        this.longitude=longitude;
        this.categoria= new Categoria(categoria);
        this.organizador = new Usuario(apelido);
    }
    public Evento(String titulo, String rua,
                  String numero, String cep,
                  String bairro, Double latitude,
                  Double longitude, String apelido) {
        this.titulo=titulo;
        this.rua=rua;
        this.numero=numero;
        this.cep=cep;
        this.bairro=bairro;
        this.latitude=latitude;
        this.longitude=longitude;
        this.organizador=new Usuario(apelido);
    }
    public Evento(String titulo, String rua,
                  String numero, String cep,
                  String bairro, Double latitude,
                  Double longitude, Long idUsuario) {
        this.titulo=titulo;
        this.rua=rua;
        this.numero=numero;
        this.cep=cep;
        this.bairro=bairro;
        this.latitude=latitude;
        this.longitude=longitude;
        this.organizador=new Usuario(idUsuario);
    }

    public Evento(String titulo, String descricao, String rua, String numero,
                  String cep, String bairro, Double latitude, Double longitude,
                  ImageModel imageModel,Long idCategoria, Long idUsuario) {
        this.titulo=titulo;
        this.descricao=descricao;
        this.rua=rua;
        this.numero=numero;
        this.cep=cep;
        this.bairro=bairro;
        this.latitude=latitude;
        this.longitude=longitude;
        this.capaDoEvento=imageModel;
        this.categoria=new Categoria(idCategoria);
        this.organizador= new Usuario(idUsuario);
    }

    public Evento(String titulo, String descricao, String rua, String numero,
                  String cep, String bairro, Double latitude, Double longitude
                  ,Long idCategoria, Long idUsuario) {
        this.titulo=titulo;
        this.descricao=descricao;
        this.rua=rua;
        this.numero=numero;
        this.cep=cep;
        this.bairro=bairro;
        this.latitude=latitude;
        this.longitude=longitude;
        this.categoria=new Categoria(idCategoria);
        this.organizador= new Usuario(idUsuario);
    }


    public Evento(String titulo, String descricao, String dataInicio, String dataFim, String horaInicio, String horaFim, String rua, String numero, String cep, String bairro, Double latitude, Double longitude, ImageModel imageModel, Long idCategoria, Long idUsuario) {
        this.titulo=titulo;
        this.descricao=descricao;
        this.dataInicio=dataInicio;
        this.dataFim=dataFim;
        this.horaInicio=horaInicio;
        this.horaFim=horaFim;
        this.rua=rua;
        this.numero=numero;
        this.cep=cep;
        this.bairro=bairro;
        this.latitude=latitude;
        this.longitude=longitude;
        this.categoria=new Categoria(idCategoria);
        this.organizador= new Usuario(idUsuario);
    }

    public Evento(String nome) {
        this.categoria= new Categoria(nome);
    }

    public Evento(Long idEvento,String titulo, String descricao, String dataInicio, String dataFim, String horaInicio, String horaFim, String rua, String numero, String cep, String bairro, Double latitude, Double longitude,ImageModel imageModel, Long idCategoria) {
        this.idEvento=idEvento;
        this.titulo=titulo;
        this.descricao=descricao;
        this.dataInicio=dataInicio;
        this.dataFim=dataFim;
        this.horaInicio=horaInicio;
        this.horaFim=horaFim;
        this.rua=rua;
        this.numero=numero;
        this.cep=cep;
        this.bairro=bairro;
        this.latitude=latitude;
        this.longitude=longitude;
        this.capaDoEvento=imageModel;
        this.categoria=new Categoria(idCategoria);
    }
    public Evento(Long idEvento,String titulo, String descricao, String dataInicio, String dataFim, String horaInicio, String horaFim, String rua, String numero, String cep, String bairro, Double latitude, Double longitude, Long idCategoria) {
        this.idEvento=idEvento;
        this.titulo=titulo;
        this.descricao=descricao;
        this.dataInicio=dataInicio;
        this.dataFim=dataFim;
        this.horaInicio=horaInicio;
        this.horaFim=horaFim;
        this.rua=rua;
        this.numero=numero;
        this.cep=cep;
        this.bairro=bairro;
        this.latitude=latitude;
        this.longitude=longitude;
        this.categoria=new Categoria(idCategoria);
    }

}
