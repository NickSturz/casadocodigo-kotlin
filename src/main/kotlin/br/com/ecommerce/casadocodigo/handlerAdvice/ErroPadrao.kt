package br.com.ecommerce.casadocodigo.handlerAdvice

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ErroPadrao(
        val instante: LocalDateTime? = LocalDateTime.now(),
        var status: HttpStatus,
        var List
) {

}
