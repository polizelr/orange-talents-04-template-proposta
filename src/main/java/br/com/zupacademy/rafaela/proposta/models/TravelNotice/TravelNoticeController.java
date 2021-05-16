package br.com.zupacademy.rafaela.proposta.models.TravelNotice;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.models.Card.CardRepository;
import br.com.zupacademy.rafaela.proposta.utils.transactions.TransactionExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/avisoviagem")
public class TravelNoticeController {
    private final TravelNoticeRepository travelNoticeRepository;
    private final CardRepository cardRepository;
    private final TransactionExecutor executor;

    public TravelNoticeController(TravelNoticeRepository travelNoticeRepository, CardRepository cardRepository, TransactionExecutor executor) {
        this.travelNoticeRepository = travelNoticeRepository;
        this.cardRepository = cardRepository;
        this.executor = executor;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> noticeTravel(@PathVariable("id") Long cardId,
                                          @Valid @RequestBody TravelNoticeRequest request,
                                          HttpServletRequest httpRequest){
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        String ipAddress = httpRequest.getRemoteAddr();
        String userAgent = httpRequest.getHeader("User-Agent");

        TravelNotice travelNotice = new TravelNotice(
                request.getDestination(),
                request.getArrivalDate(),
                ipAddress,
                userAgent,
                card.get()
        );

        executor.saveAndCommit(travelNotice);
        return ResponseEntity.ok().build();
    }
}
