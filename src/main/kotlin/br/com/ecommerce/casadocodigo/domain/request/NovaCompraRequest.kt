package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.domain.model.Compra
import br.com.ecommerce.casadocodigo.domain.model.CupomDesconto
import br.com.ecommerce.casadocodigo.repository.CupomDescontoRepository
import org.springframework.util.Assert
import org.springframework.util.StringUtils
import javax.persistence.EntityManager
import javax.validation.Valid

data class NovaCompraRequest(

        @field: Valid
        val dadosCliente: DadosClienteRequest,

        @field: Valid
        val carrinhoCompra: CarrinhoCompraRequest,

        val cupomDesconto: String? = null

) {
    fun toModel(manager: EntityManager, cupomDescontoRepository: CupomDescontoRepository): Compra{

        val novaCompra = Compra(
                dadosCliente = this.dadosCliente.toModel(manager),
                carrinhoCompra = this.carrinhoCompra.toModel(manager = manager)
        )

        if (this.cupomDesconto != null){
            val cupomBuscadoPeloCodigo = cupomDescontoRepository.findByCodigo(codigoCupom = this.cupomDesconto)
            Assert.notNull(cupomBuscadoPeloCodigo, "Cupom com código : $cupomDesconto, não encontrado")
            Assert.isTrue(cupomBuscadoPeloCodigo?.isValid()!!, "Cupom de Desconto expirado")

            novaCompra.cupomDesconto = cupomBuscadoPeloCodigo
        }

        return novaCompra
    }

}
