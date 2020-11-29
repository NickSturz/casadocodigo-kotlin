package br.com.ecommerce.casadocodigo.annotation

import br.com.ecommerce.casadocodigo.validator.ValorUnicoValidator
import javax.validation.Constraint

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = arrayOf(ValorUnicoValidator::class))
annotation class ValorUnico
