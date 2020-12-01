package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import org.springframework.util.StringUtils
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

    @ManyToOne
    var cupomDesconto: CupomDesconto? = null

    fun valorTotalCompraCalculado(): BigDecimal{
        return this.carrinhoCompra.totalCompra().setScale(2)
    }

    fun valorTotalEnviado(): BigDecimal{
        return this.carrinhoCompra.total.setScale(2)
    }

    fun possuiCupom(): Boolean{
        return !StringUtils.isEmpty(cupomDesconto)
    }

    fun valorCupom(): String? {
        if (cupomDesconto == null) return null
        return "${this.cupomDesconto?.desconto}%"
    }

    fun valorADescontar():BigDecimal{
        if (cupomDesconto == null) return BigDecimal.ZERO
        return this.valorTotalCompraCalculado().multiply(cupomDesconto?.descontoDecimal()).setScale(2)
    }

    fun valorFinalCompra(): BigDecimal{
        return valorTotalCompraCalculado().subtract(valorADescontar()).setScale(2)
    }

}
