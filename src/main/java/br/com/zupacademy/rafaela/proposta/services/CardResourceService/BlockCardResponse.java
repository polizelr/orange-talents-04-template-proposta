package br.com.zupacademy.rafaela.proposta.services.CardResourceService;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockCardResponse {
    private String resultado;

    public BlockCardResponse(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
