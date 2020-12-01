package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.annotation.ValorUnico
import br.com.ecommerce.casadocodigo.domain.model.CupomDesconto
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class NovoCupomRequest(
        @field: NotBlank
        @field: ValorUnico(fieldName = "codigo", className = "CupomDesconto")
        val codigo: String,

        @field: NotNull
        @field: Positive
        val desconto: Double,

        @field: Future
        @field: NotNull
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
        val dataValidade: LocalDateTime
) {

    fun toModel(): CupomDesconto {
        return CupomDesconto(
                codigo = this.codigo,
                desconto = this.desconto,
                dataValidade = this.dataValidade
        )
    }

}
