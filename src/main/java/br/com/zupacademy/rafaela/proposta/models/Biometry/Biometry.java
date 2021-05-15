package br.com.zupacademy.rafaela.proposta.models.Biometry;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometry {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "biometry_sequence"
    )
    @SequenceGenerator(
            name= "biometry_sequence",
            sequenceName = "biometry_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private byte[] fingerprint;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "card_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "card_id_fk")
    )
    private Card card;

    public Biometry(byte[] fingerprint, Card card) {
        this.fingerprint = fingerprint;
        this.card = card;
    }

    @Deprecated
    public Biometry() {

    }

    public Long getId() {
        return id;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Card getCard() {
        return card;
    }
}
