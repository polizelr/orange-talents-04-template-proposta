package br.com.zupacademy.rafaela.proposta.models.Block;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.models.Card.CardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/bloqueio")
public class BlockController {
    private final BlockRepository blockRepository;

    private final CardRepository cardRepository;

    public BlockController(BlockRepository blockRepository, CardRepository cardRepository){
        this.blockRepository = blockRepository;
        this.cardRepository = cardRepository;
    }

    @PostMapping("/{cardId}")
    @Transactional
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

        blockRepository.save(blockCard);
        return ResponseEntity.ok().build();
    }
}
