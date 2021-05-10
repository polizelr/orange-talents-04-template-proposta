package br.com.zupacademy.rafaela.proposta.Proposal;

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

    public ProposalController(ProposalRepository proposalRepository){
        this.proposalRepository = proposalRepository;
    }

    @PostMapping
    public ResponseEntity<ProposalResponse> addProposal(@Valid @RequestBody ProposalRequest proposalRequest, UriComponentsBuilder uriBuilder){
        Proposal proposal = proposalRequest.convert();
        proposalRepository.save(proposal);
        URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProposalResponse(proposal));
    }
}
