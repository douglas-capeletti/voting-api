package br.com.drpaz.voting.associate

import br.com.drpaz.voting.associate.model.dto.AssociateCreate
import br.com.drpaz.voting.associate.model.dto.AssociateResponse
import br.com.drpaz.voting.associate.model.entity.Associate
import br.com.drpaz.voting.commom.exception.InvalidCpfException
import br.com.drpaz.voting.commom.exception.ResourceNotFoundException
import br.com.drpaz.voting.commom.exception.UncheckedSaveResponseException
import br.com.drpaz.voting.util.Functions.onlyDigits
import org.springframework.stereotype.Service

@Service
class AssociateService constructor(private val repository: AssociateRepository) : AssociateFacade {

    override fun create(create: AssociateCreate): String {
        return create
                .toEntity()
                .save()
    }

    override fun getById(id: String) = getAssociateByIdOrThrows(id).toResponse()

    override fun getByCpf(cpf: String): List<AssociateResponse> {
        return findEntityByCpf(cpf)
                .map {
                    it.toResponse()
                }
    }

    fun getAssociateByIdOrThrows(id: String): Associate {
        return repository
                .findById(id)
                .orElseThrow {
                    throw ResourceNotFoundException("Associate")
                }
    }

    private fun findEntityByCpf(cpf: String): List<Associate> {
        return cpf
                .onlyDigits()
                .apply { if (isEmpty()) throw InvalidCpfException() }
                .run { repository.findByCpf(this) }
    }

    private fun Associate.save() = repository.save(this).id ?: throw UncheckedSaveResponseException()

    private fun AssociateCreate.toEntity() = Associate(cpf = cpf.onlyDigits())

    private fun Associate.toResponse(): AssociateResponse {
        return AssociateResponse(
                id = id ?: "",
                cpf = cpf ?: ""
        )
    }

}
