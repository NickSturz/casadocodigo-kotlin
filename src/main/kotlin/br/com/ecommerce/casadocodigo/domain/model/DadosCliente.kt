package br.com.ecommerce.casadocodigo.domain.model

import javax.persistence.Embeddable
import javax.persistence.OneToOne
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Embeddable
data class DadosCliente(

        @field: NotBlank
        @field: Email
        val email: String,

        @field: NotBlank
        val nome: String,

        @field: NotBlank
        val sobrenome: String,

        @field: NotBlank
        val documento: String,

        @field: NotBlank
        val endereco: String,

        @field: NotBlank
        val complemento: String,

        @field: NotBlank
        val cidade: String,

        @field: NotBlank
        @OneToOne
        val pais: Pais,

        @field: NotBlank
        @OneToOne
        var estado: Estado? = null,

        @field: NotBlank
        val telefone: String,

        @field: NotBlank
        val cep: String
) {
    constructor(): this(
            email = "" ,
            nome = "",
            sobrenome = "",
            documento = "",
            endereco = "",
            complemento = "",
            cidade = "",
            pais = Pais(),
            estado = Estado(),
            telefone = "",
            cep = ""

    ){}

}
