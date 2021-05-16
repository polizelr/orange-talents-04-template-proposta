package br.com.zupacademy.rafaela.proposta.services.CardResourceService;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NoticeTravelResponse {
    private String resultado;

    public NoticeTravelResponse(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
