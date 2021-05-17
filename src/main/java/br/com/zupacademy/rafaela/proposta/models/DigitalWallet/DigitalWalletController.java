package br.com.zupacademy.rafaela.proposta.models.DigitalWallet;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.models.Card.CardRepository;
import br.com.zupacademy.rafaela.proposta.services.CardResourceService.CardResourceClient;

import br.com.zupacademy.rafaela.proposta.services.CardResourceService.DigitalWalletFeignRequest;
import br.com.zupacademy.rafaela.proposta.utils.transactions.TransactionExecutor;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/carteiradigital")
public class DigitalWalletController {
    TransactionExecutor executor;
    CardResourceClient cardResourceClient;
    CardRepository cardRepository;
    DigitalWalletRepository digitalWalletRepository;

    public DigitalWalletController(TransactionExecutor executor,
                                   CardResourceClient cardResourceClient,
                                   CardRepository cardRepository,
                                   DigitalWalletRepository digitalWalletRepository) {
        this.executor = executor;
        this.cardResourceClient = cardResourceClient;
        this.cardRepository = cardRepository;
        this.digitalWalletRepository = digitalWalletRepository;
    }

    @PostMapping("{cardId}")
    public ResponseEntity<?> associateDigitalWalllet(@Valid @PathVariable("cardId") Long cardId,
                                                     @RequestBody DigitalWalletRequest request,
                                                     UriComponentsBuilder uriBuilder){
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else if(digitalWalletRepository.existsDigitalWalletByDigitalWalletAndCard(
                request.getCarteira(),
                card.get()
        )){
            return ResponseEntity.unprocessableEntity().build();
        }
        DigitalWallet digitalWallet = request.convert(card.get());

        try{
            DigitalWalletFeignRequest digitalWalletFeignRequest = new DigitalWalletFeignRequest(
                    digitalWallet.getEmail(), digitalWallet.getDigitalWallet().toString()
            );
            cardResourceClient.associateDigitalWallet(card.get().getCardNumber(), digitalWalletFeignRequest);
            executor.saveAndCommit(digitalWallet);
            URI uri = uriBuilder.path("/carteiradigital/{id}").buildAndExpand(digitalWallet.getId()).toUri();
            return ResponseEntity.created(uri).body(new DigitalWalletResponse(digitalWallet));
        }
        catch (FeignException exception){
            return ResponseEntity.unprocessableEntity().build();
        }

    }
}
