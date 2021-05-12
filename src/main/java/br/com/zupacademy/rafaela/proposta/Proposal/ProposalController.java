package br.com.zupacademy.rafaela.proposta.Proposal;

import br.com.zupacademy.rafaela.proposta.config.exception.ApiRequestException;
import br.com.zupacademy.rafaela.proposta.services.FinancialAnalysisService.FinancialAnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/proposta")
public class ProposalController {
    private final ProposalRepository proposalRepository;
    private final FinancialAnalysisService financialAnalysisService;

    public ProposalController(ProposalRepository proposalRepository, FinancialAnalysisService financialAnalysisService){
        this.proposalRepository = proposalRepository;
        this.financialAnalysisService = financialAnalysisService;
    }

    @PostMapping
    public ResponseEntity<?> addProposal(@Valid @RequestBody ProposalRequest proposalRequest, UriComponentsBuilder uriBuilder){
        Optional<Proposal> proposalByDocument = proposalRepository.findProposalByDocument(proposalRequest.getDocument());
        if(proposalByDocument.isPresent()){
            throw new ApiRequestException("Document already exists", HttpStatus.UNPROCESSABLE_ENTITY, "document");
        }
        Proposal proposal = proposalRequest.convert();
        proposalRepository.save(proposal);

        financialAnalysisService.financialAnalysis(proposal);
        proposalRepository.save(proposal);

        URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProposalResponse(proposal));
    }


}
