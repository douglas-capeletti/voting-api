package br.com.drpaz.voting.vote

import br.com.drpaz.voting.associate.AssociateService
import br.com.drpaz.voting.associate.model.entity.Associate
import br.com.drpaz.voting.commom.exception.ExpiredSessionException
import br.com.drpaz.voting.commom.exception.UncheckedSaveResponseException
import br.com.drpaz.voting.session.SessionService
import br.com.drpaz.voting.session.model.entity.Session
import br.com.drpaz.voting.util.Functions.isPaste
import br.com.drpaz.voting.vote.model.dto.VoteCountResponse
import br.com.drpaz.voting.vote.model.dto.VoteCreate
import br.com.drpaz.voting.vote.model.entity.Vote
import br.com.drpaz.voting.vote.model.entity.VoteKey
import br.com.drpaz.voting.vote.model.enumeration.ResultEnum.*
import org.springframework.stereotype.Service

@Service
class VoteService constructor(
        private val associateService: AssociateService,
        private val sessionService: SessionService,
        private val repository: VoteRepository) : VoteFacade {

    override fun vote(sessionId: String, create: VoteCreate): VoteKey {
        val session = sessionService
                .getSessionByIdOrThrows(sessionId)
                .apply { if (expirationDate.isPaste().not()) throw ExpiredSessionException() }

        val associate = associateService
                .getAssociateByIdOrThrows(create.associateId)

        return create
                .toEntity(session, associate)
                .save()
    }

    override fun countSessionVotes(sessionId: String): VoteCountResponse {
        val session = sessionService.getSessionByIdOrThrows(sessionId)
        val votes = repository.countSessionVotes(session).map { Pair(it.value, it.count) }.toMap()

        val negatives = votes.getOrDefault(false, 0L)
        val positives = votes.getOrDefault(true, 0L)

        val result = when {
            negatives > positives -> NEGATIVE
            negatives < positives -> POSITIVE
            else -> DRAW
        }

        return VoteCountResponse(
                positive = positives,
                negative = negatives,
                result = result,
                open = session.expirationDate.isPaste()
        )
    }

    private fun VoteCreate.toEntity(session: Session, associate: Associate): Vote {
        val key = VoteKey().apply {
            associateId = associate.id
            sessionId = session.id
        }
        return Vote(
                session = session,
                associate = associate,
                value = vote,
                id = key
        )
    }

    private fun Vote.save() = repository.save(this).id ?: throw UncheckedSaveResponseException()

}
