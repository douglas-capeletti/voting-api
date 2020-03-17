package br.com.drpaz.voting.vote

import br.com.drpaz.voting.session.model.entity.Session
import br.com.drpaz.voting.vote.model.dao.VoteCount
import br.com.drpaz.voting.vote.model.entity.Vote
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface VoteRepository : PagingAndSortingRepository<Vote, String> {

    @Query("select new br.com.drpaz.voting.vote.model.dao.VoteCount(value, count(value)) from vote where session = ?1 group by value")
    fun countSessionVotes(session: Session): List<VoteCount>

}
