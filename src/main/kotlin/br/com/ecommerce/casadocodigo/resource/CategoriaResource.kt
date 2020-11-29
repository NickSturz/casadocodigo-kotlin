package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.request.NovaCategoriaRequest
import br.com.ecommerce.casadocodigo.repository.CategoriaRepository
import br.com.ecommerce.casadocodigo.validator.CategoriaUnicaValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/v1/categorias")
class CategoriaResource(
        private final val categoriaRepository: CategoriaRepository,
        private final val categoriaUnicaValidator: CategoriaUnicaValidator
        ) {

    private final val logger: Logger = LoggerFactory.getLogger(CategoriaResource::class.java)

    @InitBinder
    fun init(binder:WebDataBinder){
        binder.addValidators(categoriaUnicaValidator)
    }

    @PostMapping
    fun novaCategoria(@RequestBody @Valid novaCategoriaRequest: NovaCategoriaRequest, uriComponentsBuilder: UriComponentsBuilder ): ResponseEntity<String>{
        logger.info("Requisição para criar uma nova categoria recebida: $novaCategoriaRequest")

        val categoria = novaCategoriaRequest.toModel()
        val categoriaSalva = categoriaRepository.save(categoria)

        logger.info("Categoria salva com sucesso com o id: ${categoriaSalva.id}")
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/categorias/${categoriaSalva.id}").build().toUri())
                .build()
    }
}