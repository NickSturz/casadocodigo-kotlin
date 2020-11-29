package br.com.ecommerce.casadocodigo.handlerAdvice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidacaoErrorAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validacao(exception: MethodArgumentNotValidException): ResponseEntity<ErroPadrao>{
        val erros = ArrayList<String>()

        exception.bindingResult.fieldErrors.stream().forEach { erros.add(" ${it.field}: ${it.defaultMessage}") }

        val erroPadrao = ErroPadrao(status = HttpStatus.BAD_REQUEST, erros = erros, causa = exception.message)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }
}