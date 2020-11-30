package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.model.Livro
import br.com.ecommerce.casadocodigo.domain.response.LivroDetalheResponseDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager

@RestController
@RequestMapping("/v1/livros")
class DetalhesLivroRespurce(private val manager: EntityManager) {

    private val logger: Logger = LoggerFactory.getLogger(DetalhesLivroRespurce::class.java)

    @GetMapping("/{id}/detalhes")
    fun detalhesLivro(@PathVariable("id") idLivro: String): ResponseEntity<LivroDetalheResponseDto> {
        logger.info("Requesição recebida para solicitar detalhes do livro com id: ${idLivro}")

        val livroBuscado = manager.find(Livro::class.java, idLivro)
        if (livroBuscado == null){
            logger.warn("Livro não encontrado para id solicitado: $idLivro")
            return ResponseEntity.notFound().build()
        }

        val livroDetalheResponseDto = LivroDetalheResponseDto(livroBuscado)
        logger.info("Detalhes do livro com id: ${idLivro} encontrado.")
        return ResponseEntity.ok().body(livroDetalheResponseDto)
    }
}