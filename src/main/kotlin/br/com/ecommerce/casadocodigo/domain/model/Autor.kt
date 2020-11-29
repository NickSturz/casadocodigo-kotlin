package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "autor")
data class Autor(
        @field:NotBlank
        val nome: String,
        @field:NotBlank @field:Email
        @field:Column(unique = true)
        val email: String,
        @field:NotBlank
        val descricao: String
) {
    constructor() : this(nome="", email = "", descricao = "") {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = ""

    @field: NotNull
    val instante: LocalDateTime? = LocalDateTime.now()
}