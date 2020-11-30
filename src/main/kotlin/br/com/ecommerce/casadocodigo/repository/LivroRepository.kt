package br.com.ecommerce.casadocodigo.repository

import br.com.ecommerce.casadocodigo.domain.model.Livro
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LivroRepository: CrudRepository<Livro, String> {

}
