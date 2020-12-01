package br.com.ecommerce.casadocodigo.repository

import br.com.ecommerce.casadocodigo.domain.model.CupomDesconto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CupomDescontoRepository: CrudRepository<CupomDesconto, String> {

    fun findByCodigo(codigoCupom: String): CupomDesconto?
}