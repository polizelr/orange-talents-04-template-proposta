package br.com.zupacademy.rafaela.proposta.models.Block;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
    Boolean existsBlockByCard(Card card);
}
