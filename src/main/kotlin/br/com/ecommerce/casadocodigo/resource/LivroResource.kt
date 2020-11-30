package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.request.NovoLivroRequest
import br.com.ecommerce.casadocodigo.repository.LivroRepository
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
@RequestMapping("/v1/livros")
class LivroResource(
        private val manager: EntityManager
) {

    private final val logger: Logger = LoggerFactory.getLogger(LivroResource::class.java)

    @PostMapping
    @Transactional
    fun novoLivro(@RequestBody @Valid novoLivroRequest: NovoLivroRequest, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<String>{

        logger.info("Requisição para cadastrar novo livro recebida: $novoLivroRequest.")

        val livro = novoLivroRequest.toModel(manager)

        manager.persist(livro)

        logger.info("Livro salvo com sucesso, idLivro: ${livro.id}")
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/livros/{id}").buildAndExpand(livro.id).toUri())
                .build();
    }
}