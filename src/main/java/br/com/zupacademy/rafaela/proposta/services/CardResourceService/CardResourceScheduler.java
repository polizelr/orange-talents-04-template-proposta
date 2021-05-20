package br.com.zupacademy.rafaela.proposta.services.CardResourceService;

import br.com.zupacademy.rafaela.proposta.models.Card.Card;
import br.com.zupacademy.rafaela.proposta.models.Proposal.Proposal;
import br.com.zupacademy.rafaela.proposta.models.Proposal.ProposalRepository;
import br.com.zupacademy.rafaela.proposta.utils.enums.ProposalStatusEnum;
import br.com.zupacademy.rafaela.proposta.utils.transactions.TransactionExecutor;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CardResourceScheduler {
    private final CardResourceClient cardResourceClient;
    private final TransactionExecutor transaction;
    private final ProposalRepository proposalRepository;
    private final Tracer tracer;

    public CardResourceScheduler(CardResourceClient cardResourceClient, TransactionExecutor transaction, ProposalRepository proposalRepository, Tracer tracer){
        this.cardResourceClient = cardResourceClient;
        this.transaction = transaction;
        this.proposalRepository = proposalRepository;
        this.tracer = tracer;
    }

    @Scheduled(fixedDelayString = "${scheduler.card.resource}")
    private void getCard(){
        Set<Proposal> proposals = proposalRepository.findProposalByStatusAndCard(ProposalStatusEnum.ELEGIVEL, null);

        proposals.forEach(proposal -> {
//            Span activeSpan = tracer.activeSpan();
//            activeSpan.setBaggageItem("user.email", "orange@talents.com");
            CardResourceResponse cardResponse = cardResourceClient.getCard(proposal.getId().toString());
            Card card = cardResponse.convert();
            transaction.saveAndCommit(card);

            proposal.setCard(card);
            transaction.updateAndCommit(proposal);
        });

    }
}
