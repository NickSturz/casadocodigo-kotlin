package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.model.Livro
import br.com.ecommerce.casadocodigo.domain.response.LivroResponseDto
import br.com.ecommerce.casadocodigo.repository.LivroRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

@RestController
@RequestMapping("/v1/livros")
class ListaLivrosResource( private val livroRepository: LivroRepository) {

    private val logger: Logger = LoggerFactory.getLogger(ListaLivrosResource::class.java)

    @Tag(name = "Livro")
    @Operation(description = "Lista o id e o titulo de todos os livros cadastrado")
    @GetMapping
    fun listaLivros(): ResponseEntity<List<LivroResponseDto>> {

        logger.info("Requisição para solicitar lista de todos os livros cadastrados recebida.")

        val todosLivros =  livroRepository.findAll() as List<Livro>

        val todosLivrosResponse = toList(todosLivros)

        logger.info("Lista de livros buscada: $todosLivrosResponse")
        return ResponseEntity.ok().body(todosLivrosResponse)
    }

    private fun toList(livro: List<Livro>): List<LivroResponseDto>{
        logger.info("converter lista de livros com todos atributos: $livro para lista de livros apenas com 2 atributps")
        return livro.stream().map({
            LivroResponseDto(id = it.id, titulo = it.titulo)
        }).toList()
    }
}