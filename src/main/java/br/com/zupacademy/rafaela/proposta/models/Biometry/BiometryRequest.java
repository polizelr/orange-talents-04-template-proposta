package br.com.zupacademy.rafaela.proposta.models.Biometry;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometryRequest {

    @NotBlank
    private String fingerprint ;

    public BiometryRequest(@JsonProperty("fingerprint") String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometry convert(Card card) {
        byte[] decodedFingerprint = Base64.getDecoder().decode(fingerprint);
        return new Biometry(decodedFingerprint, card);
    }

    public boolean isValid() {
        return fingerprint.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
    }
}
