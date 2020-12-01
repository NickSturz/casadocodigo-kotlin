package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "compra")
data class Compra(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @Embedded
        val dadosCliente: DadosCliente

) {
    constructor(): this(
            id = "",
            dadosCliente = DadosCliente()
    ){}

}
