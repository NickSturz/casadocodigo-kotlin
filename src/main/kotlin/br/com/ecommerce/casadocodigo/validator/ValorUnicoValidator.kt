package br.com.ecommerce.casadocodigo.validator

import br.com.ecommerce.casadocodigo.annotation.ValorUnico
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValorUnicoValidator: ConstraintValidator<ValorUnico, String> {
    override fun isValid(p0: String?, p1: ConstraintValidatorContext?): Boolean {
        TODO("Not yet implemented")
    }

}
