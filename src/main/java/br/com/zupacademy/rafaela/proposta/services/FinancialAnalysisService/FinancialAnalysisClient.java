package br.com.zupacademy.rafaela.proposta.services.FinancialAnalysisService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "financialAnalysis", url = "${client.financialAnalysis.url}")
public interface FinancialAnalysisClient {

    @PostMapping
    FinancialAnalysisResponse financialAnalysis(FinancialAnalysisRequest financialAnalysisRequest);
}
