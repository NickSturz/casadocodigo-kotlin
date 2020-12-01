package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.Valid

@Entity
@Table(name = "compra")
data class Compra(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @Embedded
        val dadosCliente: DadosCliente,

        @field: Valid
        @OneToOne( cascade = arrayOf(CascadeType.PERSIST))
        val carrinhoCompra: CarrinhoCompra

) {
    constructor(): this(
            id = "",
            dadosCliente = DadosCliente(),
            carrinhoCompra = CarrinhoCompra()
    ){}

    fun valorTotalCompraCalculado(): BigDecimal{
        return this.carrinhoCompra.totalCompra().setScale(2)
    }

    fun valorTotalEnviado(): BigDecimal{
        return this.carrinhoCompra.total.setScale(2)
    }

}
