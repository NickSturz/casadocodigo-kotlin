package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.domain.model.Categoria
import javax.validation.constraints.NotBlank

data class NovaCategoriaRequest(
        @field:NotBlank
        val nome: String
) {
    constructor():this(""){
    }

    fun toModel(): Categoria {
        return Categoria(nome = this.nome)
    }
}
