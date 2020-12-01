package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.domain.model.Compra
import javax.persistence.EntityManager
import javax.validation.Valid

data class NovaCompraRequest(

        @field: Valid
        val dadosCliente: DadosClienteRequest,

        @field: Valid
        val carrinhoCompra: CarrinhoCompraRequest

) {
    fun toModel(manager: EntityManager): Compra{
        return Compra(
                dadosCliente = this.dadosCliente.toModel(manager),
                carrinhoCompra = this.carrinhoCompra.toModel(manager = manager)
        )
    }

}
