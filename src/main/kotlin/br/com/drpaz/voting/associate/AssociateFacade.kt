package br.com.drpaz.voting.associate

import br.com.drpaz.voting.associate.model.dto.AssociateCreate
import br.com.drpaz.voting.associate.model.dto.AssociateResponse
import org.springframework.web.bind.annotation.PathVariable
import javax.validation.Valid

interface AssociateFacade {

    fun create(@Valid create: AssociateCreate): String
    fun getById(id: String): AssociateResponse
    fun getByCpf(@PathVariable cpf: String): List<AssociateResponse>
}