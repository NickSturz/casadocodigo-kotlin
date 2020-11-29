package br.com.ecommerce.casadocodigo.validator

import br.com.ecommerce.casadocodigo.annotation.ValorUnico
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class ValorUnicoValidator(
        private final val manager: EntityManager
): ConstraintValidator<ValorUnico, String> {

    private var fieldName: String = ""
    private var className: String = ""

    override fun initialize(constraintAnnotation: ValorUnico?) {
        if (constraintAnnotation != null) {
            fieldName = constraintAnnotation.fieldName
            className = constraintAnnotation.className
        }
    }

    override fun isValid(valor: String?, p1: ConstraintValidatorContext?): Boolean {
        if (StringUtils.isEmpty(valor)) return true

        val valorBuscado = manager
                .createQuery("select p from $className p where p.$fieldName = :pValue")
                .setParameter("pValue",valor)
                .resultList

        return !(valorBuscado.size>0)
    }

}
