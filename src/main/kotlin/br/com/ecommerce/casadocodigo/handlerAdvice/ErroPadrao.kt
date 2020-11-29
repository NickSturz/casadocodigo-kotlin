package br.com.ecommerce.casadocodigo.handlerAdvice

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErroPadrao(
        val instante : LocalDateTime? = LocalDateTime.now(),
        val status : HttpStatus,
        val erros : List<String>,
        val causa : String
) {

}
