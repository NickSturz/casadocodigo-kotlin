package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Autor(
        @NotBlank
        val nome:String,
        @NotBlank @Email
        val email: String,
        @NotBlank
        val descricao: String,
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
        val id: String = ""
) {
    @field: NotNull
    val instante: LocalDateTime = LocalDateTime.now()
}