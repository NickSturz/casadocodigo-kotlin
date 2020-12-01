package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.domain.model.CarrinhoCompra
import br.com.ecommerce.casadocodigo.domain.model.ItensCompra
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.persistence.OneToMany
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class CarrinhoCompraRequest(
        @field: NotNull
        @field: Positive
        val total: BigDecimal,

        @field: Size(min = 1 )
        @field: Valid
        @OneToMany
        val itens: Set<ItensCompraRequest>
) {

        fun toModel(manager: EntityManager): CarrinhoCompra {

                val itens: Set<ItensCompra> = this.itens
                        .map {
                               it.toModel(manager)
                        }.toSet()

                return CarrinhoCompra(
                        total = this.total,
                        itens = itens
                )
        }

}
