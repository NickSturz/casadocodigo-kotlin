package br.com.ecommerce.casadocodigo.handlerAdvice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErroPadraoAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun argumentoInvalido(exception: IllegalArgumentException): ResponseEntity<ErroPadrao>{
        val erros = ArrayList<String>()

        erros.add(exception.message.toString())

        val erroPadrao: ErroPadrao = ErroPadrao(status = HttpStatus.BAD_REQUEST, erros =  erros, causa = exception.cause.toString())

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroPadrao)
    }
}