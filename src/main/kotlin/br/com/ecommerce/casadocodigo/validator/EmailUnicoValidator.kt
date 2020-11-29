package br.com.ecommerce.casadocodigo.validator

import br.com.ecommerce.casadocodigo.domain.model.Autor
import br.com.ecommerce.casadocodigo.domain.request.NovoAutorRequest
import br.com.ecommerce.casadocodigo.resource.AutorResource
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import javax.persistence.EntityManager

@Component
class EmailUnicoValidator(private final val manager: EntityManager) : Validator{

    override fun supports(clazz: Class<*>): Boolean {
        return NovoAutorRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(value: Any, errors: Errors) {
        val novoAutorRequest: NovoAutorRequest = value as NovoAutorRequest

        val autorBuscadoPeloEmail = manager.createQuery("select e from Autor e where e.email=:pValue", Autor::class.java)
                .setParameter("pValue", novoAutorRequest.email)
                .resultList

        if (!autorBuscadoPeloEmail.isEmpty()){
            errors.rejectValue("email", "", "já existe autor com este valor ")
        }
    }
}