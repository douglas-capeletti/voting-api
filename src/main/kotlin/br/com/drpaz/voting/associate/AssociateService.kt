package br.com.drpaz.voting.associate

import br.com.drpaz.voting.associate.model.dto.AssociateCreate
import br.com.drpaz.voting.associate.model.dto.AssociateResponse
import br.com.drpaz.voting.associate.model.entity.Associate
import org.springframework.stereotype.Service

@Service
class AssociateService constructor(private val repository: AssociateRepository) {

    fun create(create: AssociateCreate): String? {
        val entity = create.toEntity()
        return repository.save(entity).id
    }

    fun findByCPF(cpf: String): List<AssociateResponse> {
        val entity = repository.findByCpf(cpf)
        return entity.map { e -> e.toResponse() }
    }

    private fun AssociateCreate.toEntity() = Associate(cpf = cpf)

    private fun Associate.toResponse(): AssociateResponse {
        return AssociateResponse(
                id = id ?: "",
                cpf = cpf ?: ""
        )
    }

}
