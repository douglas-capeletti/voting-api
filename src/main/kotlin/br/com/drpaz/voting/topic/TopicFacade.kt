package br.com.drpaz.voting.topic

import br.com.drpaz.voting.topic.model.dto.TopicCreate
import br.com.drpaz.voting.topic.model.dto.TopicResponse
import org.springframework.web.bind.annotation.PathVariable
import javax.validation.Valid

interface TopicFacade {

    fun create(@Valid create: TopicCreate): String
    fun getByName(@PathVariable name: String): List<TopicResponse>

}