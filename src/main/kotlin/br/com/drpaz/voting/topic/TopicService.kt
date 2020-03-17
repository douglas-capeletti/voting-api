package br.com.drpaz.voting.topic

import br.com.drpaz.voting.topic.model.dto.TopicCreate
import br.com.drpaz.voting.topic.model.dto.TopicResponse
import br.com.drpaz.voting.topic.model.entity.Topic
import br.com.drpaz.voting.util.Functions.onlyOneOrThrow
import br.com.drpaz.voting.util.exception.ResourceNotFoundException
import br.com.drpaz.voting.util.exception.UncheckedSaveResponseException
import org.springframework.stereotype.Service

@Service
class TopicService constructor(private val repository: TopicRepository) : TopicFacade {

    override fun create(create: TopicCreate): String {
        return create
                .toEntity()
                .save()
    }

    override fun getByName(name: String): List<TopicResponse> {
        return repository
                .findAllByName(name)
                .map { it.toResponse() }
    }

    fun getTopicByIdOrThrows(id: String): Topic {
        return repository
                .findById(id)
                .orElseThrow {
                    throw ResourceNotFoundException("Topic")
                }
    }

    fun getSingleTopicByNameOrThrows(id: String): Topic {
        return repository
                .findAllByName(id)
                .onlyOneOrThrow()
    }

    private fun Topic.save() = repository.save(this).id ?: throw UncheckedSaveResponseException()

    private fun TopicCreate.toEntity() = Topic(name = name, description = description)

    //EXPORTED
    companion object {
        fun Topic.toResponse(): TopicResponse {
            return TopicResponse(
                    id = id ?: "",
                    name = name ?: "",
                    description = description ?: ""
            )
        }
    }
}