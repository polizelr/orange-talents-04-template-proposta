package br.com.zupacademy.rafaela.proposta.models.DigitalWallet;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.utils.enums.DigitalWalletEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigitalWalletRepository extends JpaRepository<DigitalWallet, Long> {
    Boolean existsDigitalWalletByDigitalWalletAndCard(DigitalWalletEnum digitalWallet,
                                                      Card card);
}
