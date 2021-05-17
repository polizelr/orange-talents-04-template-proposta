package br.com.zupacademy.rafaela.proposta.services.CardResourceService;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.models.DigitalWallet.DigitalWallet;

public class DigitalWalletFeignRequest {
    private String email;
    private String carteira;

    public DigitalWalletFeignRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
