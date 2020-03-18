package br.com.drpaz.voting.associate

import br.com.drpaz.voting.associate.model.dto.AssociateCreate
import br.com.drpaz.voting.associate.model.dto.AssociateResponse

interface AssociateFacade {

    fun create(create: AssociateCreate): String
    fun getById(id: String): AssociateResponse
    fun getByCpf(cpf: String): List<AssociateResponse>
}