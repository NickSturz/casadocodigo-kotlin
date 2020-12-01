package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Entity
@Table(name = "itensCompra")
data class ItensCompra(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @field: Positive
        @field: NotNull
        val quantidade: Int,

        @field: Valid
        @OneToOne
        val livro: Livro
) {
        constructor(): this(
                id = "",
                quantidade = 0,
                livro = Livro()
        )

        fun valorTotalItem(): BigDecimal{
                return this.quantidade.toBigDecimal().multiply(this.livro.preco)
        }

}
