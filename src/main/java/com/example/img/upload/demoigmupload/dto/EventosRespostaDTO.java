package com.example.img.upload.demoigmupload.dto;

import com.example.img.upload.demoigmupload.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventosRespostaDTO{
    private Iterable<Evento> eventoList = new ArrayList<>();
}