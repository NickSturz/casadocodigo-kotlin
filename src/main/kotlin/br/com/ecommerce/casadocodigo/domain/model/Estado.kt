package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "estado")
data class Estado(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @field:NotBlank
        val nome: String,

        @field: NotNull
        @OneToOne
        val pais: Pais
) {

    constructor(): this(
            id = "",
            nome= "",
            pais = Pais()
    ){

    }

}
