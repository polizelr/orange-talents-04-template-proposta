package br.com.zupacademy.rafaela.proposta.services.CardResourceService;

import br.com.zupacademy.rafaela.proposta.Card.Card;
import br.com.zupacademy.rafaela.proposta.Card.CardRepository;
import br.com.zupacademy.rafaela.proposta.Proposal.Proposal;
import br.com.zupacademy.rafaela.proposta.Proposal.ProposalRepository;
import br.com.zupacademy.rafaela.proposta.utils.enums.ProposalStatusEnum;
import br.com.zupacademy.rafaela.proposta.utils.transactions.TransactionExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CardResourceScheduler {
    private final CardResourceClient cardResourceClient;
    private final TransactionExecutor transaction;
    private final ProposalRepository proposalRepository;
    private final CardRepository cardRepository;

    public CardResourceScheduler(CardResourceClient cardResourceClient, TransactionExecutor transaction, ProposalRepository proposalRepository, CardRepository cardRepository){
        this.cardResourceClient = cardResourceClient;
        this.transaction = transaction;
        this.proposalRepository = proposalRepository;
        this.cardRepository = cardRepository;
    }

    @Scheduled(fixedDelayString = "${scheduler.card.resource}")
    private void getCard(){
        Set<Proposal> proposals = proposalRepository.findProposalByStatusAndCard(ProposalStatusEnum.ELEGIVEL, null);

        proposals.forEach(proposal -> {
            CardResourceResponse cardResponse = cardResourceClient.getCard(proposal.getId().toString());
            Card card = cardResponse.convert();
            transaction.saveAndCommit(card);

            proposal.setCard(card);
            transaction.updateAndCommit(proposal);
        });

    }
}
