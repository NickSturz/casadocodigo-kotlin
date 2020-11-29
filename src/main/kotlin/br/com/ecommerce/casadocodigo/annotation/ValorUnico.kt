package br.com.ecommerce.casadocodigo.annotation

import br.com.ecommerce.casadocodigo.validator.ValorUnicoValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = arrayOf(ValorUnicoValidator::class))
@MustBeDocumented
annotation class ValorUnico(
    val message: String = "já existe este valor no banco de dados",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val fieldName: String,
    val className: String
)
