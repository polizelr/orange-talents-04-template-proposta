package br.com.zupacademy.rafaela.proposta.models.Proposal;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.models.Card.CardResponse;

import java.math.BigDecimal;
import java.util.Optional;

public class ProposalResponse {
    private Long id;
    private String document;
    private String email;
    private String name;
    private String address;
    private BigDecimal salary;
    private Optional<CardResponse> card;


    public ProposalResponse(Proposal proposal){
        this.id = proposal.getId();
        this.document = proposal.getDocument();
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
        this.card = proposal.getCard() != null ? Optional.of(new CardResponse(proposal.getCard())) : Optional.empty();
    }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Optional<CardResponse> getCard() {
        return card;
    }
}
