package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.annotation.ValorUnico
import br.com.ecommerce.casadocodigo.domain.model.Categoria
import javax.validation.constraints.NotBlank

data class NovaCategoriaRequest(
        @field:NotBlank
        @field: ValorUnico(fieldName = "nome", className = "Categoria")
        val nome: String
) {
    constructor():this(""){
    }

    fun toModel(): Categoria {
        return Categoria(nome = this.nome)
    }
}
