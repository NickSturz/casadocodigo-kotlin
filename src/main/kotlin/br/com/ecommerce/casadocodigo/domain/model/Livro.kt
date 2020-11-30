package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.*

@Entity
@Table(name = "livro")
data class Livro(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @field: NotBlank
        @field: Column(unique = true)
        val titulo: String,

        @field: NotBlank
        val resumo: String,

        val sumario: String?,

        @field: NotNull
        @field: Positive
        @field: Min(20)
        val preco: BigDecimal,

        @field: NotNull
        @field: Positive
        val numeroPagina: Int,

        @field: NotBlank
        @field: Column(unique = true)
        val isbn: String,

        @field: Future
        val dataPublicacao: LocalDate,

        @field: NotNull
        @ManyToOne(fetch = FetchType.EAGER)
        val categoria: Categoria,

        @field: NotNull
        @ManyToOne(fetch = FetchType.EAGER)
        val autor: Autor
) {

    constructor(): this(
            "",
            "",
            "",
            "",
            BigDecimal.ZERO,
            20,
            "",
            LocalDate.now(),
            Categoria(),
            Autor()){

    }

}
