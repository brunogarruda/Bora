package com.example.img.upload.demoigmupload.dto;

import com.example.img.upload.demoigmupload.model.Evento;
import com.example.img.upload.demoigmupload.model.Usuario;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EventoJsonSerializer extends JsonSerializer<Evento> {
    @Override
    public void serialize(Evento evento,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(
                "organizador",
                getApelidoOrganizador(evento.getOrganizador()));
        jsonGenerator.writeEndObject();
    }

    private static String getApelidoOrganizador(Usuario usuario){
        String apelido = usuario.getApelido();
        return String.format("%s",apelido);
    }
}
