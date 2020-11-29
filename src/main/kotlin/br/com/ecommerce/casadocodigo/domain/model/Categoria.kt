package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "categoria")
data class Categoria(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        @field: NotBlank
        @field: Column(nullable = true)
        val nome: String
) {
    constructor() : this("","") {
    }

}
