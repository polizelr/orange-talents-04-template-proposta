package br.com.zupacademy.rafaela.proposta.models.TravelNotice;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class TravelNotice {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "travel_notice_sequence"
    )
    @SequenceGenerator(
            name= "travel_notice_sequence",
            sequenceName = "travel_notice_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String destination;

    @Column(
            nullable = false,
            columnDefinition = "date"
    )
    private Date arrivalDate;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private LocalDateTime noticedAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "cardId",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "card_id_fk")
    )
    private Card card;

    public TravelNotice(String destination, Date arrivalDate, String ipAddress, String userAgent, Card card) {
        this.destination = destination;
        this.arrivalDate = arrivalDate;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.card = card;
    }

    @Deprecated
    public TravelNotice() {
    }

    public Long getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getNoticedAt() {
        return noticedAt;
    }

    public Card getCard() {
        return card;
    }
}
