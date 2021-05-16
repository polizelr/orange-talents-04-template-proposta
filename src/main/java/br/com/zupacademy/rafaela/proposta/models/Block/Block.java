package br.com.zupacademy.rafaela.proposta.models.Block;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Block {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "block_sequence"
    )
    @SequenceGenerator(
            name= "block_sequence",
            sequenceName = "block_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private LocalDateTime blockedAt = LocalDateTime.now();

    @OneToOne(optional = false)
    @JoinColumn(
            name = "cardId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "card_id_fk")
    )
    private Card card;

    public Block(String ipAddress, String userAgent, Card card) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.card = card;
    }

    @Deprecated
    public Block() {
    }

    public Long getId() {
        return id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public Card getCard() {
        return card;
    }
}
