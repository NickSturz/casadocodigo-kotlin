package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.model.Livro
import br.com.ecommerce.casadocodigo.domain.response.LivroDetalheResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager

@RestController
@RequestMapping("/v1/livros")
class DetalhesLivroRespurce(private val manager: EntityManager) {

    @GetMapping("/{id}/detalhes")
    fun detalhesLivro(@PathVariable("id") idLivro: String): ResponseEntity<LivroDetalheResponseDto> {

        val livroBuscado = manager.find(Livro::class.java, idLivro)
        if (livroBuscado == null){
            return ResponseEntity.notFound().build()
        }

        val livroDetalheResponseDto = LivroDetalheResponseDto(livroBuscado)

        return ResponseEntity.ok().body(livroDetalheResponseDto)
    }
}