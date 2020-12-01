package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.request.NovoPaisRequest
import io.swagger.v3.oas.annotations.Operation
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
@RequestMapping("/v1/paises")
class PaisResource(private  val manager: EntityManager) {

    private val logger: Logger = LoggerFactory.getLogger(PaisResource::class.java)

    @Tag(name = "Pais",description = "Cadastra um novo Pais")
    @Operation(description = "Cadastra um novo Pais")
    @PostMapping
    @Transactional
    fun novoPais(@RequestBody @Valid novoPaisRequest: NovoPaisRequest, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<String>{

        logger.info("Requisição para cadastrar novo pais recebida: $novoPaisRequest")

        val novoPais = novoPaisRequest.toModel()

        manager.persist(novoPais)

        logger.info("Pais cadastrado com sucesso. Id: ")
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/paises/{id}").buildAndExpand(novoPais.id).toUri())
                .build()
    }
}