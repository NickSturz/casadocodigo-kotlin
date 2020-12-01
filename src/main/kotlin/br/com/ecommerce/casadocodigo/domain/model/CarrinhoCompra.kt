package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

@Entity
@Table(name = "carrinhoCompra")
data class CarrinhoCompra(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @field: NotNull
        @field: Positive
        val total: BigDecimal,

        @field: Size(min = 1)
        @field: Valid
        @OneToMany(cascade = arrayOf(CascadeType.PERSIST))
        val itens: Set<ItensCompra>
) {
        constructor(): this(
                id = "",
                total = BigDecimal.ZERO,
                itens = HashSet<ItensCompra>()
        ){}

        fun totalCompra():BigDecimal{
                val valorTotalCompra = itens
                        .map { it.valorTotalItem() }
                        .reduce { acumulador, valor ->
                                acumulador + valor
                        }

                return valorTotalCompra
        }

}
