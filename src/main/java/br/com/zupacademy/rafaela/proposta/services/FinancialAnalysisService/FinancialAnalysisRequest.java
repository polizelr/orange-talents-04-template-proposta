package br.com.zupacademy.rafaela.proposta.services.FinancialAnalysisService;

import br.com.zupacademy.rafaela.proposta.Proposal.Proposal;

public class FinancialAnalysisRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public FinancialAnalysisRequest(Proposal proposal) {
        this.documento = proposal.getDocument();
        this.nome = proposal.getName();
        this.idProposta = proposal.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
