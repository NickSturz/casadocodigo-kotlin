package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.model.Livro
import br.com.ecommerce.casadocodigo.domain.response.LivroResponseDto
import br.com.ecommerce.casadocodigo.repository.LivroRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

@RestController
@RequestMapping("/v1/livros")
class ListaLivrosResource( private val livroRepository: LivroRepository) {

    @GetMapping
    fun listaLivros(): ResponseEntity<List<LivroResponseDto>> {

        val todosLivros =  livroRepository.findAll() as List<Livro>

        val todosLivrosResponse = toList(todosLivros)

        return ResponseEntity.ok().body(todosLivrosResponse)
    }

    private fun toList(livro: List<Livro>): List<LivroResponseDto>{
        return livro.stream().map({
            LivroResponseDto(id = it.id, titulo = it.titulo)
        }).toList()
    }
}