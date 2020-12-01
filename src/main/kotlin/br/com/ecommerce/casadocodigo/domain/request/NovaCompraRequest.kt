package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.domain.model.Compra
import javax.persistence.EntityManager
import javax.validation.Valid

data class NovaCompraRequest(

        @field: Valid
        val dadosCliente: DadosClienteRequest

) {
    fun toModel(manager: EntityManager): Compra{
        return Compra(dadosCliente = this.dadosCliente.toModel(manager))
    }

}
