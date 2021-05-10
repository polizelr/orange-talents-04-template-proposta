package br.com.zupacademy.rafaela.proposta.Proposal;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposal {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "proposal_sequence"
    )
    @SequenceGenerator(
            name= "proposal_sequence",
            sequenceName = "proposal_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private BigDecimal salary;

    public Proposal(String document, String email, String name, String address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    @Deprecated
    public Proposal() {
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
