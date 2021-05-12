package br.com.zupacademy.rafaela.proposta.services.FinancialAnalysisService;

import br.com.zupacademy.rafaela.proposta.Proposal.Proposal;
import br.com.zupacademy.rafaela.proposta.utils.enums.ProposalStatusEnum;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class FinancialAnalysisService {
    private final FinancialAnalysisClient financialAnalysisClient;

    public FinancialAnalysisService(FinancialAnalysisClient financialAnalysisClient){
        this.financialAnalysisClient = financialAnalysisClient;
    }

    public void financialAnalysis(Proposal proposal){
        try{
            financialAnalysisClient.financialAnalysis(new FinancialAnalysisRequest(proposal));
            proposal.setStatus(ProposalStatusEnum.ELEGIVEL);
        } catch (FeignException.UnprocessableEntity e){
            proposal.setStatus(ProposalStatusEnum.NAO_ELEGIVEL);
        }
    }
}
