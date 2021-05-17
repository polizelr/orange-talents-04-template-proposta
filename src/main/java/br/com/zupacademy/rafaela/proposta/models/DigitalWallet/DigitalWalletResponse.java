package br.com.zupacademy.rafaela.proposta.models.DigitalWallet;

import br.com.zupacademy.rafaela.proposta.utils.enums.DigitalWalletEnum;

import java.time.LocalDateTime;

public class DigitalWalletResponse {
    private Long id;

    private String email;

    private DigitalWalletEnum digitalWallet;

    private LocalDateTime associatedAt = LocalDateTime.now();


    public DigitalWalletResponse(DigitalWallet digitalWallet) {
        this.id = digitalWallet.getId();
        this.email = digitalWallet.getEmail();
        this.digitalWallet = digitalWallet.getDigitalWallet();
        this.associatedAt = digitalWallet.getAssociatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public DigitalWalletEnum getDigitalWallet() {
        return digitalWallet;
    }

    public LocalDateTime getAssociatedAt() {
        return associatedAt;
    }
}
