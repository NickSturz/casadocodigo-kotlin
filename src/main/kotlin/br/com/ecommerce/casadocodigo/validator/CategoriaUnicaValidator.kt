package br.com.ecommerce.casadocodigo.validator

import br.com.ecommerce.casadocodigo.domain.model.Categoria
import br.com.ecommerce.casadocodigo.domain.request.NovaCategoriaRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import javax.persistence.EntityManager

@Component
class CategoriaUnicaValidator(
        private val manager: EntityManager
): Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return NovaCategoriaRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(value: Any, errors: Errors) {
        val novaCategoriaRequest = value as NovaCategoriaRequest

        val categoriaBuscadaPeloNome = manager.createQuery("select c from Categoria c where c.nome =: pValue", Categoria::class.java)
                .setParameter("pValue", novaCategoriaRequest.nome)
                .resultList
        if (!categoriaBuscadaPeloNome.isEmpty()){
            errors.rejectValue("nome","","ja existe Categoria com esse valor")
        }

    }
}