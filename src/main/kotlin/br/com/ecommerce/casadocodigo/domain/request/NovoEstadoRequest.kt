package br.com.ecommerce.casadocodigo.domain.request

import br.com.ecommerce.casadocodigo.domain.model.Estado
import br.com.ecommerce.casadocodigo.domain.model.Pais
import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank

data class NovoEstadoRequest(
        @field: NotBlank
        val nome: String,

        @field: NotBlank
        val idPais: String
) {
    fun toModel(manager: EntityManager): Estado{
        val paisBuscado = manager.find(Pais::class.java, this.idPais)
        Assert.notNull(paisBuscado, "Pais não encontrado para id: $idPais")

        return Estado(nome = this.nome, pais = paisBuscado)
    }

}
