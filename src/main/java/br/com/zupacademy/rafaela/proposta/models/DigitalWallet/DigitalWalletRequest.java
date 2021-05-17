package br.com.zupacademy.rafaela.proposta.models.DigitalWallet;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.utils.enums.DigitalWalletEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class DigitalWalletRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private DigitalWalletEnum carteira;

    public DigitalWalletRequest(String email, DigitalWalletEnum carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public DigitalWallet convert(Card card) {
        return new DigitalWallet(email, carteira, card);
    }

    public DigitalWalletEnum getCarteira() {
        return carteira;
    }
}
