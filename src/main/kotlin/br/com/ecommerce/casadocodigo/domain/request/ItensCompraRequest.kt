package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.domain.model.ItensCompra
import br.com.ecommerce.casadocodigo.domain.model.Livro
import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ItensCompraRequest(
        @field:NotBlank
        val idLivro: String,

        @field:Positive
        @field:NotNull
        val quantidade: Int
) {
        fun toModel(manager: EntityManager): ItensCompra {
                val livroBuscado = manager.find(Livro::class.java, this.idLivro)
                Assert.notNull(livroBuscado,"Livro não encontrado para id: $idLivro")

                return ItensCompra(
                        quantidade = this.quantidade,
                        livro = livroBuscado
                        )
        }
}
