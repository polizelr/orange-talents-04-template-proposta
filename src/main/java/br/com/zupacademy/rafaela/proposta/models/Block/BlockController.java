package br.com.zupacademy.rafaela.proposta.models.Block;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.models.Card.CardRepository;
import br.com.zupacademy.rafaela.proposta.services.CardResourceService.BlockCardRequest;
import br.com.zupacademy.rafaela.proposta.services.CardResourceService.BlockCardResponse;
import br.com.zupacademy.rafaela.proposta.services.CardResourceService.CardResourceClient;
import br.com.zupacademy.rafaela.proposta.utils.transactions.TransactionExecutor;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/bloqueio")
public class BlockController {
    private final BlockRepository blockRepository;

    private final CardRepository cardRepository;

    private final CardResourceClient cardResourceClient;

    private final TransactionExecutor executor;

    public BlockController(BlockRepository blockRepository, CardRepository cardRepository, CardResourceClient cardResourceClient, TransactionExecutor executor) {
        this.blockRepository = blockRepository;
        this.cardRepository = cardRepository;
        this.cardResourceClient = cardResourceClient;
        this.executor = executor;
    }

    @PostMapping("/{cardId}")
    public ResponseEntity<?> blockCard (@PathVariable Long cardId, HttpServletRequest request){
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else if (blockRepository.existsBlockByCard(card.get())){
            return ResponseEntity.unprocessableEntity().build();
        }

        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        Block blockCard = new Block(ipAddress, userAgent , card.get());

        try{
            cardResourceClient.blockCard(card.get().getCardNumber(), new BlockCardRequest());
            executor.saveAndCommit(blockCard);
            return ResponseEntity.ok().build();
        }
        catch (FeignException exception){
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
