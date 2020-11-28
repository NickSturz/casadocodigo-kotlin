package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.model.Autor
import br.com.ecommerce.casadocodigo.domain.request.NovoAutorRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger.getLogger
import javax.validation.Valid

@RestController
@RequestMapping("/autores")
class AutorResource {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    fun novoAutor(@Valid @RequestBody novoAutorRequest: NovoAutorRequest):ResponseEntity<Autor> {
        log.info("Requisição recebida para cadastrar novo autor: $novoAutorRequest")

        return ResponseEntity.ok().build();
    }
}