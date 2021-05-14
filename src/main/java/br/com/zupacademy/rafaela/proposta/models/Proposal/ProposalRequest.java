package br.com.zupacademy.rafaela.proposta.models.Proposal;

import br.com.zupacademy.rafaela.proposta.config.validation.ValidCpfOrCnpj;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProposalRequest {

    @NotBlank
    @ValidCpfOrCnpj
    private String document;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Positive
    @NotNull
    private BigDecimal salary;

    public ProposalRequest(String document, String email, String name, String address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposal convert() {
        return new Proposal(document, email, name, address,salary);
    }

    public String getDocument() {
        return document;
    }
}
