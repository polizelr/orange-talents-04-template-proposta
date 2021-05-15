package br.com.zupacademy.rafaela.proposta.models.Card;

import br.com.zupacademy.rafaela.proposta.models.Biometry.Biometry;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "card_number_unique", columnNames = "cardNumber")
        }
)
public class Card {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_sequence"
    )
    @SequenceGenerator(
            name= "card_sequence",
            sequenceName = "card_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "card", orphanRemoval = true)
    private List<Biometry> biometrics;

    public Card(String cardNumber, LocalDateTime createdAt) {
        this.cardNumber = cardNumber;
        this.createdAt = createdAt;
    }

    @Deprecated
    public Card() {
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
