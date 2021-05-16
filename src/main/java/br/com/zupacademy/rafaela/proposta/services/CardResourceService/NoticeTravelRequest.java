package br.com.zupacademy.rafaela.proposta.services.CardResourceService;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NoticeTravelRequest {
    private String destino;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date validoAte;

    public NoticeTravelRequest(String destino, Date validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Date getValidoAte() {
        return validoAte;
    }
}
