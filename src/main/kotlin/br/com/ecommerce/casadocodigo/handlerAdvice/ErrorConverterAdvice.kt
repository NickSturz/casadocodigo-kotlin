package br.com.ecommerce.casadocodigo.handlerAdvice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorConverterAdvice {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun erroConverter(httpMessageNotReadableException: HttpMessageNotReadableException): ResponseEntity<ErroPadrao>{
        val erro = ArrayList<String>()

        erro.add(httpMessageNotReadableException.message.toString())

        val erroPadrao: ErroPadrao = ErroPadrao(erros = erro, status = HttpStatus.BAD_REQUEST, causa = httpMessageNotReadableException.cause.toString())

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao)
    }
}