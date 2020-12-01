package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.model.Autor
import br.com.ecommerce.casadocodigo.domain.request.NovoAutorRequest
import br.com.ecommerce.casadocodigo.repository.AutorRepository
import br.com.ecommerce.casadocodigo.validator.EmailUnicoValidator
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/v1/autores")
class AutorResource(
        private final val autorRepository: AutorRepository,
        private final val emailUnicoValidator: EmailUnicoValidator
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

//    @InitBinder
//    fun init(binder:WebDataBinder){
//        binder.addValidators(emailUnicoValidator)
//    }

    @Operation(description = "Endpoint para criar um novo autor ")
    @Tag(name = "Autor", description = "Cria um novo autor")
    @PostMapping
    fun novoAutor(@Valid @RequestBody novoAutorRequest: NovoAutorRequest, uriComponentsBuilder: UriComponentsBuilder):ResponseEntity<Autor> {
        log.info("Requisição recebida para cadastrar novo autor: $novoAutorRequest")

        val autor = novoAutorRequest.toModel()

        val autorSalvo = autorRepository.save(autor)

        log.info("Novo autor salvo com id: ${autorSalvo.id}")
        return ResponseEntity
                .created(uriComponentsBuilder.path("/autores/${autorSalvo.id}").build().toUri())
                .build();
    }
}