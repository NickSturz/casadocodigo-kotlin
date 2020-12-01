package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.request.NovoCupomRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/v1/cupons")
class CupomResource(
        private final val manager: EntityManager
) {

    private val logger: Logger = LoggerFactory.getLogger(CupomResource::class.java)

    @Tag(name = "CupomDesconto", description = "cadastro de um novo cupom de desconto")
    @PostMapping
    @Transactional
    fun novoCupom(@RequestBody @Valid novoCupomResquest: NovoCupomRequest, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<String>{
        logger.info("Requisição recebida para cadastrar um novo cupom: $novoCupomResquest")

        val novoCupom = novoCupomResquest.toModel()

        manager.persist(novoCupom)

        logger.info("Cupom cadastrado com sucesso, id: ${novoCupom.id}")
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/cupons/{id}").buildAndExpand(novoCupom.id).toUri())
                .build()
    }
}