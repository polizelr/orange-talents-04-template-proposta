package br.com.zupacademy.rafaela.proposta.services.FinancialAnalysisService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "financialAnalysis", url = "${financialAnalysis}")
public interface FinancialAnalysisClient {

    @PostMapping
    FinancialAnalysisResponse financialAnalysis(FinancialAnalysisRequest financialAnalysisRequest);
}
