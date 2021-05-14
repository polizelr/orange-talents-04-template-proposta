package br.com.zupacademy.rafaela.proposta.services.CardResourceService;

import br.com.zupacademy.rafaela.proposta.Card.Card;

import java.time.LocalDateTime;

public class CardResourceResponse {
    private String id;
    private LocalDateTime emitidoEm;

    public CardResourceResponse(String id, LocalDateTime emitidoEm) {
        this.id = id;
        this.emitidoEm = emitidoEm;
    }

    public String getId() {
        return id;
    }

    public Card convert() {
        return new Card(id, emitidoEm);
    }
}
