package br.com.ecommerce.casadocodigo.resource

import br.com.ecommerce.casadocodigo.domain.model.Compra
import br.com.ecommerce.casadocodigo.domain.response.CompraDetalheResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager

@RestController
@RequestMapping("/v1/compras")
class CompraDetalheResource(
        private final val manager: EntityManager
) {

    private val logger: Logger = LoggerFactory.getLogger(CompraDetalheResource::class.java)

    @Tag(name = "Compra")
    @Operation(description = "Exibe detalhes da compra a partir de um id.")
    @GetMapping("/{id}")
    fun detalheCompra(@PathVariable("id") idCompra: String):ResponseEntity<CompraDetalheResponseDto>{
        logger.info("Requisição recebida para mostrar detalhes da compra com id: $idCompra")

        val compraBuscada = manager.find(Compra::class.java, idCompra)
        if (compraBuscada == null){
            return ResponseEntity.notFound().build()
        }

        val compraDetalheResponseDto = CompraDetalheResponseDto(compraBuscada)

        return ResponseEntity.ok().body(compraDetalheResponseDto)
    }

}