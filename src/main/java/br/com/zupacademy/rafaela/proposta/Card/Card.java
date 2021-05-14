package br.com.zupacademy.rafaela.proposta.Card;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public Card(String cardNumber, LocalDateTime createdAt) {
        this.cardNumber = cardNumber;
        this.createdAt = createdAt;
    }

    @Deprecated
    public Card() {
    }
}
