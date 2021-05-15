package br.com.zupacademy.rafaela.proposta.models.Biometry;

import br.com.zupacademy.rafaela.proposta.config.exception.ApiRequestException;
import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.models.Card.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/biometria")
public class BiometryController {

    private final BiometryRepository biometryRepository;
    private final CardRepository cardRepository;

    public BiometryController(CardRepository cardRepository, BiometryRepository biometryRepository){
        this.cardRepository = cardRepository;
        this.biometryRepository = biometryRepository;
    }

    @PostMapping("/{cardId}")
    @Transactional
    public ResponseEntity<?> addBiometry(@RequestBody @Valid BiometryRequest biometryRequest,
                                         @PathVariable("cardId") Long cardId,
                                         UriComponentsBuilder uriBuilder){
        if(!biometryRequest.isValid()){
            throw new ApiRequestException("Invalid fingerprint", HttpStatus.BAD_REQUEST, "fingerprint");
        }

        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isPresent()){
            Biometry biometry = biometryRequest.convert(card.get());
            biometryRepository.save(biometry);
            URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(biometry.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        throw new ApiRequestException("Card not found", HttpStatus.NOT_FOUND, "cardId");
    }

}
