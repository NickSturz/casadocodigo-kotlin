package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.request.NovaCompraRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/v1/compras")
class CompraResource(
        private val manager: EntityManager
) {

    private val logger: Logger = LoggerFactory.getLogger(CompraResource::class.java)

    @Tag(name = "Compra", description = "Cria uma nova compra")
    @PostMapping
    @Transactional
    fun novaCompra(@RequestBody @Valid novaCompraRequest: NovaCompraRequest, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<String>{
        logger.info("Requisição recebida para cadastrar nova compra: $novaCompraRequest")

        val novaCompra = novaCompraRequest.toModel(manager)

        Assert.isTrue(novaCompra.valorTotalEnviado() == novaCompra.valorTotalCompraCalculado(), "Valor total da compra não bate com o valor real da compra.")

        manager.persist(novaCompra)

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/compras/{id}").buildAndExpand(novaCompra.id).toUri())
                .build()
    }
}