package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.request.NovoEstadoRequest
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
@RequestMapping("/v1/estados")
class EstadoResource(
        private final val manager: EntityManager
) {

    private val logger: Logger = LoggerFactory.getLogger(EstadoResource::class.java)

    @PostMapping
    @Transactional
    fun novoEstado(@RequestBody @Valid novoEstadoRequest: NovoEstadoRequest, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<String>{
        logger.info("Requisição para cadastrar novo estado recebida: ${novoEstadoRequest}")

        val novoEstado =  novoEstadoRequest.toModel(manager)

        manager.persist(novoEstado)

        logger.info("Estado criado com sucesso. Id: $")
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/estados/{id}").buildAndExpand(novoEstado.id).toUri())
                .build()
    }
}