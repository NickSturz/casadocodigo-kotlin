package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.annotation.ValorUnico
import br.com.ecommerce.casadocodigo.domain.model.Autor
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class NovoAutorRequest(
        @field: NotBlank
        var nome:String,

        @field:NotBlank
        @field: ValorUnico(fieldName = "email", className = "Autor")
        @field: Email
        var email: String,

        @field: NotBlank
        var descricao: String
) {
    fun toModel(): Autor{
        return Autor(nome = nome, email =  email, descricao =  descricao)
    }
}