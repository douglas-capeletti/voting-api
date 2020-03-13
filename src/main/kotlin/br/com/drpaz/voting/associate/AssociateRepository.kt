package br.com.drpaz.voting.associate

import br.com.drpaz.voting.associate.model.entity.Associate
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AssociateRepository : PagingAndSortingRepository<Associate, String> {

    fun findByCpf(cpf: String): List<Associate>

}
