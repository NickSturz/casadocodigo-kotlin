package br.com.ecommerce.casadocodigo.handlerAdvice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidacaoErrorAdvice {

    fun validacao(exception: MethodArgumentNotValidException): ResponseEntity<ErroPadrao>{

    }
}