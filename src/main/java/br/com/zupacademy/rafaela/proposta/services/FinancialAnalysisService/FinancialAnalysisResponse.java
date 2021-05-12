package br.com.zupacademy.rafaela.proposta.services.FinancialAnalysisService;

import br.com.zupacademy.rafaela.proposta.utils.enums.FinancialAnalysisStatusEnum;

public class FinancialAnalysisResponse {
    private String documento;
    private String nome;
    private FinancialAnalysisStatusEnum resultadoSolicitacao;
    private String idProposta;

    public FinancialAnalysisResponse(String documento, String nome, FinancialAnalysisStatusEnum resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }
}
