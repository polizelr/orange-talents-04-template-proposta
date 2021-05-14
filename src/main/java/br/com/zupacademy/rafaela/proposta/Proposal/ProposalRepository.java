package br.com.zupacademy.rafaela.proposta.Proposal;

import br.com.zupacademy.rafaela.proposta.Card.Card;
import br.com.zupacademy.rafaela.proposta.utils.enums.ProposalStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Optional<Proposal> findProposalByDocument(String document);

    Set<Proposal> findProposalByStatusAndCard(ProposalStatusEnum status, Card card);
}
