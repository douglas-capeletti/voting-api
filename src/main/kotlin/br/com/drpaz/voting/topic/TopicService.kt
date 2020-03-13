package br.com.drpaz.voting.topic

import br.com.drpaz.voting.topic.model.dto.TopicCreate
import br.com.drpaz.voting.topic.model.dto.TopicResponse
import br.com.drpaz.voting.topic.model.entity.Topic
import org.springframework.stereotype.Service

@Service
class TopicService constructor(private val repository: TopicRepository) {

    fun create(create: TopicCreate): String? {
        val entity = create.toEntity()
        return repository.save(entity).id
    }

    fun findByName(cpf: String): List<TopicResponse> {
        val entity = repository.findAllByName(cpf)
        return entity.map { e -> e.toResponse() }
    }

    private fun TopicCreate.toEntity(): Topic {
        return Topic(
                name = name,
                description = description
        )
    }

    private fun Topic.toResponse(): TopicResponse {
        return TopicResponse(
                id = id ?: "",
                name = name ?: "",
                description = description ?: ""
        )
    }

}
