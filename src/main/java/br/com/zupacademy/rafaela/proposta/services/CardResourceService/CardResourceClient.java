package br.com.zupacademy.rafaela.proposta.services.CardResourceService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cardResource", url = "${client.cardResource.url}")
public interface CardResourceClient {
    @GetMapping
    CardResourceResponse getCard(@RequestParam("idProposta") String idProposal);
}
