package br.com.ecommerce.casadocodigo.domain.response

import br.com.ecommerce.casadocodigo.domain.model.CarrinhoCompra
import br.com.ecommerce.casadocodigo.domain.model.Compra
import br.com.ecommerce.casadocodigo.domain.model.DadosCliente

data class CompraDetalheResponseDto(
        val id: String?,
        val detalheValorCompra: DetalheValorCompraResponseDto,
        val dadosCliente: DadosCliente,
        val carrinhoCompra: CarrinhoCompra

        ) {
    constructor(compra: Compra) : this(
            id = compra.id,
            dadosCliente = compra.dadosCliente,
            carrinhoCompra = compra.carrinhoCompra,
            detalheValorCompra = DetalheValorCompraResponseDto(compra)
    )

}
