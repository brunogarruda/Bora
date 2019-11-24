package br.com.bandtec.bora.model.enums;

public enum AvaliacaoEnum {

    ESTRELA0(0),
    ESTRELA1(1),
    ESTRELA2(2),
    ESTRELA3(3),
    ESTRELA4(4),
    ESTRELA5(5);
 
    public int estrela;

    AvaliacaoEnum(int nota) {
        estrela = nota;
    }
}