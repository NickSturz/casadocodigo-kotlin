package br.com.ecommerce.casadocodigo.domain.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Entity
@Table(name = "cupomDesconto")
data class CupomDesconto(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @field: NotBlank
        @field: Column(unique = true)
        val codigo: String,

        @field: Positive
        @field: NotNull
        val desconto: Int,

        @field: Future
        @field: NotNull
        val dataValidade: LocalDateTime
) {
    constructor(): this(
            id = "",
            codigo = "",
            desconto = Int.MIN_VALUE,
            dataValidade = LocalDateTime.MIN
    ){}

    fun isValid(): Boolean {
            return this.dataValidade.isAfter(LocalDateTime.now())
    }

    fun descontoDecimal(): BigDecimal{
        return desconto.toBigDecimal().divide("100".toBigDecimal())
    }
}
