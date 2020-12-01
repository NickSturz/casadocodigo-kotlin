package br.com.ecommerce.casadocodigo.domain.response

import br.com.ecommerce.casadocodigo.domain.model.Compra
import java.math.BigDecimal

data class DetalheValorCompraResponseDto(
        val existeCupom: Boolean = false,
        val valorCupom: String?,
        val valorCompra: BigDecimal,
        val descontoCompra: BigDecimal,
        val valorFinalCompra: BigDecimal
) {
    constructor(compra: Compra) : this(
            existeCupom = compra.possuiCupom(),
            valorCupom = compra.valorCupom(),
            valorCompra = compra.valorTotalCompraCalculado(),
            descontoCompra = compra.valorADescontar(),
            valorFinalCompra = compra.valorFinalCompra()
    )

}
