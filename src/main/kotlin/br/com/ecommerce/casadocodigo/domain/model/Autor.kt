package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class Autor(
        @NotBlank
        var nome:String,
        @NotBlank @Email
        var email: String,
        @NotBlank
        var descricao: String,
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
        val id: String = ""
) {

    val instante: LocalDateTime = LocalDateTime.now()
}