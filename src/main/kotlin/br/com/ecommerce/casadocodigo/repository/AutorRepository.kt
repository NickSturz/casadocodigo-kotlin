package br.com.ecommerce.casadocodigo.repository

import br.com.ecommerce.casadocodigo.domain.model.Autor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AutorRepository: CrudRepository<Autor, String> {
}