package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.annotation.DocumentoValido
import br.com.ecommerce.casadocodigo.domain.model.DadosCliente
import br.com.ecommerce.casadocodigo.domain.model.Estado
import br.com.ecommerce.casadocodigo.domain.model.Pais
import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.validator.constraints.Length
import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class DadosClienteRequest(

        @field: NotBlank
        @field: Email
        val email: String,

        @field: NotBlank
        val nome: String,

        @field: NotBlank
        val sobrenome: String,

        @field: NotBlank
        @field: DocumentoValido
        val documento: String,

        @field: NotBlank
        val endereco: String,

        @field: NotBlank
        val complemento: String,

        @field: NotBlank
        val cidade: String,

        @field: NotBlank
        val idPais: String,

        @field: NotBlank
        val idEstado: String?,

        @field: NotBlank
        @field: Length(min = 11)
        val telefone: String,

        @field: NotBlank
        @field: Positive
        @field: Length(min = 8)
        val cep: String
) {
        fun toModel(manager: EntityManager): DadosCliente{
                val paisBuscado = manager.find(Pais::class.java, idPais)
                Assert.notNull(paisBuscado, "Pais não encontrado para id: $idPais")

                val dadosCliente = DadosCliente(
                        email = this.email,
                        nome = this.nome,
                        sobrenome = this.sobrenome,
                        documento = this.documento,
                        endereco = this.endereco,
                        complemento = this.complemento,
                        cidade = this.cidade,
                        pais = paisBuscado,
                        telefone = this.telefone,
                        cep = this.cep)

                if (idEstado != null){
                        val estadoBuscado = manager.find(Estado::class.java, idEstado)
                        Assert.notNull(estadoBuscado, "Estado não encontrado paara id: $idEstado")

                        Assert.isTrue(estadoBuscado.pais.id == paisBuscado.id, "Estado não pertence a Pais escolhido.")

                        dadosCliente.estado = estadoBuscado
                }

                return dadosCliente
        }
}