package br.com.ecommerce.casadocodigo.domain.response

import br.com.ecommerce.casadocodigo.domain.model.Autor
import br.com.ecommerce.casadocodigo.domain.model.Categoria
import br.com.ecommerce.casadocodigo.domain.model.Livro
import java.math.BigDecimal
import java.time.LocalDate

data class LivroDetalheResponseDto(
        val id: String?,

        val titulo: String,

        val resumo: String,

        val sumario: String?,

        val preco: BigDecimal,

        val numeroPagina: Int,

        val isbn: String,

        val dataPublicacao: LocalDate,

        val categoria: Categoria,

        val autor: Autor
) {

    constructor(livro: Livro): this(
            id = livro.id,
            titulo = livro.titulo,
            resumo = livro.resumo,
            sumario = livro.sumario,
            preco = livro.preco,
            numeroPagina = livro.numeroPagina,
            isbn = livro.isbn,
            dataPublicacao = livro.dataPublicacao,
            categoria = livro.categoria,
            autor = livro.autor
    )

}
