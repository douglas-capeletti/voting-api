package br.com.drpaz.voting.topic

import br.com.drpaz.voting.topic.model.dto.TopicCreate
import br.com.drpaz.voting.topic.model.dto.TopicResponse

interface TopicFacade {

    fun create(create: TopicCreate): String
    fun getByName(name: String): List<TopicResponse>

}