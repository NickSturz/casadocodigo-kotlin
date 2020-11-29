package br.com.ecommerce.casadocodigo.repository

import br.com.ecommerce.casadocodigo.domain.model.Categoria
import org.springframework.data.repository.CrudRepository

interface CategoriaRepository: CrudRepository<Categoria, String> {
}
