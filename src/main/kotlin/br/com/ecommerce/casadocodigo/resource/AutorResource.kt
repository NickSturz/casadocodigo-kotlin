package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.model.Autor
import br.com.ecommerce.casadocodigo.domain.request.NovoAutorRequest
import br.com.ecommerce.casadocodigo.repository.AutorRepository
import br.com.ecommerce.casadocodigo.validator.EmailUnicoValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/v1/autores")
class AutorResource(
        private final val autorRepository: AutorRepository,
        private final val emailUnicoValidator: EmailUnicoValidator
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @InitBinder
    fun init(binder:WebDataBinder){
        binder.addValidators(emailUnicoValidator)
    }

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