package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.annotation.ValorUnico
import br.com.ecommerce.casadocodigo.domain.model.Pais
import javax.validation.constraints.NotBlank

data class NovoPaisRequest(
        @field:NotBlank
        @field:ValorUnico(fieldName = "nome", className = "Pais")
        val nome: String
) {

    fun toModel(): Pais {
        return Pais(nome = this.nome)
    }

}
