package br.com.zupacademy.rafaela.proposta.Proposal;

import br.com.zupacademy.rafaela.proposta.Card.Card;
import br.com.zupacademy.rafaela.proposta.utils.enums.ProposalStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "document_unique", columnNames = "document")
        }
)
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

    @Enumerated(EnumType.STRING)
    private ProposalStatusEnum status =  ProposalStatusEnum.NAO_ANALISADO;

    @OneToOne
    @JoinColumn(
            name = "card_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "card_id_fk")
    )
    private Card card;

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

    public void setStatus(ProposalStatusEnum status) {
        this.status = status;
    }

    public void setCard(Card card) {
        this.card = card;
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

    public ProposalStatusEnum getStatus() {
        return status;
    }

    public Card getCard() {
        return card;
    }
}
