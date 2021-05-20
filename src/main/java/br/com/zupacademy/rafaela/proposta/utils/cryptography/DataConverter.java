package br.com.zupacademy.rafaela.proposta.utils.cryptography;

import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.AttributeConverter;

public class DataConverter implements AttributeConverter<String,String> {
    @Override
    public String convertToDatabaseColumn(String s) {
        return Encryptors.text("${proposal.cryptography.secret}", "1357924680").encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return Encryptors.text("${proposal.cryptography.secret}", "1357924680").decrypt(s);
    }
}
