package br.com.zupacademy.rafaela.proposta.models.Card;

import java.time.LocalDateTime;

public class CardResponse {
    private Long id;

    private String cardNumber;

    private LocalDateTime createdAt;

    public CardResponse(Card card){
        this.id = card.getId();
        this.cardNumber = card.getCardNumber();
        this.createdAt = card.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
