package br.com.drpaz.voting.session

import br.com.drpaz.voting.session.model.dto.SessionCreate
import br.com.drpaz.voting.session.model.dto.SessionResponse
import br.com.drpaz.voting.session.model.entity.Session
import br.com.drpaz.voting.topic.TopicService
import br.com.drpaz.voting.topic.TopicService.Companion.toResponse
import br.com.drpaz.voting.topic.model.entity.Topic
import br.com.drpaz.voting.util.Functions.isPaste
import br.com.drpaz.voting.util.exception.ResourceNotFoundException
import br.com.drpaz.voting.util.exception.UncheckedSaveResponseException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SessionService constructor(
        private val repository: SessionRepository,
        private val topicService: TopicService) : SessionFacade {

    override fun create(topicId: String, create: SessionCreate): String {
        return topicService
                .getTopicByIdOrThrows(topicId)
                .run { create.toEntity(this) }
                .save()
    }

    override fun createByTopicName(topicName: String, create: SessionCreate): String {
        return topicService
                .getSingleTopicByNameOrThrows(topicName)
                .run { create.toEntity(this) }
                .save()
    }

    override fun getById(id: String) = getSessionByIdOrThrows(id).toResponse()

    override fun getByTopicName(topicName: String): List<SessionResponse> {
        return topicService
                .getSingleTopicByNameOrThrows(topicName)
                .run { repository.findAllByTopic(this) }
                .map { it.toResponse() }

    }

    fun getSessionByIdOrThrows(id: String): Session {
        return repository
                .findById(id)
                .orElseThrow {
                    throw ResourceNotFoundException("Session")
                }
    }

    private fun Session.save() = repository.save(this).id ?: throw UncheckedSaveResponseException()

    private fun SessionCreate.toEntity(topic: Topic): Session {
        val now = LocalDateTime.now()
        return Session(
                topic = topic,
                expirationDate = now.plusSeconds(this.duration?.seconds ?: 60L),
                createdDate = now
        )
    }

    private fun Session.toResponse(): SessionResponse {
        return SessionResponse(
                id = id ?: "",
                topic = topic?.toResponse(),
                expirationDate = expirationDate ?: LocalDateTime.now(),
                open = expirationDate.isPaste()
        )
    }


}
