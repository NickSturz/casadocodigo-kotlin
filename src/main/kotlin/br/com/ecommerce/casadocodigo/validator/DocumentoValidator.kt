package br.com.ecommerce.casadocodigo.validator

import br.com.ecommerce.casadocodigo.annotation.DocumentoValido
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class DocumentoValidator: ConstraintValidator<DocumentoValido, String> {


    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (StringUtils.isEmpty(value)) return true

        val cpfValidator: CPFValidator = CPFValidator()
        val cnpjValidator: CNPJValidator = CNPJValidator()

        cpfValidator.initialize(null)
        cnpjValidator.initialize(null)

        return cpfValidator.isValid(value, context)
                || cnpjValidator.isValid(value, context)
    }

}
