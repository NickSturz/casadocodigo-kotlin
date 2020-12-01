package br.com.ecommerce.casadocodigo.annotation

import br.com.ecommerce.casadocodigo.validator.DocumentoValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = arrayOf(DocumentoValidator::class))
annotation class DocumentoValido(
    val message: String = "não é valido.",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)
