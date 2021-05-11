package br.com.zupacademy.rafaela.proposta.Proposal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Optional<Proposal> findProposalByDocument(String document);
}
