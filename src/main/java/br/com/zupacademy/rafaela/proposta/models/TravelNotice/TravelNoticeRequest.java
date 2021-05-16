package br.com.zupacademy.rafaela.proposta.models.TravelNotice;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class TravelNoticeRequest {
    @NotBlank
    private String destination;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date arrivalDate;

    public TravelNoticeRequest(String destination, Date arrivalData) {
        this.destination = destination;
        this.arrivalDate = arrivalData;
    }

    public String getDestination() {
        return destination;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }
}
