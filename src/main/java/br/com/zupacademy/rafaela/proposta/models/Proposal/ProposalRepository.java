package br.com.zupacademy.rafaela.proposta.models.Proposal;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.utils.enums.ProposalStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Boolean existsProposalByDocument(String document);

    Set<Proposal> findProposalByStatusAndCard(ProposalStatusEnum status, Card card);
}
