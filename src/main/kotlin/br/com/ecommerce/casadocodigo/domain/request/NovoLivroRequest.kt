package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.annotation.ValorUnico
import br.com.ecommerce.casadocodigo.domain.model.Autor
import br.com.ecommerce.casadocodigo.domain.model.Categoria
import br.com.ecommerce.casadocodigo.domain.model.Livro
import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.validator.constraints.Length
import org.springframework.util.Assert
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.validation.constraints.*

data class NovoLivroRequest(
        @field: NotBlank
        @field: ValorUnico(fieldName = "titulo", className = "Livro")
        private val titulo: String,

        @field: NotBlank
        @field: Length(max = 500)
        private val resumo: String,

        private val sumario: String?,

        @field:NotNull
        @field:Min(20)
        @field:Positive
        private val preco: BigDecimal,

        @field:NotNull
        @field:Positive
        @field:Min(100)
        private val numeroPagina: Int,

        @field: NotBlank
        @field: ValorUnico(fieldName = "isbn", className = "Livro")
        private val isbn: String,

        @field:NotNull
        @field:Future
        @field: JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
        private val dataPublicacao: LocalDate,

        @field:NotBlank
        private val categoriaId: String,

        @field:NotBlank
        private val autorId: String
) {
    fun toModel(manager: EntityManager): Livro{
        val categoria = manager.find(Categoria::class.java, categoriaId)
        Assert.notNull(categoria, "Categoria não achada para categoriaId: $categoriaId")

        val autor = manager.find(Autor::class.java, autorId)
        Assert.notNull(autor, "Autor não achado para autorId: $autorId")

        return Livro(titulo = titulo, resumo= resumo, sumario = sumario, preco = preco, numeroPagina = numeroPagina, isbn = isbn, dataPublicacao = dataPublicacao, categoria = categoria, autor = autor)
    }
}
