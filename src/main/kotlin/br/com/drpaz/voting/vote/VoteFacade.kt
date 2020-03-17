package br.com.drpaz.voting.vote

import br.com.drpaz.voting.vote.model.dto.VoteCountResponse
import br.com.drpaz.voting.vote.model.dto.VoteCreate
import br.com.drpaz.voting.vote.model.entity.VoteKey


interface VoteFacade {

    fun vote(sessionId: String, create: VoteCreate): VoteKey
    fun countSessionVotes(sessionId: String): VoteCountResponse

}