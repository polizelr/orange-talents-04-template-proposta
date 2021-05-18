package br.com.zupacademy.rafaela.proposta.models.Proposal;

import br.com.zupacademy.rafaela.proposta.config.exception.ApiRequestException;
import br.com.zupacademy.rafaela.proposta.services.FinancialAnalysisService.FinancialAnalysisService;
import br.com.zupacademy.rafaela.proposta.utils.transactions.TransactionExecutor;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/proposta")
public class ProposalController {
    private final ProposalRepository proposalRepository;
    private final FinancialAnalysisService financialAnalysisService;
    private final TransactionExecutor transaction;
    private final Tracer tracer;

    public ProposalController(ProposalRepository proposalRepository, FinancialAnalysisService financialAnalysisService, TransactionExecutor transaction, Tracer tracer){
        this.proposalRepository = proposalRepository;
        this.financialAnalysisService = financialAnalysisService;
        this.transaction = transaction;
        this.tracer = tracer;
    }

    @PostMapping
    public ResponseEntity<?> addProposal(@Valid @RequestBody ProposalRequest proposalRequest, UriComponentsBuilder uriBuilder){
        if(proposalRepository.existsProposalByDocument(proposalRequest.getDocument())){
            throw new ApiRequestException("Document already exists", HttpStatus.UNPROCESSABLE_ENTITY, "document");
        }
        Proposal proposal = proposalRequest.convert();
        transaction.saveAndCommit(proposal);

        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", proposal.getEmail());

        String userEmail = activeSpan.getBaggageItem(proposal.getEmail());
        activeSpan.setBaggageItem("user.email", userEmail);

        financialAnalysisService.financialAnalysis(proposal);
        transaction.updateAndCommit(proposal);

        URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProposalResponse(proposal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponse> getProposal(@PathVariable Long id){
        Optional<Proposal> proposal = proposalRepository.findById(id);
        if(proposal.isEmpty()){
            throw new ApiRequestException("Proposal with id " + id + " not found.", HttpStatus.NOT_FOUND, "id");
        }
        return ResponseEntity.ok().body(new ProposalResponse(proposal.get()));
    }


}
