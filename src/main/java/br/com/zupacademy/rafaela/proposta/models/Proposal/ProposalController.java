package br.com.zupacademy.rafaela.proposta.models.Proposal;

import br.com.zupacademy.rafaela.proposta.config.exception.ApiRequestException;
import br.com.zupacademy.rafaela.proposta.services.FinancialAnalysisService.FinancialAnalysisService;
import br.com.zupacademy.rafaela.proposta.utils.transactions.TransactionExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/proposta")
public class ProposalController {
    private final ProposalRepository proposalRepository;
    private final FinancialAnalysisService financialAnalysisService;
    private final TransactionExecutor transaction;

    public ProposalController(ProposalRepository proposalRepository, FinancialAnalysisService financialAnalysisService, TransactionExecutor transaction){
        this.proposalRepository = proposalRepository;
        this.financialAnalysisService = financialAnalysisService;
        this.transaction = transaction;
    }

    @PostMapping
    public ResponseEntity<?> addProposal(@Valid @RequestBody ProposalRequest proposalRequest, UriComponentsBuilder uriBuilder){

        if(proposalRepository.existsProposalByDocument(proposalRequest.getDocument())){
            throw new ApiRequestException("Document already exists", HttpStatus.UNPROCESSABLE_ENTITY, "document");
        }
        Proposal proposal = proposalRequest.convert();
        transaction.saveAndCommit(proposal);

        financialAnalysisService.financialAnalysis(proposal);
        transaction.updateAndCommit(proposal);

        URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProposalResponse(proposal));
    }


}
