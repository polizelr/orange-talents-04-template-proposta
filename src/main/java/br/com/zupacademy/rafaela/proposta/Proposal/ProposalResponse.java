package br.com.zupacademy.rafaela.proposta.Proposal;

import java.math.BigDecimal;

public class ProposalResponse {
    private Long id;
    private String document;
    private String email;
    private String name;
    private String address;
    private BigDecimal salary;

    public ProposalResponse(Proposal proposal){
        this.id = proposal.getId();
        this.document = proposal.getDocument();
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
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
}
